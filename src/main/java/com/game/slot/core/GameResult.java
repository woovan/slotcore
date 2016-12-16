package com.game.slot.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.game.slot.common.SymbolType;
import com.game.slot.model.LinePattern;
import com.game.slot.model.Pattern;

public class GameResult {
	
	private BigDecimal bet;

	private List<LinePattern> linePatterns;
	
	private Map<SymbolType, List<Pattern>> patterns;
	
	public boolean isWin() {
		return CollectionUtils.isNotEmpty(linePatterns) || MapUtils.isNotEmpty(patterns);
	}
	
	public BigDecimal winCoins() {
		if (CollectionUtils.isNotEmpty(linePatterns)) {
			BigDecimal sumMultiple = linePatterns.stream().map(pattern -> pattern.getMultiple()).reduce(BigDecimal.ZERO, BigDecimal::add);
			return bet.multiply(sumMultiple);
		}
		return BigDecimal.ZERO;
	}
	
	@Override
	public String toString() {
		return linePatterns.toString();
	}

	public BigDecimal getBet() {
		return bet;
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
