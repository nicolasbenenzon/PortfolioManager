package PortfolioManager;

/**
 * Thrown when the user does not have the necessary funds to acquire an asset
 * 
 * @author Tomas Ferrer
 *
 */

@SuppressWarnings("serial")
public class InsufficientFundsException extends RuntimeException {
	
	public InsufficientFundsException() {
		super("You don't have enough funds to buy this asset.");
	}

}
