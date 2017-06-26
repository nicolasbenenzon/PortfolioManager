package PortfolioManager;

import static org.junit.Assert.*;
/**
 * Tests the most important methods of the Sys class.
 * 
 * Some methods have a time limit to check if they require too much time. 
 * 
 * @author Alejandro Santoflaminio
 *
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSyst {
	
	private Syst s;


	@Test(timeout=2500)
	public void testUpdate() {
		
		/*
		 * The test how much time it takes to get the values from the internet.
		 * It also checks if the method actually worked and stocks were obtained.
		 */
		
		Syst.updateValuesFromInternet();
		assertTrue(!( Syst.getStocks()==null || Syst.getStocks().isEmpty()));
	}

}
