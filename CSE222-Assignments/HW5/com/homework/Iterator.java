package com.homework;

public interface Iterator<K> {
	
	public boolean hasNext();
	public K next();
	public K prev();
	public K getKey();
	
}
