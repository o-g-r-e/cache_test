package com.my.cachetest;

import java.util.Scanner;

import com.my.twolevelcahce.CahceOverfullException;
import com.my.twolevelcahce.TwoLevelCache;

public class CacheTester {
	private TwoLevelCache<String, String> cache;
	
	public CacheTester(TwoLevelCache<String, String> cache) {
		this.cache = cache;
	}
	
	public void runCommandLineTester() {
		boolean exit = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to cache testing.");
		System.out.println("Type help to display available commands.");
		System.out.println(cache.toString());
		
		while(!exit) {
			System.out.print(">");
			String inputValue = scan.nextLine();
			inputValue = fixInput(inputValue);
			Command com = new Command();
			com.read(inputValue);
			if(validateCommand(com)) {
				if(com.getType() == Type.EXIT) {
					exit = true;
				} else {
					commandProcessor(com, cache);
					System.out.println(cache.toString());
				}
			} else {
				System.out.println("Unknown command.");
			}
		}
	}
	
	private String fixInput(String inputValue) {
		return inputValue.trim().replaceAll("\\s{2,}", " ");
	}
	
	private void commandProcessor(Command com, TwoLevelCache<String, String> cache) {
		switch (com.getType()) {
		case PUT_OBJECT:
			try {
				cache.put(com.getKey(), com.getValue());
			} catch (CahceOverfullException e) {
				System.out.println("Failed to put object. File cache is full!");
			}
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
		case HELP:
			System.out.println(getHelp());
			break;
		default:
			break;
		}
	}
	
	private String getHelp() {
		return "put {key} {value}\nget {key}\nupdate {key} {value}\ndelete {key}";
	}
	
	public TwoLevelCache<String, String> getCache() {
		return cache;
	}
	
	private boolean validateCommand(Command com) {
		if(com.getType() == null) {
			return false;
		}
		
		switch (com.getType()) {
		case PUT_OBJECT:
			if(com.getKey() == null || com.getValue() == null) {
				return false;
			}
			break;
		case GET_OBJECT:
			if(com.getKey() == null) {
				return false;
			}
			break;
		case UPDATE_OBJECT:
			if(com.getKey() == null || com.getValue() == null) {
				return false;
			}
			break;
		case DELETE_OBJECT:
			if(com.getKey() == null) {
				return false;
			}
			break;
		default:
			break;
		}
		
		return true;
	}
	
}
