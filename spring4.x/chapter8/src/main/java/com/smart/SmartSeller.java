package com.smart;

public class SmartSeller implements Seller {

	public int sell(String goods,String clientName) {
		System.out.println("SmartSeller: sell "+goods +" to "+clientName+"...");
		return 100;
	}
	
	public void checkBill(int billId){
		if (billId == 1) {
			System.out.println("throw IllegalArgumentException");
			throw new IllegalArgumentException("IllegalArgumentException Exception");
		}
		else {
			System.out.println("throw RuntimeException");
			throw new RuntimeException("re Exception");
		}
	}
}
