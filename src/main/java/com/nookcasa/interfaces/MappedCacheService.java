package com.nookcasa.interfaces;

import java.util.List;

public interface MappedCacheService<T> {
	
	public void add(T t);
	
	public List<T> get(String key);
	
	public void subscribe(String key, Listener<T> listener);
	
	public void remove(T t);
	
	public List<T> findAll(Filter<T> filter);

}
