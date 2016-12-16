package com.game.slot.model;

import com.game.slot.common.SymbolType;

public class Symbol {
	
	private int id;
	
	private SymbolType type;

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
	

}
