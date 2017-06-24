package PortfolioManager;
import java.util.Date;

/**
 * @author Agustin Bossi & Alejandro Santoflaminio
 *
 */

public class Opcion extends Expire{

	String type;
	Asset subyacente;
	double prima;
	
	public Opcion(double value, double min, double max, double open, double close, double variation, int amount,
			String name, String ticker, Date expiration, String type, Asset subyacente, double prima) {
		super(value, min, max, open, close, variation, amount, name, ticker, expiration);
		this.type = type;
		this.subyacente = subyacente;
		this.prima = prima;
	}

}
