package com.example.apprute.model;

import com.google.gson.annotations.SerializedName;

public class TouristAttraction {

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getLocation(){
		return location;
	}

	public int getId(){
		return id;
	}
}