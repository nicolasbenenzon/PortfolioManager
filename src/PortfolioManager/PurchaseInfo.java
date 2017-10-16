package PortfolioManager;

import java.io.Serializable;

/**
 * Contains information about a specific asset owned by the user
 * 
 * @author Felipe Gorostiaga & Tomas Ferrer
 *
 */
public class PurchaseInfo implements Serializable{
	/**
	 * Total current amount of money invested in a specific asset
	 */
	private double moneyInvested;
	/**
	 * Total current amount of the owned asset
	 */
	private int assetAmount;
	
	public PurchaseInfo(double moneyInvested, int assetAmount) {
		if(moneyInvested <= 0 || assetAmount <= 0)
			throw new NegativeAssetAmountException();
		this.moneyInvested = moneyInvested;
		this.assetAmount = assetAmount;
	}
	
	public double getMoneyInvested() {
		return moneyInvested;
	}
	public void setMoneyInvested(double moneyInvested) {
		if(moneyInvested <= 0)
			throw new NegativeAssetAmountException();
		this.moneyInvested = moneyInvested;
	}
	public int getAssetAmount() {
		return assetAmount;
	}
	public void setAssetAmount(int assetAmount) {
		if(assetAmount < 0)
			throw new NegativeAssetAmountException();
		this.assetAmount = assetAmount;
	}
}