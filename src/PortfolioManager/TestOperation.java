package PortfolioManager;

import static org.junit.Assert.*;

/**
 * Tests the method isBuyingOperation() of the Operation class.
 * 
 * @author Alejandro Santoflaminio
 *
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestOperation {
	
	private Operation o;
	private Asset a;

	@Before
	public void setUp() throws Exception {
		
		a= new Bono(5, 0, 0, 0, 0, 0, 4, "Prueba", "PRB", 0, 0);
		o= new Operation(true,a, null, 10);

	}


	@Test
	public void testIsBuyingOperation() {

		assertTrue( o.isBuyingOperation());
	}

}
