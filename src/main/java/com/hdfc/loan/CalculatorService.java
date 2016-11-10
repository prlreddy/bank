package com.hdfc.loan;

public class CalculatorService {

	public int sum(int val1,int val2) {
		return val1+val2;
	}

	public int diff(int val1,int val2){
		return val1-val2;
	}

	public int multiply(int val1,int val2){
		return val1*val2;
	}
	public int divide(int val1,int val2){
		return val1/val2;
	}

	public int bigNumber(int val1,int val2){
		if(val1<val2)
			return val2;
		else 
			return val1;
	}



}