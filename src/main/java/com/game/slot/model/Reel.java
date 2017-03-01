package com.game.slot.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * 轴
 * @author Administrator
 *
 */
public class Reel {

	private List<Integer> symbols;
	
	public Reel() {
		
	}
	
	public Reel(List<Integer> symbols) {
		this.symbols = symbols;
	}

	/**
	 * 轴转动，返回轴停的index
	 * @return
	 */
	public int spin() {
		return RandomUtils.nextInt(0, symbols.size());
	}
	
	/**
	 * 获取轴上的元素
	 * @param index	起始位置
	 * @param count	数量
	 * @return
	 */
	public List<Integer> getSymbols(int index, int count) {
		int length = symbols.size();
		int endIndex = index + count;
		if (endIndex > length) {
			List<Integer> list = new ArrayList<>(symbols.subList(index, length));
			list.addAll(symbols.subList(0, endIndex - length));
			return list;
		}
		return new ArrayList<>(symbols.subList(index, endIndex));
	}
	
	public int getLength() {
		return symbols.size();
	}
	
	public List<Integer> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Integer> symbols) {
		this.symbols = symbols;
	}
	
}
