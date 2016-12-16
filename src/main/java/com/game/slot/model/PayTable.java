package com.game.slot.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class PayTable {

	private Map<Integer, TreeMap<Integer, BigDecimal>> payouts;

	public BigDecimal getMultiple(int symbolId, int count) {
		return payouts.get(symbolId).get(count);
	}
	
	public int getMinCount(int symbolId) {
		return payouts.get(symbolId).firstKey();
	}
	
	public Map<Integer, TreeMap<Integer, BigDecimal>> getPayouts() {
		return payouts;
	}

	public void setPayouts(Map<Integer, TreeMap<Integer, BigDecimal>> payouts) {
		this.payouts = payouts;
	}

}
