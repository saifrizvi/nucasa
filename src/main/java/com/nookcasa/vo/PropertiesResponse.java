package com.nookcasa.vo;

import java.util.List;

import com.nookcasa.vo.Property;

public class PropertiesResponse {

	private final long id;
	private final List<Property> properties;

	public PropertiesResponse(long id, List<Property> properties) {
		this.id = id;
		this.properties = properties;
	}

	public long getId() {
		return id;
	}

	public List<Property> getProperties() {
		return properties;
	}
}
