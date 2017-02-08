package com.game.slot.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * payTable
 * @author Administrator
 *
 */
public class PayTable {

	/** <settleId, <count, multiple>> */
	private Map<Integer, TreeMap<Integer, BigDecimal>> payouts;

	public BigDecimal getMultiple(int settleId, int count) {
		return payouts.get(settleId).get(count);
	}
	
	public int getMinCount(int settleId) {
		return payouts.get(settleId).firstKey();
	}
	
	public Map<Integer, TreeMap<Integer, BigDecimal>> getPayouts() {
		return payouts;
	}

	public void setPayouts(Map<Integer, TreeMap<Integer, BigDecimal>> payouts) {
		this.payouts = payouts;
	}

}
