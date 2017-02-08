package com.game.slot.util;

import java.util.HashMap;
import java.util.Map;

import com.game.slot.model.Coordinate;

public class CoordinatePool {
	
	private final Map<Integer, Coordinate> pool = new HashMap<>();
	
	public CoordinatePool(int maxX, int maxY) {
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				pool.put((y << 4) ^ x, new Coordinate(x, y));	//����4λ�൱��y*16��֧��x���ֵ15
			}
		}
	}
	
	public Coordinate get(int x, int y) {
		return pool.get((y << 4) ^ x);
	}
	
}
