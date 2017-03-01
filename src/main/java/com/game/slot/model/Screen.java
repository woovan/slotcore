package com.game.slot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/**
 * ÆÁÄ» symbolÕ¹Ê¾Çø
 * @author Administrator
 *
 */
public class Screen {
	
	private static final int SPACE = 5;
	
	private List<Integer> reelIndices;

	private Map<Coordinate, MatrixSymbol> matrix = new TreeMap<>();
	
	public void put(Coordinate c, Symbol symbol) {
		matrix.put(c, new MatrixSymbol(c, symbol));
	}
	
	public MatrixSymbol get(Coordinate c) {
		return matrix.get(c);
	}
	
	public Symbol getSymbol(Coordinate c) {
		return matrix.get(c).getSymbol();
	}
	
	public List<MatrixSymbol> getMatrixSymbols() {
		return new ArrayList<>(matrix.values());
	}
	
	public void print() {
		System.out.println("Screen:");
		int y = 0;
		for (Coordinate c : matrix.keySet()) {
			if (c.getY() < y) {
				System.out.println("|");
			}
			System.out.print(StringUtils.center(getSymbol(c).toString(), SPACE));
			y = c.getY();
		}
		System.out.println("|\r\n");
	}
	
	public void print(Pattern pattern) {
		System.out.println(pattern.toString());
		int y = 0;
		for (Coordinate c : matrix.keySet()) {
			if (c.getY() < y) {
				System.out.println("|");
			}
			String symbol = getSymbol(c).toString();
			
			if (pattern.getSymbols().contains(get(c))) {
				symbol = "(" + symbol + ")";
			} else if (pattern instanceof LinePattern) {
				LinePattern linePattern = (LinePattern)pattern;
				if (linePattern.getLine().getCoordinates().contains(c)) {
					symbol =  "[" + symbol + "]";
				}
			}
			
			System.out.print(StringUtils.center(symbol, SPACE));
			y = c.getY();
		}
		System.out.println("|\r\n");
	}

	public List<Integer> getReelIndices() {
		return reelIndices;
	}

	public void setReelIndices(List<Integer> reelIndices) {
		this.reelIndices = reelIndices;
	}
	
	
}
