package com.interview.solutions.coffeemaker;

public class PowerSupply {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Going to make coffee");
		CoffeMakerProcessor coffeeMaker = new CoffeMakerProcessor();
		coffeeMaker.start();
	}
	
	public void stop() {
		System.out.println("Stopping coffee maker");
		System.exit(0);
	}

}
