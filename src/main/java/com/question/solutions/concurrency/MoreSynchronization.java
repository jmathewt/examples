 package com.question.solutions.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoreSynchronization {
	
	public static void main(String[] args) {
		
		Printer printer = new Printer();
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for(int i=0; i<10; i++) {
			printer.setOrder(i);
			printer.setRandomNumber(new Random().nextInt(11));
			printer.setTimeSent(System.currentTimeMillis());
			executor.submit(printer);
		}
		
		executor.shutdown();
	}

}

class Printer implements Callable<Boolean>{
	
	private long timeSent;
	
	private int randomNumber;
	
	private int order;

	public long getTimeSent() {
		return timeSent;
	}  

	public synchronized void setTimeSent(long timeSent) {
		this.timeSent = timeSent;
	}

	public int getRandomNumber() {
		return randomNumber;
	}

	public synchronized void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public int getOrder() {
		return order;
	}

	public synchronized void setOrder(int order) {
		this.order = order;
	}

	@Override
	public Boolean call() throws Exception {
		displayResults(Thread.currentThread().getName(), getRandomNumber(), getOrder());
		return true;
	}
	
	private void displayResults(String threadName, int randomNum, int order) {
		System.out.println("Running - " + threadName + ", order - " + order + ", randomNumber - " + randomNum);
	}
	
}
