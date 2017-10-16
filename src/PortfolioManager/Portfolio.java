package PortfolioManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Class that handles those assets acquired by the user. Calculates the user's net worth, and overall
 * gains (in $) and returns (in %).
 * 
 * @author Tomas Ferrer & Felipe Gorostiaga
 *
 */
public class Portfolio implements Serializable{
	
	private Map<Asset, PurchaseInfo> holdings;
	private double netWorth;
	private double overallGains;
	private double overallReturns;
	private double cash;
	
	public Portfolio() {
		
		holdings = new HashMap<>();
		
		if(getPortfolioFromFile() != null) {
			holdings.putAll(getPortfolioFromFile());
			calcNetWorth();
			calcGains();
			calcReturns();
			setCash(100000 - getAllAcquiredValues());
		}
		else {
			netWorth = overallGains = overallReturns = 0;
			setCash(100000);
		}
	}

	public Map<Asset, PurchaseInfo> getHoldings() {
		return Collections.unmodifiableMap(holdings);
	}
	
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getNetWorth() {
		calcNetWorth();
		return netWorth;
	}

	public double getOverallGains() {
		calcGains();
		return Math.round(overallGains * 100.0) / 100.0;
	}

	public double getOverallReturns() {
		calcReturns();
		return Math.round(overallReturns * 100.0) / 100.0;
	}
	
	private void calcGains() {
		overallGains = getAllCurrentValues() - getAllAcquiredValues();
	}
	
	private void calcReturns() {	
		overallReturns = (( getAllCurrentValues() / getAllAcquiredValues() ) * 100 ) - 100 ;
	}
	
	private void calcNetWorth() {	
		netWorth = getAllCurrentValues() + getCash();
	}
	
	private double getAllCurrentValues() {
		double sum = 0; 
		for(Asset asset : getHoldings().keySet()) { 
			sum += asset.getValue() * getHoldings().get(asset).getAssetAmount();
		}
		return sum;
	}
	
	public double getAllAcquiredValues() {
		double sum = 0;
		for(Asset asset : getHoldings().keySet()) {
			sum += getHoldings().get(asset).getMoneyInvested();
		}
		return sum;
	}
	 
	
	private void operate(Asset asset, int amount, double price) {
		PurchaseInfo info;
		if(!holdings.containsKey(asset)) {
			holdings.put(asset, new PurchaseInfo(amount * price, amount));
		}
		else if(holdings.get(asset).getAssetAmount() + amount == 0) {
			holdings.remove(asset);
		}
		else {
			info = holdings.get(asset);
			info.setMoneyInvested(info.getMoneyInvested() + (amount * price));
			info.setAssetAmount(info.getAssetAmount() + amount);
		}
		setCash(getCash() - amount * price);
	}
	/**
	 * Adds an operation to the current Portfolio. If the user already had acquired a certain amount of
	 * a specific asset and wishes to buy more, these are merged.
	 * 
	 * @param operation Operation to be added.
	 * @throws NegativeAssetAmountException
	 * @throws InsufficientFundsException
	 */
	public void addOperation(Operation operation) throws NegativeAssetAmountException, 
														 InsufficientFundsException {
		
		if(operation.isBuyingOperation()) {
			double amount = operation.getPurchaseAmount() * operation.getPurchaseValue();
			if(amount > getCash() )
				throw new InsufficientFundsException();
			else
				operate(operation.getAsset(), operation.getPurchaseAmount(), operation.getPurchaseValue());
		}
		else {
			if(!holdings.containsKey(operation.getAsset()) 
					|| holdings.get(operation.getAsset()).getAssetAmount() < operation.getPurchaseAmount()){
				throw new NegativeAssetAmountException();
			}
			else
				operate(operation.getAsset(), -1 * operation.getPurchaseAmount(), operation.getPurchaseValue());
				
		}
		writeOperationInFile(operation);
		Operation.setOperationNumber(Operation.getOperationNumber()+1);
		writePortfolioInFile();
		calcNetWorth();
		calcGains();
		calcReturns();
	}
	
	
	public void writeOperationInFile(Operation operation) {
		
		ArrayList<Operation> operationList = new ArrayList<Operation>();
			
		List<Operation> aux = readOperationListFromFile();
		if(aux != null)
			operationList.addAll(aux);
		
		operationList.add(operation);	
		writeFile(operationList);
	
	}
	
	
	public ArrayList<Operation> readOperationListFromFile() {
		
		ArrayList<Operation> operationList=null;
		String fileName = "ListHistory.ser";
	
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			operationList = (ArrayList<Operation>) ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e){
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
	    	  e.printStackTrace();
	    } catch (IOException io) {
	    	  io.printStackTrace();
	    }
		return operationList;
	}
	
	private void writeFile(ArrayList<Operation> list) {
	
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListHistory.ser")));
			oos.writeObject(list);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
	}
	public void writePortfolioInFile() {
		
		String fileName = "Portfolio.ser";
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
			oos.writeObject(this.getHoldings());
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public Map<Asset, PurchaseInfo> getPortfolioFromFile(){
		
		Map<Asset, PurchaseInfo> portfolioMap = null;
		String fileName = "Portfolio.ser";
	
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			portfolioMap =  (Map<Asset, PurchaseInfo>) ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e){
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
	    	  e.printStackTrace();
	    } catch (IOException io) {
	    	  io.printStackTrace();
	    }
		return portfolioMap;	
	}
	
}

