package com.question.solutions.concurrency.coffeemaker;

public class WaterTransporter {
	
	private WaterCan waterCan;
	
	public WaterTransporter(WaterCan waterCan) {
		this.waterCan = waterCan;
	}
	
	public void moveWaterToFilterPouch(int filterPouchLimit, int cupLimit, int numOfTimes) throws Exception {
		// logic to move water to filter pouch
		for(int i=0; i<numOfTimes; i++) {
			System.out.println("Sending water to filter pouch");
			if(numOfTimes - i == 1)
				new FilterPouch().sendWaterToCupHolder(filterPouchLimit, true);
			else
				new FilterPouch().sendWaterToCupHolder(filterPouchLimit, false);
		}
		Thread.sleep(60000);
	}

}
