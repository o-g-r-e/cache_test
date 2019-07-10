package com.my.cachetest;

import com.my.twolevelcahce.CahceOverfullException;
import com.my.twolevelcahce.CallsFrequensyStrategy;
import com.my.twolevelcahce.Strategy;
import com.my.twolevelcahce.TwoLevelCache;
import com.my.twolevelcahce.UpdateTimeStrategy;



public class Main {
	
	public static void main(String[] args) throws CahceOverfullException {
		
		if(args.length < 4) {
			System.out.println("Parameters is not set");
			return;
		}
		
		String fileCacheDirectory = args[0];
		
		int memoryCacheSize = Integer.parseInt(args[1]);
		int fileCacheSize = Integer.parseInt(args[2]);
		String strategyCommand = args[3];
		
		Strategy strategy = parseStrategy(strategyCommand);
		
		TwoLevelCache<String, String> twoLevelCache = new TwoLevelCache<String, String>(fileCacheDirectory, memoryCacheSize, fileCacheSize, strategy);
		
		CacheTester cacheTester = new CacheTester(twoLevelCache);
		
		cacheTester.runCommandLineTester();
		
		System.out.println("End!");
	}
	
	private static Strategy parseStrategy(String command) {
		
		StrategyType strategyType = null;
		
		switch (command) {
		case "1":
			strategyType = StrategyType.CALL_FREQUENCY;
			break;
		case "2":
			strategyType = StrategyType.CALL_TIME;
			break;
		}
		
		if(strategyType == null) {
			return null;
		}
		
		switch (strategyType) {
		case CALL_FREQUENCY:
			return new CallsFrequensyStrategy<String, String>();
		case CALL_TIME:
			return new UpdateTimeStrategy<String, String>();
		}
		
		return null;
	}
}
