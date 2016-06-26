package com.nookcasa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import com.nookcasa.interfaces.Filter;
import com.nookcasa.interfaces.KeyedItem;
import com.nookcasa.interfaces.Listener;
import com.nookcasa.interfaces.MappedCacheService;

/* This is a simple caching class which assumes that there will be multiple objects which 
 * would be mapped to the same key.
 * It also provides a filtering search as well as subscription mechanism. 
 * 
 */

public class MappedCacheServiceImpl<I extends KeyedItem> implements MappedCacheService<I> {

	private ConcurrentMap<String, List<I>> itemMap = new ConcurrentHashMap<String, List<I>>();
	private ConcurrentMap<String, Listener<I>> listeners = new ConcurrentHashMap<String, Listener<I>>();

	public void add(I item) {
		List<I> newItemList = new ArrayList<I>();
		newItemList.add(item);
		List<I> oldItemList = itemMap.putIfAbsent(item.getKey(), newItemList);
		if (null != oldItemList) {
			oldItemList.add(item);
		}
		Listener<I> listener = listeners.get(item.getKey());
		if (null != listener) {
			listener.publish(item);
		}
	}

	public List<I> get(String key) {
		return itemMap.get(key);
	}

	// Here subscription is per key level rather than per value level.
	public void subscribe(String key, Listener<I> listener) {
		listeners.putIfAbsent(key, listener);
	}

	// For now, we don't subscribe per bid/offer so we never unsubscribe.
	public void unSubscribe(String key) {
		listeners.remove(key);
	}

	public void remove(I item) {
		List<I> currentItemList = null;
		List<I> newItemList = null;
		/*
		 * Here the replace function will only return true if the oldItemList is
		 * same when it started the do-while loop, else it retries. This ensures
		 * concurrency and need to avoid locks.
		 */
		do {
			currentItemList = itemMap.get(item.getKey());
			if (currentItemList != null) {
				newItemList = currentItemList;
				newItemList.remove(item);
				// Although the below code can have be executed by multiple
				// threads at the same time, the outcome will not cause any
				// unexpected behaviour as the remove key,val will only remove
				// if the value doesn't change while a thread is doing this
				// operation.
				if (newItemList.size() == 0) {
					if (itemMap.remove(item.getKey(), currentItemList)) {
						break;
					} else {
						continue;
					}
				}
			}
		} while (!itemMap.replace(item.getKey(), currentItemList, newItemList));

	}

	public List<I> findAll(final Filter<I> filter) {
		List<I> itemList = new ArrayList<I>();
		// The below code converts all the values of the map(which are List of
		// List) into a single list and then tries to match these with the given
		// filter criteria.
		itemList = itemMap.values().stream().flatMap(l -> l.stream()).filter((t) -> filter.passes(t))
				.collect(Collectors.toList());

		return itemList;
	}

}
