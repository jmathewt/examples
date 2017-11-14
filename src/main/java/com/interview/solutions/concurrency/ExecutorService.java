package com.interview.solutions.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorService {
	
	public static void main(String... args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future<Integer>> futures = new ArrayList<>();
		
		for(int i=1; i<=100; i++) {
			Future<Integer> future = executorService.submit(new NumberPrinter(i));
			futures.add(future);
		}
		
		boolean allFuturesComplete = false;
		
		while(!allFuturesComplete) {
			for(Future eachFuture : futures) {
				System.out.println(eachFuture.isDone());
				if(eachFuture.isDone()) {
					System.out.println(eachFuture.get());
					allFuturesComplete = true;
				}else {
					allFuturesComplete = false;
					Thread.sleep(10000);
					break;
				}
			}
		}
		
		executorService.shutdown();
	}

}


class NumberPrinter implements Callable<Integer>{
	
	private int numberToPrint;
	
	public NumberPrinter(int number) {
		this.numberToPrint = number;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("Request to print number - " + numberToPrint);
		Thread.sleep(1000);
		return numberToPrint;
	}
}
