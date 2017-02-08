package com.game.slot.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.game.slot.common.SymbolType;
import com.game.slot.model.LinePattern;
import com.game.slot.model.Pattern;
import com.game.slot.model.Screen;

public class GameResult {
	
	private BigDecimal bet;
	
	private Screen screen;

	private List<LinePattern> linePatterns;
	
	private Map<SymbolType, List<Pattern>> patterns;
	
	public boolean isWin() {
		return CollectionUtils.isNotEmpty(linePatterns) || MapUtils.isNotEmpty(patterns);
	}
	
	public BigDecimal winCoins() {
		if (CollectionUtils.isNotEmpty(linePatterns)) {
			BigDecimal sumMultiple = linePatterns.stream().map(pattern -> pattern.getTotalMultiple()).reduce(BigDecimal.ZERO, BigDecimal::add);
			return bet.multiply(sumMultiple);
		}
		return BigDecimal.ZERO;
	}
	
	public void print() {
		linePatterns.forEach(pattern -> screen.print(pattern));
		
		patterns.entrySet().forEach(entry -> {
			System.out.print(entry.getKey() + ": ");
			entry.getValue().forEach(pattern -> screen.print(pattern));
		});
		System.out.println("______________________________");
	}
	
	@Override
	public String toString() {
		return linePatterns.toString();
	}

	public BigDecimal getBet() {
		return bet;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public void setBet(BigDecimal bet) {
		this.bet = bet;
	}

	public List<LinePattern> getLinePatterns() {
		return linePatterns;
	}

	public void setLinePatterns(List<LinePattern> linePatterns) {
		this.linePatterns = linePatterns;
	}

	public Map<SymbolType, List<Pattern>> getPatterns() {
		return patterns;
	}

	public void setPatterns(Map<SymbolType, List<Pattern>> patterns) {
		this.patterns = patterns;
	}

}
