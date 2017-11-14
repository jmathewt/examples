package com.jthomas.coffeemaker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Boiler{
	
	private WaterCan waterCan;
	
	public Boiler(WaterCan waterCan) {
		this.waterCan = waterCan;
	}
	
	public void boilWater() throws InterruptedException {
		System.out.println("Going to start boiling water");
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Boolean> future = executorService.submit(new BoilWater(waterCan));
		while(!future.isDone()) {
			System.out.println("Checking if water is boiled. Status - " + future.isDone());
			Thread.sleep(10000);
		}
		executorService.shutdown();
		System.out.println("Water is boiled");
		waterCan.setWaterBoiled(true);
	}

}

class BoilWater implements Callable<Boolean>{
	
	private WaterCan waterCan;
	
	public BoilWater(WaterCan waterCan) {
		this.waterCan = waterCan;
	}

	@Override
	public Boolean call() throws Exception {
		System.out.println("Boiling water from water can");
		Thread.sleep(60000);
		return true;
	}
	
}
