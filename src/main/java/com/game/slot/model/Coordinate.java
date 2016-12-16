package com.game.slot.model;

public class Coordinate implements Comparable<Coordinate> {

	private int x;
	
	private int y;
	
	public Coordinate() {
		
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Coordinate o) {
		return this.y != o.y ? o.y - this.y : this.x - o.x;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
		if (obj instanceof Coordinate) {
			Coordinate other = (Coordinate)obj;
			return this.x == other.x && this.y == other.y;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
	
	@Override
	public int hashCode() {
		return (y << 3) + x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
