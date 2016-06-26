package com.nookcasa.interfaces;

public interface Filter<T> {
	public boolean passes(T t);
}
