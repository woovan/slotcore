package com.game.slot.util;

import java.util.HashMap;
import java.util.Map;

import com.game.slot.model.Coordinate;

public class CoordinatePool {
	
	private static final int MAX_X = 5;
	
	private static final int MAX_Y = 3;

	private static final Map<Integer, Coordinate> pool = new HashMap<>();
	
	static {
		for (int x = 0; x < MAX_X; x++) {
			for (int y = 0; y < MAX_Y; y++) {
				pool.put((y << 3) + x, new Coordinate(x, y));
			}
		}
	}
	
	public static Coordinate get(int x, int y) {
		return pool.get((y << 3) + x);
	}
}
