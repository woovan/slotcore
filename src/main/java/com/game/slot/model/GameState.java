package com.game.slot.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GameState {

	/** 投注额 */
	private BigDecimal bet;
	
	/** 轴停的位置预设值 用于调试或特殊玩法 <reelId, reelIndex> */
	private Map<Integer, Integer> presetReels = new HashMap<>();
	
	public BigDecimal getBet() {
		return bet;
	}

	public void setBet(BigDecimal bet) {
		this.bet = bet;
	}

	public Map<Integer, Integer> getPresetReels() {
		return presetReels;
	}

	public void setPresetReels(Map<Integer, Integer> presetReels) {
		this.presetReels = presetReels;
	}
	
}
