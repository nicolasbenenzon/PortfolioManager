package PortfolioManager;
import java.util.Date;

/**
 * @author Agustin Bossi & Alejandro Santoflaminio
 *
 */

public class Opcion extends Asset{

	String type;
	Asset subyacente;
	double prima;
	
	public Opcion(double value, double variation, String ticker/*, Date expiration*/, String type, Asset subyacente, double prima) {
		super(value, variation, ticker/*, expiration*/);
		this.type = type;
		this.subyacente = subyacente;
		this.prima = prima;
	}
	
	public Opcion(double value, String ticker) {
		super(value, 0, ticker);
		this.type = "";
		this.subyacente = null;
		this.prima = 0;
	}
	
	public Opcion() {
		super(0, 0, "");
		this.type = "";
		this.subyacente = null;
		this.prima = 0;
	}

}
