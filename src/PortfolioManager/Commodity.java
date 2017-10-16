package PortfolioManager;

/**
 * @author Agustin Bossi & Alejandro Santoflaminio
 *
 */

public class Commodity extends Asset {

	public Commodity(double value, double variation, String ticker) {
		super(value, variation, ticker);
	}
	
	public Commodity(double value, String ticker) {
		super(value, 0, ticker);
	}
	
	public Commodity() {
		super(0, 0, "");
	}
}

