package PortfolioManager;

import java.io.Serializable;

public class Asset implements Serializable{

	private static final long serialVersionUID = -3130240257539577230L;
	double value;
	double variation;
	String ticker;
	
	public Asset(double value, double variation, String ticker) {
		super();
		this.value = value;
		this.variation = variation;
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
	public String toString() {
		return this.getTicker();
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
	
	public double getValue() {
		return value;
	}
	public double getVariation() {
		return variation;
	}
	public String getTicker() {
		return ticker;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public void setVariation(double variation) {
		this.variation = variation;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
}
