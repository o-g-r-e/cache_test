package com.my.cachetest;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import javax.xml.stream.XMLStreamException;

import com.my.twolevelcahce.Cache;
import com.my.twolevelcahce.CahceOverfullException;
import com.my.twolevelcahce.CallsFrequensyStrategy;
import com.my.twolevelcahce.FileCahce;
import com.my.twolevelcahce.MemoryCahce;
import com.my.twolevelcahce.TwoLevelCache;



public class Main {
	Map<String, Integer> callFrequensyes;
	static FileCahce<String, String> fileCache = new FileCahce<String, String>("d:\\cache");
	static MemoryCahce<String,String> memoryCache = new MemoryCahce<String,String>();
	static TwoLevelCache<String, String> cache = new TwoLevelCache<String, String>(new CallsFrequensyStrategy<>(fileCache, memoryCache, 3, 5));
	
	public static void main(String[] args) throws CahceOverfullException {
		boolean exit = false;
		Scanner scan = new Scanner(System.in);
		System.out.println(printCache(cache));
		
		while(!exit) {
			System.out.print(">");
			String inputValue = scan.nextLine();
			Command com = new Command();
			com.read(inputValue);
			if(com.getType() == null) {
				System.out.println("Unknown command.");
			} else {
				if(com.getType() == Type.EXIT) {
					exit = true;
				} else {
					commandProcessor(com, cache);
					System.out.println(printCache(cache));
				}
			}
		}
		System.out.println("End!");
	}
	
	private static void commandProcessor(Command com, TwoLevelCache<String, String> cache) throws CahceOverfullException {
		switch (com.getType()) {
		case PUT_OBJECT:
			cache.put(com.getKey(), com.getValue());
			break;
		case GET_OBJECT:
			cache.get(com.getKey());
			break;
		case UPDATE_OBJECT:
			cache.update(com.getKey(), com.getValue());
			break;
		case DELETE_OBJECT:
			cache.delete(com.getKey());
			break;
		}
	}
	
	private static String printCache(TwoLevelCache<String, String> cache) {
		StringBuilder result = new StringBuilder();
		result.append("\nMemory cache:\n");
		if(memoryCache.getData().size() <= 0) {
			result.append("[]\n");
		} else {
			for(Map.Entry<String, String> e : memoryCache.getData().entrySet()) {
				result.append("[");
				result.append(e.getKey());
				result.append(", ");
				result.append(e.getValue());
				result.append("]\n");
			}
		}
		
		result.append("\nFile cache:\n");
		if(fileCache.getFilePaths().size() <= 0) {
			result.append("[]\n");
		} else {
			for(Map.Entry<String, String> e : fileCache.getFilePaths().entrySet()) {
				result.append("[");
				result.append(e.getKey());
				result.append(", ");
				result.append(e.getValue());
				result.append("]\n");
			}
		}
		return result.toString();
	}
}
