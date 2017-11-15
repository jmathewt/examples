package com.question.solutions.coffeemaker;

public class CoffeMakerProcessor {
	
	
	public void start() throws Exception {
		if(doChecking()) {
			startMakingCoffee();
		}else {
			stopProcess();
		}
	}
	
	private boolean doChecking() {
		if(new WaterCan().minimumAmountOfWater()) {
			if(new FilterPouch().hasFilterAndCofeePowder()) {
				if(new CupHolder().isThereCup()) {
					return true;
				}else {
					sendError("Please put a cup for coffee");
					return false;
				}
			}else {
				sendError("Please check if there is filter and coffee powder");
				return false;
			}
		}else {
			sendError("Not enough water available");
			return false;
		}
	}
	
	private void sendError(String message) {
		new DisplayIssue().displayIssue(message);
	}
	 
	private void stopProcess() {
		new PowerSupply().stop();
	}
	
	private void startMakingCoffee() throws Exception {
		WaterCan waterCan = new WaterCan();
		Boiler boiler = new Boiler(waterCan);
		
		boiler.boilWater();
		
		if(waterCan.isWaterBoiled()) {
			WaterTransporter transporter = new WaterTransporter(waterCan);
			int pouchLimit = new FilterPouch().getMaxPouchLimit();
			if(pouchLimit < new CupHolder().maxCupLimit()) {
				int numOfTimes = new CupHolder().maxCupLimit()/pouchLimit;
				int remainder = new CupHolder().maxCupLimit() % pouchLimit;
				if(remainder > 0)
					numOfTimes++;
				transporter.moveWaterToFilterPouch(pouchLimit, new CupHolder().maxCupLimit(), numOfTimes);
			}else {
				transporter.moveWaterToFilterPouch(pouchLimit, new CupHolder().maxCupLimit(), 1);
			}
		}else {
			sendError("Water couldn't be boiled");
			stopProcess();
		}
	}

}
