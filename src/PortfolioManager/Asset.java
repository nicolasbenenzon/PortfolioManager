package PortfolioManager;

import java.io.Serializable;
/**
 * @author Agustin Bossi & Alejandro Santoflaminio
 *
 */

public abstract class Asset implements Serializable{

	
	private static final long serialVersionUID = -3130240257539577230L;
	double value;
	double min;
	double max;
	double open;
	double close;
	double variation;
	int amount;
	String name;
	String ticker;
	
	public Asset(double value, double min, double max, double open, double close, double variation, int amount,
			String name, String ticker) {
		super();
		this.value = value;
		this.min = min;
		this.max = max;
		this.open = open;
		this.close = close;
		this.variation = variation;
		this.amount = amount;
		this.name = name;
		this.ticker = ticker;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.getTicker();
	}
	
	
	public double getValue() {
		return value;
	}
	public double getMin() {
		return min;
	}
	public double getMax() {
		return max;
	}
	public double getOpen() {
		return open;
	}
	public double getClose() {
		return close;
	}
	public double getVariation() {
		return variation;
	}
	public int getAmount() {
		return amount;
	}
	public String getName() {
		return name;
	}
	public String getTicker() {
		return ticker;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public void setVariation(double variation) {
		this.variation = variation;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
}
