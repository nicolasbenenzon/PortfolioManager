package PortfolioManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 */

/**
 * @author Nicolas Benenzon
 *
 */
public class Syst {
	
	List<Stock> stocks;
	List<Commodity> commodities;
	List<Futuro> futuros;
	List<Bono> bonos;
	List<Opcion> opciones;

	public Syst(List<Stock> stocks, List<Commodity> commodities, List<Futuro> futuros, List<Bono> bonos,
			List<Opcion> opciones) {
		super();
		this.stocks = stocks;
		this.commodities = commodities;
		this.futuros = futuros;
		this.bonos = bonos;
		this.opciones = opciones;
	}

	void UpdateValuesFromInternet(){
		stocks = new ArrayList<Stock>();
		commodities = new ArrayList<Commodity>();
		futuros = new ArrayList<Futuro>();
		bonos = new ArrayList<Bono>();
		opciones = new ArrayList<Opcion>();
		
		
		
	}
	
	public static void LoadNewsFromInternet(){
		
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
	}
	
	void GetBestCCL(){}
	
	void GetBestInvertCCL(){}

	public List<Stock> getStocks() {
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

