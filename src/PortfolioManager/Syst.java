package PortfolioManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 */

/**
 * @author Nicolas Benenzon
 *
 */
public class Syst {
	
	private static List<Stock> stocks;
	private static List<Commodity> commodities;
	private static List<Futuro> futuros;
	private static List<Bono> bonos;
	private static List<Opcion> opciones;

	public Syst(List<Stock> stocks, List<Commodity> commodities, List<Futuro> futuros, List<Bono> bonos,
			List<Opcion> opciones) {
		super();
		this.stocks = stocks;
		this.commodities = commodities;
		this.futuros = futuros;
		this.bonos = bonos;
		this.opciones = opciones;
	}

	public static void updateValuesFromInternet(){
		stocks = getStocksFromMerval();
		commodities = new ArrayList<Commodity>();
		futuros = new ArrayList<Futuro>();
		bonos = new ArrayList<Bono>();
		opciones = new ArrayList<Opcion>();
	}
	
	private static List<Stock> getStocksFromMerval(){
		Document doc = null;
		boolean loaded = false;
		List<Stock> mervalStocks = new ArrayList<Stock>();
		try {
			doc = Jsoup.connect("http://www.merval.sba.com.ar/Vistas/Cotizaciones/Acciones.aspx").get();
			loaded = true;
		} catch (IOException e) {
			
		}
		if(loaded){
			String[] tickers = doc.select("td.txtGrisTabla_ConBorde a.link").text().split(" ");
			String[] parsedData = doc.select("td.txtGrisTabla_ConBorde ~ td.txtGrisTabla_ConBorde").text().replaceAll("0,00 %", "skip").split(" ");
			final List<String> list = new ArrayList<String>();
			Collections.addAll(list, parsedData); 
			list.removeAll(Collections.singleton("skip"));
			parsedData = list.toArray(new String[list.size()]);
			String[] prices = new String[tickers.length];
			int added = 0;
			for(int i = 5; i < parsedData.length; i+=9){
				prices[added] = parsedData[i].replace(",", ".");
				added++;
			}
			for(int i = 0; i < tickers.length; i++){
				double auxDouble = Double.parseDouble(prices[i]);
				mervalStocks.add(new Stock(auxDouble, i, i, i, i, i, i, tickers[i], tickers[i]));
			}
			return mervalStocks;
		}
		return null;
	}
	
	public static void loadNewsFromInternet(){
		
		Document doc = null;
		boolean loaded = false;
		String news1, news2, news3, news4;
		news1 = news2 = news3 = news4 = "No hay conexión a internet";
		try {
			doc = Jsoup.connect("https://www.cronista.com/").get();
			loaded = true;
		} catch (IOException e) {
			
		}
		if(loaded)
		{
	        news1 = doc.select(".content-bloque-4 .entry-box .entry-data h2 a").first().text();
	        news2 = doc.select(".pull-left .entry-box .entry-data h3 a").first().text();
	        news3 = doc.select(".content-bloque-3 article div h3 a").last().text();
	        news4 = doc.select(".entry-box .entry-data h3 a").first().text();
		}
		MainScreen.setNews(news1, news2, news3, news4);
		getStocksFromMerval();
	}
	
	void getBestCCL(){}
	
	void getBestInvertCCL(){}

	public static List<Stock> getStocks() {
		return stocks;
	}

	public List<Commodity> getCommodities() {
		return commodities;
	}

	public List<Futuro> getFuturos() {
		return futuros;
	}

	public List<Bono> getBonos() {
		return bonos;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	public void setFuturos(List<Futuro> futuros) {
		this.futuros = futuros;
	}

	public void setBonos(List<Bono> bonos) {
		this.bonos = bonos;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}
}
