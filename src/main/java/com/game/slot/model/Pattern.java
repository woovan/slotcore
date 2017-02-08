package com.game.slot.model;

import java.math.BigDecimal;
import java.util.List;

import com.game.slot.common.SymbolType;

/**
 * һ��symbol��ɵ�ͼ��
 * @author Administrator
 *
 */
public class Pattern implements Comparable<Pattern> {

	/** symbol���� ��ͨsymbol��wild���typeΪNORMAL һ����ȫΪwild��typeΪWILD ����symbolֻ�ܺ�ͬ�������pattern */
	private SymbolType type;
	
	/** ����ID */
	private int settleId;
	
	/** ���� */
	private int count;
	
	/** �������� payTable�еķ������� */
	private BigDecimal baseMultiple;
	
	/** ���ͼ����symbol�б� */
	private List<MatrixSymbol> symbols;
	
	/**
	 * ������շ�������
	 * @return
	 */
	public BigDecimal getTotalMultiple() {
		return baseMultiple.multiply(getAdditionMultiple());
	}
	
	/**
	 * ��ø��ӷ������� ������Ը���
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
