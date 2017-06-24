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
	/**
	 * Amount of the asset to be purchased/sold
	 */
	private final int purchaseAmount;
	/**
	 * Value of a single unit of the asset
	 */
	private final double purchaseValue;
	
	//Asegurar que purchaseAmount sea positivo
	
	public Operation(boolean isBuyingOperation, Asset asset, Date date, int purchaseAmount){
		
		if(purchaseAmount < 0)
			throw new NegativeAssetAmountException();
		this.isBuyingOperation = isBuyingOperation;
		this.purchaseValue = asset.getValue();
		this.purchaseAmount = purchaseAmount;
		this.date = date;
		this.asset = asset;
		
		writeOperationInHistoryFile(this);
		
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
	
	public void updatePorfolio() {
		// creo que este metodo deberia ir en Portfolio
	}
	
	public void operate(Operation op){
		
	}
	
	void writeOperationInHistoryFile(Operation operation){	//o es un método static, o es de instancia y no recibe parametros
		
		String fileName = "operationHistory.ser";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(operation);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}
	/*Hay lo que decia santi
	 *  de manejar el flujo con 
	 *  try-catch pero es la unica
	 *   manera que encontre
	 *   
	 *   Esto esta bien en archivos, no queda otra (me parece?)
	*/
	public static Collection<Operation> readFromFile() {  
		List<Operation> operationList = new ArrayList<Operation>();
		String fileName = "operationHistory.ser";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(fileName);  
			ois = new ObjectInputStream(fis); 
	    	while(true) {
	    		Operation operation = (Operation) ois.readObject();	
	    		operationList.add(operation);
	    	}
	    } catch (EOFException expectedException) {
	    	  //continue to finally block
	    } catch (ClassNotFoundException cnf) {
	    	  cnf.printStackTrace();
	    } catch (IOException io) {
	    	  io.printStackTrace();
	    } finally {
	    	try {
	    		if( ois != null )
	    			ois.close();
	        } catch (IOException e){
	        	e.printStackTrace();
	        }
	    }
		 return operationList;
	}

	public boolean isBuyingOperation() {
		return isBuyingOperation;
	}
	
}
