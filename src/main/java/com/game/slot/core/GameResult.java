package com.game.slot.core;

import java.util.List;

import com.game.slot.common.SymbolType;

public class GameResult {

	private List<IRoundResult> roundResults;
	
	public boolean isWin() {
		return roundResults.get(0).isWin();
	}
	
	public boolean isWin(SymbolType symbolType) {
		RoundResult firstRound = (RoundResult)roundResults.get(0);
		return firstRound.isWin(symbolType);
	}
}
