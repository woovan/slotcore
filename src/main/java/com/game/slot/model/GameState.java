package com.game.slot.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GameState {

	/** Ͷע�� */
	private BigDecimal bet;
	
	/** ��ͣ��λ��Ԥ��ֵ ���ڵ��Ի������淨 <reelId, reelIndex> */
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
