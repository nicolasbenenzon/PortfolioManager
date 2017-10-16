package PortfolioManager;

/**
 * @author Agustin Bossi & Alejandro Santoflaminio
 *
 */

public class Futuro extends Asset {

	public Futuro(double value, double variation, String ticker) {
		super(value, variation, ticker);
	}
	
	public Futuro(double value, String ticker) {
		super(value, 0, ticker);
	}
	
	public Futuro() {
		super(0, 0, "");
	}

}
