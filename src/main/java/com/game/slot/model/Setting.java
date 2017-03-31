package com.game.slot.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 老虎机设置
 * @author Administrator
 *
 */
public class Setting {

	/** 投注额 */
	private BigDecimal bet;
	
	/** 轴停的位置预设值 用于调试或特殊玩法 <reelId, reelIndex> */
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
