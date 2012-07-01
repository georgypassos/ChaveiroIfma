package util;

//Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 01/07/2012 12:58:36
//Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   SelectItem.java

public class SelectItem {

	public SelectItem(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object value) {
		key = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object label) {
		value = label;
	}

	private Object key;
	private Object value;
}
