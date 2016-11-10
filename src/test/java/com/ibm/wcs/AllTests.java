package com.ibm.wcs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hdfc.loan.*;

@RunWith(Suite.class)
@SuiteClasses({CalculatorServiceTest.class,ShoppingCartServiceTest.class})
public class AllTests {

}
