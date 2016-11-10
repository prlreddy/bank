package com.hdfc.loan;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.junit.runners.*;


@RunWith(JUnit4.class)
public class CalculatorServiceTest{

	CalculatorService calculatorService = null;

	@Before
	public void init(){
		calculatorService = new CalculatorService();
	}

	@After
	public void clean(){
		calculatorService = null;
	}


	@Test
	public void testMultiply(){
		assertEquals(250,calculatorService.multiply(50,5));
	}
	
	@Test
	public void testDiff(){
		assertEquals(45,calculatorService.diff(50,5));
	}

	@Test
	public void testNegativeDiff(){
		assertEquals(55,calculatorService.diff(50,-5));
	}

	@Test
	public void testTwoNegativeDiff(){
		assertEquals(-45,calculatorService.diff(-50,-5));
	}


	@Test
	public void testSum(){
		int rs = calculatorService.sum(10,5);
		assertEquals(15,rs);
	}

	@Test
	public void testNegativeSum(){
		int rs = calculatorService.sum(10,-5);
		assertEquals(5,rs);
	}


	@Test
	public void testTwoNegativeSum(){
		int rs = calculatorService.sum(-10,-5);
		assertEquals(-15,rs);
	}	
	@Test
	public void testDivide(){
		int rs = calculatorService.divide(10,5);
		assertEquals(2,rs);
	}
	
	@Test
	public void testBigNumber(){
		int rs = calculatorService.bigNumber(10,5);
		assertEquals(10,rs);
	}


}