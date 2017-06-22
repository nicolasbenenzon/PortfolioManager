package PortfolioManager;
import java.util.Date;

public class Expire extends Asset{
	Date expiration;

	public Expire(double value, double min, double max, double open, double close, double variation, int amount,
			String name, String ticker, Date expiration) {
		super(value, min, max, open, close, variation, amount, name, ticker);
		this.expiration = expiration;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}