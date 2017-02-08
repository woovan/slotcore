package com.game.slot.model;

import java.math.BigDecimal;
import java.util.List;

import com.game.slot.common.SymbolType;

/**
 * 一组symbol组成的图案
 * @author Administrator
 *
 */
public class Pattern implements Comparable<Pattern> {

	/** symbol类型 普通symbol和wild组合type为NORMAL 一条线全为wild则type为WILD 特殊symbol只能和同类型组成pattern */
	private SymbolType type;
	
	/** 结算ID */
	private int settleId;
	
	/** 数量 */
	private int count;
	
	/** 基础倍数 payTable中的反奖倍数 */
	private BigDecimal baseMultiple;
	
	/** 组成图案的symbol列表 */
	private List<MatrixSymbol> symbols;
	
	/**
	 * 获得最终反奖倍数
	 * @return
	 */
	public BigDecimal getTotalMultiple() {
		return baseMultiple.multiply(getAdditionMultiple());
	}
	
	/**
	 * 获得附加反奖倍数 子类可以覆盖
	 * @return
	 */
	public BigDecimal getAdditionMultiple() {
		return BigDecimal.ONE;
	}

	@Override
	public int compareTo(Pattern o) {
		if (o == null) {
			return 1;
		}
		return this.getTotalMultiple().compareTo(o.getTotalMultiple());
	}
	
	@Override
	public String toString() {
		return String.format("(%s) * %d -> %.2f * %.2f = %.2f", settleId, count, baseMultiple.doubleValue(), getAdditionMultiple().doubleValue(), getTotalMultiple().doubleValue());
	}
	
	public int getSettleId() {
		return settleId;
	}

	public void setSettleId(int settleId) {
		this.settleId = settleId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public SymbolType getType() {
		return type;
	}

	public void setType(SymbolType type) {
		this.type = type;
	}

	public BigDecimal getBaseMultiple() {
		return baseMultiple;
	}

	public void setBaseMultiple(BigDecimal baseMultiple) {
		this.baseMultiple = baseMultiple;
	}

	public List<MatrixSymbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<MatrixSymbol> symbols) {
		this.symbols = symbols;
	}

}
