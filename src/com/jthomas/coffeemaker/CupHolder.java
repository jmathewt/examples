package com.jthomas.coffeemaker;

public class CupHolder {
	
	public int maxCupLimit() {
		return 13;
	}
	
	public boolean isThereCup() {
		return true;
	}
	
	public void getWaterForCoffeeCup(boolean isDone) {
		if(isDone)
			System.out.println("Your cup of coffee is ready");
	}

}
