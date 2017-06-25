package PortfolioManager;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Class that handles those assets acquired by the user. Calculates the user's net worth, and overall
 * gains (in $) and returns (in %).
 * 
 * @author Tomas Ferrer & Felipe Gorostiaga
 *
 */
public class Portfolio {
	
	private Map<Asset, PurchaseInfo> holdings;
	private List<Operation> history;
	private double netWorth;
	private double overallGains;
	private double overallReturns;
	private double cash;
	
	public Portfolio() {
		holdings = new HashMap<>();
		history = new LinkedList<>();
		netWorth = overallGains = overallReturns = 0;
	}

	public Map<Asset, PurchaseInfo> getHoldings() {
		return Collections.unmodifiableMap(holdings);
	}

	public List<Operation> getHistory() { 
		return Collections.unmodifiableList(history);
	}
	
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getNetWorth() {
		calcNetWorth(); //estaria bueno hacer esto solo si pasaron 5min desde la ultima actualizacion
		return netWorth;
	}

	public double getOverallGains() {
		calcGains(); //estaria bueno hacer esto solo si pasaron 5min desde la ultima actualizacion
		return overallGains;
	}

	public double getOverallReturns() {
		calcReturns(); //estaria bueno hacer esto solo si pasaron 5min desde la ultima actualizacion
		return overallReturns;
	}
	
	private void calcGains() {
		overallGains = getAllCurrentValues() - getAllAcquiredValues();
	}
	
	private void calcReturns() {	
		overallReturns = (( getAllCurrentValues() / getAllAcquiredValues() ) * 100 ) - 100 ;
	}
	
	private void calcNetWorth() {	
		netWorth = getAllCurrentValues();
	}
	
	private double getAllCurrentValues() { //updated
		double sum = 0; 
		
		for(Asset asset : getHoldings().keySet()) {
			sum += asset.getValue() * getHoldings().get(asset).getAssetAmount();
		}
		return sum;
	}
	
	public double getAllAcquiredValues() { //updated
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
		else { System.out.println("hola");
			if(!holdings.containsKey(operation.getAsset()) 
					|| holdings.get(operation.getAsset()).getAssetAmount() < operation.getPurchaseAmount()){
				throw new NegativeAssetAmountException();
			}
			else
				operate(operation.getAsset(), -1 * operation.getPurchaseAmount(), operation.getPurchaseValue());
				
		}
		history.add(operation);
		calcNetWorth();
		calcGains();
		calcReturns();
		/* 
		 * writeOperationInHistoryFile(operation); este metodo no se usaria ya que cuando se crea la operation se guarda instantaneamente 
		 * en el archivo de historias. Para esto se tiene que validar antes de la creacion que la operation se realizable, es decir que si es 
		 * de compra se tenga la $$ necesaria y si es de venta se tengan la cantidad de Assets disponibles para dicha venta.
		 */
	}
	
	public Object[][] toArray() {
		Object[][] tableData = new Object[getHoldings().keySet().size()][3];
		int index = 0;
		for(Asset asset : getHoldings().keySet()) {
			tableData[index][0] = asset.getTicker();
			tableData[index][1] = getHoldings().get(asset).getAssetAmount();
			tableData[index][2] = "$" + getHoldings().get(asset).getMoneyInvested();
			index++;
		}
		return tableData;
	}
	
	public void writeOperationListInFile(){
		
		String fileName = "ListHistory.ser";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
	
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.getHistory());
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}
	
	public List<Operation> readOperationListFromFile(){
		
		List<Operation> operationList=null;
		String fileName = "ListHistory.ser";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			
			fis = new FileInputStream(fileName);  
			ois = new ObjectInputStream(fis); 
			operationList = (List<Operation>) ois.readObject();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
	    	  e.printStackTrace();
	    } catch (IOException io) {
	    	  io.printStackTrace();
	    }
		
		return operationList;

	}
	
}

