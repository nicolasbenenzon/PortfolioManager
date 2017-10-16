package PortfolioManager;

/**
 * Thrown when a specific action causes a negative value of an asset to exist (or a nil value, in case
 * this shouldn't happen in the action involved)
 * 
 * @author Tomas Ferrer
 *
 */
@SuppressWarnings("serial")
public class NegativeAssetAmountException extends RuntimeException {
	
	public NegativeAssetAmountException() {
		super("Can't handle a negative amount of an asset.");
	}

}
