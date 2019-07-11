package com.my.cachetest;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.my.twolevelcahce.CallFrequencyStrategy;
import com.my.twolevelcahce.Strategy;
import com.my.twolevelcahce.UpdateTimeStrategy;

public class Settings<KeyType, ValueType extends Serializable> {
	
	private String fileCacheDirectory;
	private int memoryCacheSize;
	private int fileCacheSize;
	private Strategy<KeyType, ValueType> strategy;
	
	public Settings(String[] args) {
		parseArgs(args);
	}
	
	private void parseArgs(String[] args) {
		if(args.length < 4) {
			return;
		}
		
		this.fileCacheDirectory = args[0];
		
		this.memoryCacheSize = Integer.parseInt(args[1]);
		this.fileCacheSize = Integer.parseInt(args[2]);
		
		this.strategy = parseStrategy(args[3]);
	}
	
	private Strategy<KeyType, ValueType> parseStrategy(String command) {
		switch (command) {
		case "1":
			return new CallFrequencyStrategy<KeyType, ValueType>();
		case "2":
			return new UpdateTimeStrategy<KeyType, ValueType>();
		}
		
		return null;
	}
	
	public boolean validate() {
		return fileCacheDirectory != null && validateDirectoryPath(fileCacheDirectory) && memoryCacheSize > 0 && fileCacheSize > 0 && strategy != null;
	}
	
	private boolean validateDirectoryPath(String value) {
		Pattern p = Pattern.compile("^[a-zA-Z]:\\\\[\\w\\-\\\\]*");
		Matcher m = p.matcher(value);
		
		return m.find();
	}

	public String getFileCacheDirectory() {
		return fileCacheDirectory;
	}

	public int getMemoryCacheSize() {
		return memoryCacheSize;
	}

	public int getFileCacheSize() {
		return fileCacheSize;
	}

	public Strategy<KeyType, ValueType> getStrategy() {
		return strategy;
	}
}
