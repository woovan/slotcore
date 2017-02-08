package com.game.slot.model;

import com.game.slot.common.SymbolType;

/**
 * 图标
 * @author Administrator
 *
 */
public class Symbol {
	
	/** Symbol唯一ID */
	private int id;
	
	/** Symbol类型 */
	private SymbolType type;
	
	/** 结算ID */
	private int settleId;
	
	/** 结算倍数 默认为1 */
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
	 * Symbol是否匹配
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
	 * 获取结算ID 如果没有设置结算ID则symbolId作为结算ID
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
