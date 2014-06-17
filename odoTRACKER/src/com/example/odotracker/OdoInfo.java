package com.example.odotracker;


/*
 * Basic Data class to hold a state name and the state capital.
 */
public class OdoInfo {
	
	private String index;
	private String start;
	private String end;
	private String distance;
	private String date;
	private String notes;
	
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String name) {
		this.index = name;
	}
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String name) {
		this.start = name;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String name) {
		this.end = name;
	}
	
	public String getDistance() {
		return distance;
	}
	
	public void setDistance(String name) {
		this.distance = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String name) {
		this.date = name;
	}
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String name) {
		this.notes = name;
	}
	
	

}
