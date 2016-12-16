package com.game.slot.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.game.slot.core.GameResult;
import com.game.slot.model.Coordinate;
import com.game.slot.model.LinePattern;
import com.game.slot.model.Symbol;

public class SlotUtil {
	
	private static final int SPACE = 5;
	
	public static void printScreen(Map<Coordinate, Symbol> screen) {
		int y = 0;
		for (Coordinate c : screen.keySet()) {
			if (c.getY() < y) {
				System.out.println();
			}
			System.out.print(StringUtils.center(screen.get(c).toString(), SPACE));
			y = c.getY();
		}
		System.out.println();
		System.out.println("______________________________");
	}
	
	public static void printResult(Map<Coordinate, Symbol> screen, GameResult result) {
		for (LinePattern pattern : result.getLinePatterns()) {
			System.out.println(pattern.toString());
			int y = 0;
			for (Coordinate c : screen.keySet()) {
				if (c.getY() < y) {
					System.out.println();
				}
				String symbol = screen.get(c).toString();
				if (pattern.getLine().getCoordinates().contains(c)) {
					symbol = pattern.getWinCoordinates().contains(c) ? "(" + symbol + ")" : "[" + symbol + "]";
				}
				System.out.print(StringUtils.center(symbol, SPACE));
				y = c.getY();
			}
			System.out.println();
			System.out.println("______________________________");
		}
	}
}
