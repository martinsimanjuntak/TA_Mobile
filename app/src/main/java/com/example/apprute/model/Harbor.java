package com.example.apprute.model;

import com.google.gson.annotations.SerializedName;

public class Harbor {

	@SerializedName("route_id")
	private Object routeId;

	@SerializedName("description")
	private String description;

	@SerializedName("harbor_Name")
	private String harborName;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private int id;

	public void setRouteId(Object routeId){
		this.routeId = routeId;
	}

	public Object getRouteId(){
		return routeId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setHarborName(String harborName){
		this.harborName = harborName;
	}

	public String getHarborName(){
		return harborName;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}