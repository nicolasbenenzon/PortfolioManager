package PortfolioManager;

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
	
	private double getAllAcquiredValues() { //updated
		double sum = 0;
		for(Asset asset : getHoldings().keySet()) {
			sum += getHoldings().get(asset).getMoneyInvested();
		}
		return sum;
	}
	
	private void operate(Asset asset, int amount, double price) {
		
		PurchaseInfo info = holdings.get(asset);
		if(info.getAssetAmount() + amount == 0)
			holdings.remove(asset);
		else {
			info.setMoneyInvested(info.getMoneyInvested() + (amount * price));
			info.setAssetAmount(info.getAssetAmount() + amount);
		}
	}
	/**
	 * Adds an operation to the current Portfolio. If the user already had acquired a certain amount of
	 * a specific asset and wishes to buy more, these should be merged.
	 * 
	 * @param operation Operation to be added.
	 * @throws Exception 
	 */
	public void addOperation(Operation operation) throws Exception {
		
		if(operation.isBuyingOperation()) {
			double amount = operation.getPurchaseAmount() * operation.getPurchaseValue();
			if(amount > getCash() )
				throw new Exception("Insuficient funds");
			else
				operate(operation.getAsset(), operation.getPurchaseAmount(), operation.getPurchaseValue());
		}
		else {
			if(holdings.get(operation.getAsset()).getAssetAmount() < operation.getPurchaseAmount() )
				throw new Exception("Insuficient assets to sell");
			else
				operate(operation.getAsset(), -1 * operation.getPurchaseAmount(), operation.getPurchaseValue());
				
		}
		
		history.add(operation);
		
		calcNetWorth();
		calcGains();
		calcReturns();
		/* writeOperationInHistoryFile(operation); este metodo no se usaria ya que cuando se crea la operation se guarda instantaneamente 
		 * en el archivo de historias. Para esto se tiene que validar antes de la creacion que la operation se realizable, es decir que si es 
		 * de compra se tenga la $$ necesaria y si es de venta se tengan la cantidad de Assets disponibles para dicha venta.
		 */
	}

}
