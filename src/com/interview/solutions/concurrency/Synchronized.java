package com.jthomas.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Synchronized {
	
	public static void main(String[] args) {
		ResourceInDemand resourceInDemand = new ResourceInDemand();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i=0; i<5; i++)
			executor.submit(resourceInDemand);
		executor.shutdown();
	}

}

class ResourceInDemand implements Callable<String>{
	
	public synchronized String identifyWhoIsHereDeptA(String identifier) throws InterruptedException {
		System.out.println("Checking thread at Dept A - " + identifier + " at - " + System.currentTimeMillis());
		Thread.sleep(10000);
		System.out.println("Done processing at Dept A - " + identifier + " at - " + System.currentTimeMillis());
		return identifier;
	}
	
	public synchronized String identifyWhoIsHereDeptB(String identifier) throws InterruptedException {
		System.out.println("Checking thread at Dept B - " + identifier + " at - " + System.currentTimeMillis());
		Thread.sleep(10000);
		System.out.println("Done processing at Dept B - " + identifier + " at - " + System.currentTimeMillis());
		return identifier;
	}
	
	public synchronized String identifyWhoIsHereDeptC(String identifier) throws InterruptedException {
		System.out.println("Checking thread at Dept C - " + identifier + " at - " + System.currentTimeMillis());
		Thread.sleep(10000);
		System.out.println("Done processing at Dept C - " + identifier + " at - " + System.currentTimeMillis());
		return identifier;
	}

	@Override
	public String call() throws Exception {
		int departmentNumber = new Random().nextInt(3);
		
		if(departmentNumber == 0) {
			identifyWhoIsHereDeptA(Thread.currentThread().getName());
		}else if (departmentNumber == 1) {
			identifyWhoIsHereDeptB(Thread.currentThread().getName());
		}else
			identifyWhoIsHereDeptC(Thread.currentThread().getName());
		
		return Long.toString(System.currentTimeMillis());
	}
}
