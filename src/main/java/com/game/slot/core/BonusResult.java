package com.game.slot.core;

import java.math.BigDecimal;

import com.game.slot.model.Setting;

public class BonusResult implements IRoundResult {
	
	private BigDecimal baseMultiple;
	
	private BigDecimal bonusMultiple;
	
	private Setting setting;
	
	public BonusResult() {
		this.baseMultiple = BigDecimal.ONE;
	}

	public BonusResult(BigDecimal baseMultiple) {
		this.baseMultiple = baseMultiple;
	}

	@Override
	public boolean isWin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal getBet() {
		return setting.getBet();
	}

	@Override
	public BigDecimal winCoins() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
