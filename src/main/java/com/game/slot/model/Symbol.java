package com.game.slot.model;

import com.game.slot.common.SymbolType;

/**
 * ͼ��
 * @author Administrator
 *
 */
public class Symbol {
	
	/** SymbolΨһID */
	private int id;
	
	/** Symbol���� */
	private SymbolType type;
	
	/** ����ID */
	private int settleId;
	
	/** ���㱶�� Ĭ��Ϊ1 */
	private int multiple = 1;

	public Symbol() {
		
	}
	
	public Symbol(int id, SymbolType type) {
		this.id = id;
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Symbol) {
			Symbol other = (Symbol)obj;
			return this.id == other.id;
		}
		return false;
	}
	
	/**
	 * Symbol�Ƿ�ƥ��
	 * @param other
	 * @return
	 */
	public boolean match(Symbol other) {
		if (this == other || this.getSettleId() == other.getSettleId()) {
			return true;
		}
		return false;
	}
	
	/**
	 * ��ȡ����ID ���û�����ý���ID��symbolId��Ϊ����ID
	 * @return
	 */
	public int getSettleId() {
		return settleId == 0 ? id : settleId;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		if (type == SymbolType.NORMAL) {
			return String.valueOf(id);
		}
		return String.valueOf(type.name().charAt(0));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SymbolType getType() {
		return type;
	}

	public void setType(SymbolType type) {
		this.type = type;
	}

	public void setSettleId(int settleId) {
		this.settleId = settleId;
	}

	public int getMultiple() {
		return multiple;
	}

	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}
	

}
