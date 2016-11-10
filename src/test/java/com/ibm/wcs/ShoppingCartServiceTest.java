package com.ibm.wcs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ibm.wcs.exception.ProductNotInCartException;
import com.ibm.wcs.model.Product;

@RunWith(JUnit4.class)
public class ShoppingCartServiceTest {
	
	ShoppingCartService shoppingCartService = null;
	
	@Before
	public void init(){
		shoppingCartService = new ShoppingCartService();
	}

	@After
	public void clean(){
		shoppingCartService = null;
	}
	
	@Test(timeout=50)
	public void testPlaceOrderForSLA(){
		Product watch = new Product(100, "Watch", 452300.00, 1);
		Product laptop = new Product(200, "Laptop", 1200.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);
		
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(desktop);
		shoppingCartService.addItem(desktop);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		
		shoppingCartService.placeOrder();
		
	}
	
	
	@Test
	public void testPlaceOrder(){
		Product watch = new Product(100, "Watch", 452300.00, 1);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
			
		assertNotNull(shoppingCartService.placeOrder());
		assertTrue(shoppingCartService.placeOrder().startsWith("O"));
	}
	
	@Test
	public void testCartDetails(){
		Product watch = new Product(100, "Watch", 52000.00, 1);
		Product laptop = new Product(200, "Laptop", 1200.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);
		
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(desktop);
		shoppingCartService.addItem(desktop);
			
		
		assertNotNull(shoppingCartService.cartDetails());
		assertTrue(shoppingCartService.countItems() > 0);
		assertTrue(shoppingCartService.cartDetails().contains(laptop));
	}
	
	@Test
	public void testEmptyCart(){
		Product watch = new Product(100, "Watch", 52000.00, 1);
		Product laptop = new Product(200, "Laptop", 1200.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);
		
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(desktop);
		
		shoppingCartService.emptyCart();
		
		assertEquals(0,shoppingCartService.countItems());
	}
	
	@Test
	public void testTotalPrice(){
		Product watch = new Product(100, "Watch", 52000.00, 1);
		Product laptop = new Product(200, "Laptop", 1200.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);
		
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(desktop);
		
		assertEquals(128900.00,shoppingCartService.totalPrice(),2);
	}
	
	@Test(expected=ProductNotInCartException.class)
	public void testRemoveItemForException() throws ProductNotInCartException{
		Product watch = new Product(100, "Watch", 452300.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);

		shoppingCartService.addItem(watch);
		shoppingCartService.removeItem(desktop);
	}
	
	@Test
	public void testRemoveItem(){
		Product watch = new Product(100, "Watch", 452300.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);

		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(desktop);
			
		try {
			shoppingCartService.removeItem(watch);
			assertEquals(1,shoppingCartService.countItems());

		} catch (ProductNotInCartException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testAddItem(){
		Product watch = new Product(100, "Watch", 452300.00, 1);
		shoppingCartService.addItem(watch);
		assertEquals(1,shoppingCartService.countItems());
	}
	
	@Test
	public void testAddDuplicateItem(){
		Product watch = new Product(100, "Watch", 452300.00, 1);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(watch);
		assertEquals(3,shoppingCartService.countItems());
	}
	

	@Test
	public void testAddFiveItem(){
		
		Product watch = new Product(100, "Watch", 452300.00, 1);
		Product laptop = new Product(200, "Laptop", 1200.00, 1);
		Product radio = new Product(300, "Radio", 2300.00, 1);
		Product mobile = new Product(400, "Mobile", 90300.00, 1);
		Product desktop = new Product(500, "Desktop", 21300.00, 1);
		
		shoppingCartService.addItem(watch);
		shoppingCartService.addItem(laptop);
		shoppingCartService.addItem(radio);
		shoppingCartService.addItem(mobile);
		shoppingCartService.addItem(desktop);
		
		assertEquals(5,shoppingCartService.countItems());
	}

}
