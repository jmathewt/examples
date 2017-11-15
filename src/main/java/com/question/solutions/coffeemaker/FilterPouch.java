package com.question.solutions.coffeemaker;

public class FilterPouch{
	
	public int getMaxPouchLimit() {
		return 3;
	}
	
	public boolean hasFilterAndCofeePowder() {
		System.out.println("Filter and coffee powder available");
		return true;
	}
	
	public void sendWaterToCupHolder(int limit, boolean isDone) throws Exception {
		if(limit > getMaxPouchLimit())
			throw new Exception("Excess water passed through filterPouch");
		new CupHolder().getWaterForCoffeeCup(isDone);	
	}

}
