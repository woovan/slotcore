package com.game.slot.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * �ϻ���ת������
 * @author woovan
 *
 */
public class Setting {

	private BigDecimal bet;
	
	/** ��ͣ��λ���б� */
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
