package com.game.slot.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.game.slot.common.LineRule;
import com.game.slot.common.LineType;
import com.game.slot.model.Coordinate;
import com.game.slot.model.Line;
import com.game.slot.model.PayTable;
import com.game.slot.model.Reel;
import com.game.slot.model.Symbol;

public class SlotConfig {
	
	private int rowNum;
	
	private Map<Integer, Symbol> symbols;
	
	private List<Reel> reels;
	
	private LineType lineType;
	
	private LineRule lineRule;
	
	private List<Line> lines;
	
	private PayTable payTable;
	
	private Set<Coordinate> invalidCoordinates;
	
	private String freeSpin;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public Symbol getSymbol(int symbolId) {
		return this.symbols.get(symbolId);
	}
	
	public void setSymbols(List<Symbol> symbols) {
		this.symbols = new HashMap<>();
		for (Symbol symbol : symbols) {
			this.symbols.put(symbol.getId(), symbol);
		}
	}
	
	public void setLines(List<Integer> lines) {
		this.lines = LineManager.get(this.lineType, lines);
	}
	
	public void setReels(List<List<Integer>> reels) {
		this.reels = new ArrayList<>();
		for (List<Integer> reelSymbols : reels) {
			this.reels.add(new Reel(reelSymbols));
		}
	}
	
	public void setPayTable(Map<Integer, TreeMap<Integer, BigDecimal>> payTable) {
		this.payTable = new PayTable();
		this.payTable.setPayouts(payTable);
	}
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public Map<Integer, Symbol> getSymbols() {
		return symbols;
	}

	public List<Reel> getReels() {
		return reels;
	}

	public List<Line> getLines() {
		return lines;
	}

	public LineType getLineType() {
		return lineType;
	}

	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	
	public LineRule getLineRule() {
		return lineRule;
	}

	public void setLineRule(LineRule lineRule) {
		this.lineRule = lineRule;
	}

	public PayTable getPayTable() {
		return payTable;
	}

	public Set<Coordinate> getInvalidCoordinates() {
		return invalidCoordinates;
	}

	public void setInvalidCoordinates(Set<Coordinate> invalidCoordinates) {
		this.invalidCoordinates = invalidCoordinates;
	}

	public String getFreeSpin() {
		return freeSpin;
	}

	public void setFreeSpin(String freeSpin) {
		this.freeSpin = freeSpin;
	}

}
