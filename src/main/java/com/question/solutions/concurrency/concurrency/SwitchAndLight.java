package com.question.solutions.concurrency.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SwitchAndLight {
	
	public static void main(String... args) {
		Light light = new Light();
		
		ExecutorService executor = Executors.newFixedThreadPool(6);
		for(int i=0; i<6; i++)
			executor.submit(new Switch(light));
		
		executor.shutdown();
	}

}


class Switch implements Callable<Boolean>{
	
	private Light light;
	
	public Switch(Light light) {
		this.light = light;
	}
	
	public void flipSwitch(String switchName, Light light) throws Exception {
		System.out.println("Flipping the switch by - " + switchName);
		light.changeState(switchName);
		System.out.println("Done by switch - " + switchName);
	}

	@Override
	public Boolean call() throws Exception {
		flipSwitch(Thread.currentThread().getName(), light);
		return true;
	}
}


class Light{
	
	private boolean state;
	
	public synchronized void changeState(String switchName) throws Exception {
		try {
			System.out.println("---------- Received command to change light state from switch - " + switchName);
			if(state) {
				System.out.println(".............Turning off the light");
				state = false;
			}else {
				System.out.println(".............Turning on the light........");
				state = true;
			}
			System.out.println("---------- Done following command of change of state of light of switch - " + switchName);
		}catch(Exception e) {
			throw new Exception("Unable to change state of the light ");
		}
		
	}
}