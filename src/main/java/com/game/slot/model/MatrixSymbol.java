package com.game.slot.model;

import com.game.slot.common.SymbolType;

/**
 * ¾ØÕóÖÐµÄsymbol
 * @author Administrator
 *
 */
public class MatrixSymbol {

	private Coordinate coordinate;
	
	private Symbol symbol;

	public MatrixSymbol(Coordinate coordinate, Symbol symbol) {
		this.coordinate = coordinate;
		this.symbol = symbol;
	}
	
	public int getId() {
		return symbol.getId();
	}
	
	public SymbolType getType() {
		return symbol.getType();
	}
	
	public int getSettleId() {
		return symbol.getSettleId();
	}
	
	public int getX() {
		return coordinate.getX();
	}
	
	public int getY() {
		return coordinate.getY();
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
