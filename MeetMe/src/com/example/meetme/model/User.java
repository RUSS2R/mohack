package com.example.meetme.model;

import java.sql.Timestamp;

import android.graphics.drawable.Drawable;
import android.location.Location;

public class User {
	int id;
	String timestamp;
	float latitude;
	float longitude;
	
	Location loc;
	String status;
	String name;
	Drawable picture;
	
	public User(int id, String timestamp,String status, String name, Drawable picture) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.status = status;
		this.name = name;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getPicture() {
		return picture;
	}

	public void setPicture(Drawable picture) {
		this.picture = picture;
	}
	
	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
