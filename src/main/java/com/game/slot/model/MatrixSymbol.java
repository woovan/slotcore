package com.game.slot.model;

public class MatrixSymbol {

	private Coordinate coordinate;
	
	private Symbol symbol;

	public MatrixSymbol(Coordinate coordinate, Symbol symbol) {
		this.coordinate = coordinate;
		this.symbol = symbol;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	
}
