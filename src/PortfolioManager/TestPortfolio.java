package PortfolioManager;

import static org.junit.Assert.*;
/**
 * Tests the most important methods of the Portfolio class.
 * 
 * Some methods have a time limit to check if they require too much time. 
 * 
 * @author Alejandro Santoflaminio
 *
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPortfolio {

	private Operation op;
	private Operation op2;
	private Asset a1;
	private Portfolio port;
	
	@Before
	public void setUp() throws Exception {
		
		a1= new Bono(5, 0, 0, 0, 0, 0, 4, "Prueba", "PRB", 0, 0);
		
		op= new Operation(true,a1, null, 10);
		op2=new Operation(true, a1,null, 20);
		port=new Portfolio();
		port.setCash(150000);
		port.addOperation(op);
		port.addOperation(op2);
		
	}



	@Test
	public void testAddOperation() {
		

		
		/*
		 * The test checks if cash is discounted properly. 
		 * The size of the collections created in Portfolio is also checked.
		 */
		
		assertEquals((150000-op.getPurchaseValue()*10-op2.getPurchaseValue()*20), port.getCash(), 0.1);
		assertEquals(2,port.readOperationListFromFile().size()); 
		assertEquals(1,port.getHoldings().size());
		
	}

	@Test(timeout=1000)
	public void testReadOperationListFromFile() {

		assertEquals(4 , port.readOperationListFromFile().size());
		
	}

}
