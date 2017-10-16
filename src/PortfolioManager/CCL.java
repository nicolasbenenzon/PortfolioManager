package PortfolioManager;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * CCL stands for getting, calculating and generating the "contado con liquidación"
 * @author Nicolas Benenzon
 *
 */

public class CCL {
	
	private final static int CANT_CCL = 13;
	private final static int[] FACC = {3, 10, 10, 20, 10, 10, 25, 2, 10, 5, 5, 2, 1};
	private static double bestMinCCL;
	
	/**
	 * Query from Yahoo Finance in JSON format and get the prices and stocks from there
	 * @throws IOException
	 * @throws JSONException
	 */

	public static void updateCCL() throws IOException, JSONException{
		// consulta a Yahoo Finance para que me entregue los datos consultados en formato JSON
		JSONObject json = readJsonFromUrl("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20csv%20where%20url%3D%27http%3A%2F%2Fdownload.finance.yahoo.com%2Fd%2Fquotes.csv%3Fs%3DBFR%2CBMA%2CCRESY%2CEDN%2CGGAL%2CIRS%2CPAM%2CPBR%2CPZE%2CTEO%2CTGS%2CTS%2CYPF%2CFRAN.BA%2CBMA.BA%2CCRES.BA%2CEDN.BA%2CGGAL.BA%2CIRS.BA%2CPAMP.BA%2CAPBR.BA%2CPESA.BA%2CTECO2.BA%2CTGSU2.BA%2CTS.BA%2CYPFD.BA%2CAA17.BA%2CAA17D.BA%2CAY24.BA%2CAY24D.BA%2CDICA.BA%2CDICAD.BA%26f%3Dsl1p%26e%3D.csv%27%20and%20columns%3D%27symbol%2Cprice%2ClastClose%27&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
	    String[] parsedStr = json.toString().split("symbol");
	    String[] strParsed;
	    String[] tickers = new String[CANT_CCL];
	    Double[] prices = new Double[CANT_CCL];
	    Double[] pricesBA = new Double[CANT_CCL];
	    int added = 0, addedBA = 0;
	    for(int i = 0; i < parsedStr.length; i++){
	    	strParsed = parsedStr[i].split(",");
	    	if(strParsed[0].contains(".BA") && addedBA < CANT_CCL){
	    		pricesBA[addedBA] = getPriceFromStr(strParsed[1]);
	    		addedBA++;
	    	}
	    	else if(!strParsed[0].contains("query") && added < CANT_CCL){
	    		tickers[added] = getTickerFromStr(strParsed[0]);
	    		prices[added] = getPriceFromStr(strParsed[1]);
	    		added++;
	    	}
	    }
	    setCCL(tickers, prices, pricesBA);
	}
	
	/**
	 * Create the tables and show them in the main screen
	 * @param tickers
	 * @param prices
	 * @param pricesBA
	 */
	
	private static void setCCL(String[] tickers, Double[] prices, Double[] pricesBA){
		String allTickers = "", pricesD = "", pricesP = "", bestCCL = "", bestCCLI = "", dolarCCL = "";
		double max = 0;
		double min = calculateCCL(pricesBA[0], prices[0], FACC[0]);
		double calcCCL;
	    for(int i = 0; i < CANT_CCL; i++){
	    	allTickers += tickers[i] + "<br>";
	    	pricesD += "U$S " + prices[i] + "<br>";
	    	pricesP += "$ " + pricesBA[i] + "<br>";
	    	dolarCCL += "U$S " + (Math.round(calculateCCL(pricesBA[i], prices[i], FACC[i]) * 100.0) / 100.0) + "<br>";
	    	calcCCL = calculateCCL(pricesBA[i], prices[i], FACC[i]);
	    	if(calcCCL > max){
	    		max = calcCCL;
	    		bestCCLI = tickers[i];
	    	}
	    	if(calcCCL < min){
	    		min = calcCCL;
	    		bestMinCCL = (Math.round(calculateCCL(pricesBA[i], prices[i], FACC[i]) * 100.0) / 100.0);
	    		bestCCL = tickers[i];
	    	}
	    }
	    MainScreen.setCCLTickers(allTickers);
	    MainScreen.setCCLPrices_D(pricesD);
	    MainScreen.setCCLPrices_P(pricesP);
	    MainScreen.setBestCCL(bestCCL);
	    MainScreen.setBestCCLI(bestCCLI);
	    MainScreen.setDolarCCL(dolarCCL);
	}
	
	public static double getMinCCL(){
		return bestMinCCL;
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

	private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    	String jsonText = readAll(rd);
	    	JSONObject json = new JSONObject(jsonText);
	    	return json;
	    } finally {
	      is.close();
	    }
	}
	
	/**
	 * Calculate dolar CCL
	 */
	private static double calculateCCL(double share, double share_D, int facc){
		return share / share_D * facc;
	}
	
	private static String getTickerFromStr(String str){
		str = str.replaceAll("[^A-Z]", "");
		return str;
	}
	
	private static double getPriceFromStr(String str){
		double f = Double.valueOf(str.replaceAll("[^\\d.]+|\\.(?!\\d)", ""));
		return f;
	}

}
