package PortfolioManager;

@SuppressWarnings("serial")
public class Bono extends Asset{
	
	double TIR;
	double parity;
	
	public Bono(double value, double variation, String ticker, double tIR, double parity) {
		super(value, variation, ticker);
		TIR = tIR;
		this.parity = parity;
	}
	
	public Bono(double value, String ticker) {
		super(value, 0, ticker);
		TIR = 0;
		parity = 0;
	}
	
	public Bono() {
		super(0, 0, "");
		TIR = 0;
		parity = 0;
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
