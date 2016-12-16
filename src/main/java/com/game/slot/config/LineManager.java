package com.game.slot.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.game.slot.common.LineType;
import com.game.slot.model.Line;

public class LineManager {

	private static final Map<LineType, List<Line>> lineMap = new HashMap<>();
	
	static {
		for (LineType lineType : LineType.values()) {
			List<Line> lines = ConfigUtil.getConfigList(lineType.getConfigFile(), Line.class);
			lineMap.put(lineType, lines);
		}
	}
	
	public static List<Line> get(LineType lineType) {
		return lineMap.get(lineType);
	}
	
	public static List<Line> get(LineType lineType, List<Integer> lineIds) {
		List<Line> lines = lineMap.get(lineType);
		return lines.stream().filter(line -> lineIds.contains(line.getId())).collect(Collectors.toList());
	}
	
}
