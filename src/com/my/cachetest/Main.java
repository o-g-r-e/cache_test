package com.my.cachetest;

import com.my.twolevelcahce.TwoLevelCache;

public class Main {
	
	public static void main(String[] args) {
		
		Settings<String, String> settings = new Settings<String, String>(args);
		
		if(!settings.validate()) {
			System.out.println("Parameters fail!");
			return;
		}
		
		TwoLevelCache<String, String> twoLevelCache = new TwoLevelCache<String, String>(settings.getFileCacheDirectory(), settings.getMemoryCacheSize(), settings.getFileCacheSize(), settings.getStrategy());
		
		CacheTester cacheTester = new CacheTester(twoLevelCache);
		
		cacheTester.runCommandLineTester();
		
		System.out.println("End!");
	}
	
}
