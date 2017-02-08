package com.game.slot.model;

import java.util.List;

/**
 * payLine ÏßÐÍ
 * @author Administrator
 *
 */
public class Line implements Comparable<Line> {
	
	private int id;

	private List<Coordinate> coordinates;
	
	public Line() {
		
	}
	
	public Line(int id, List<Coordinate> coordinates) {
		this.id = id;
		this.coordinates = coordinates;
	}
	
	@Override
	public int compareTo(Line o) {
		return this.id - o.id;
	}
	
	public int getLength() {
		return coordinates.size();
	}
	
	@Override
	public String toString() {
		return id + coordinates.toString();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

}
