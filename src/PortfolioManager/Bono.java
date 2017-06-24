package PortfolioManager;

@SuppressWarnings("serial")
public class Bono extends Asset{
	
	double TIR;
	double parity;
	
	public Bono(double value, double min, double max, double open, double close, double variation, int amount,
			String name, String ticker, double tIR, double parity) {
		super(value, min, max, open, close, variation, amount, name, ticker);
		TIR = tIR;
		this.parity = parity;
	}

	public double getTIR() {
		return TIR;
	}

	public double getParity() {
		return parity;
	}

	public void setTIR(double tIR) {
		TIR = tIR;
	}

	public void setParity(double parity) {
		this.parity = parity;
	}
	

}
