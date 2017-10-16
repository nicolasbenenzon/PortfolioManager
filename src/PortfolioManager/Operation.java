package PortfolioManager;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/*Operations are saved to a file named operationHistory.ser */
/**
 * Class for managing Operation objects. Includes functionality to export these to a
 * file (named operationHistory.ser). 
 * 
 * @author Felipe Gorostiaga & Tomas Ferrer
 * 
 */
public class Operation implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * isBuyingOperation is set to true if this is a buying operation, and set to false if this is a selling operation.
	 */
	private final boolean isBuyingOperation;
	private final Asset asset;
	private final Date date;
	private static int operationNumber = 0;
	/**
	 * Amount of the asset to be purchased/sold
	 */
	private final int purchaseAmount;
	/**
	 * Value of a single unit of the asset
	 */
	private final double purchaseValue;
	
	public Operation(boolean isBuyingOperation, Asset asset, Date date, int purchaseAmount){
		
		if(purchaseAmount < 0)
			throw new NegativeAssetAmountException();
		this.isBuyingOperation = isBuyingOperation;
		this.purchaseValue = asset.getValue();
		this.purchaseAmount = purchaseAmount;
		this.date = date;
		this.asset = asset;
		
		
	}
	
	public double getPurchaseValue() {
		return purchaseValue;
	}
	
	public int getPurchaseAmount() {
		return purchaseAmount;
	}
	
	public Asset getAsset() {
		return asset;
	}
	
	public Date getDate() {
		return date;
	}
	
	public static int getOperationNumber(){
		return operationNumber;
	}
	
	public static void setOperationNumber(int n){
		operationNumber=n;
	}
	
	public boolean isBuyingOperation() {
		return isBuyingOperation;
	}
	
}
