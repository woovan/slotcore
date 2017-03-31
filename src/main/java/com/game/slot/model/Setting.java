package com.game.slot.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * �ϻ�������
 * @author Administrator
 *
 */
public class Setting {

	/** Ͷע�� */
	private BigDecimal bet;
	
	/** ��ͣ��λ��Ԥ��ֵ ���ڵ��Ի������淨 <reelId, reelIndex> */
	private Map<Integer, Integer> presetReelIdxs = new HashMap<>();

	public BigDecimal getBet() {
		return bet;
	}

	public void setBet(BigDecimal bet) {
		this.bet = bet;
	}

	public Map<Integer, Integer> getPresetReelIdxs() {
		return presetReelIdxs;
	}

	public void setPresetReelIdxs(Map<Integer, Integer> presetReelIdxs) {
		this.presetReelIdxs = presetReelIdxs;
	}

}
