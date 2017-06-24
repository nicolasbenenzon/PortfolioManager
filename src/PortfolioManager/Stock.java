package PortfolioManager;

/**
 * @author Agustin Bossi & Alejandro Santoflaminio
 *
 */

public class Stock extends Asset{

	public Stock(double value, double min, double max, double open, double close, double variation, int amount,
			String name, String ticker) {
		super(value, min, max, open, close, variation, amount, name, ticker);
	}

}
