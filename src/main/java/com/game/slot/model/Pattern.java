package com.game.slot.model;

import java.math.BigDecimal;
import java.util.List;

public class Pattern implements Comparable<Pattern> {

	private Symbol winSymbol;
	
	private int count;
	
	private BigDecimal multiple;
	
	private List<Coordinate> winCoordinates;

	@Override
	public int compareTo(Pattern o) {
		if (o == null) {
			return 1;
		}
		return this.multiple.compareTo(o.multiple);
	}
	
	@Override
	public String toString() {
		return String.format("(%s) * %d = %f", winSymbol.toString(), count, multiple.doubleValue());
	}
	
	public Symbol getWinSymbol() {
		return winSymbol;
	}

	public void setWinSymbol(Symbol winSymbol) {
		this.winSymbol = winSymbol;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getMultiple() {
		return multiple;
	}

	public void setMultiple(BigDecimal multiple) {
		this.multiple = multiple;
	}

	public List<Coordinate> getWinCoordinates() {
		return winCoordinates;
	}

	public void setWinCoordinates(List<Coordinate> winCoordinates) {
		this.winCoordinates = winCoordinates;
	}
}
