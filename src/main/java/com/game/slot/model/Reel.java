package com.game.slot.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class Reel {

	private List<Integer> symbols;
	
	public Reel() {
		
	}
	
	public Reel(List<Integer> symbols) {
		this.symbols = symbols;
	}

	public List<Integer> spin(int rowNum) {
		int index = RandomUtils.nextInt(0, symbols.size());
		return spin(index, rowNum);
	}
	
	public List<Integer> spin(int index, int rowNum) {
		int length = symbols.size();
		int endIndex = index + rowNum;
		if (endIndex > length) {
			List<Integer> list = new ArrayList<>(symbols.subList(index, length));
			list.addAll(symbols.subList(0, endIndex - length));
			return list;
		}
		return new ArrayList<>(symbols.subList(index, endIndex));
	}
	
	public int getLength() {
		return symbols.size();
	}
	
	public List<Integer> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Integer> symbols) {
		this.symbols = symbols;
	}
	
}
