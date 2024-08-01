package com.homework;

public interface KWHashMap<K, V> {
	
	public V get(Object key);
	public V put(K key, V value);
	public V remove(Object key);
	public void print();
	
}
