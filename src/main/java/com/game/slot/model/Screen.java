package com.game.slot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Screen {

	private Map<Coordinate, MatrixSymbol> matrix = new TreeMap<>();
	
	public void put(Coordinate c, Symbol symbol) {
		matrix.put(c, new MatrixSymbol(c, symbol));
	}
	
	public Symbol get(Coordinate c) {
		return matrix.get(c).getSymbol();
	}
	
	public List<MatrixSymbol> getMatrixSymbols() {
		return new ArrayList<>(matrix.values());
	}
	
}
