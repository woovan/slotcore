package com.game.slot.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 老虎机转动设置
 * @author woovan
 *
 */
public class Setting {

	private BigDecimal bet;
	
	/** 轴停的位置列表 */
	private List<Integer> reelIndices;

	public BigDecimal getBet() {
		return bet;
	}

	public void setBet(BigDecimal bet) {
		this.bet = bet;
	}

	public List<Integer> getReelIndices() {
		return reelIndices;
	}

	public void setReelIndices(List<Integer> reelIndices) {
		this.reelIndices = reelIndices;
	}

}
