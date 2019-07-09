package com.my.cachetest;

enum Type {PUT_OBJECT, GET_OBJECT, UPDATE_OBJECT, DELETE_OBJECT, EXIT};

public class Command {
	private Type type;
	private String key;
	private String value;
	
	public void read(String input) {
		String[] chars = input.split(" ");
		if(chars.length < 2) {
			return;
		}
		if(chars[0].startsWith("put")) {
			type = Type.PUT_OBJECT;
		} else if(chars[0].startsWith("get")) {
			type =  Type.GET_OBJECT;
		} else if(chars[0].startsWith("update")) {
			type =  Type.UPDATE_OBJECT;
		} else if(chars[0].startsWith("delete")) {
			type =  Type.DELETE_OBJECT;
		} else if(chars[0].startsWith("exit")) {
			type =  Type.EXIT;
		}
		
		key = chars[1];
		
		if(chars.length > 2) {
			value = chars[2];
		} else {
			value = null;
		}
		
	}

	public Type getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	
}
