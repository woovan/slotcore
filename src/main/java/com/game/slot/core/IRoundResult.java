package com.game.slot.core;

import java.math.BigDecimal;

public interface IRoundResult {

	boolean isWin();
	
	BigDecimal getBet();
	
	BigDecimal winCoins();
	
}
