package com.game.slot.test.config;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.game.slot.common.LineType;
import com.game.slot.config.ConfigUtil;
import com.game.slot.config.LineManager;
import com.game.slot.config.SlotConfig;
import com.game.slot.model.Line;
import com.google.common.collect.ImmutableList;

public class ConfigTest extends TestCase {

	@Test
	public void testGetConfig() {
		SlotConfig config = ConfigUtil.getConfig("slot", SlotConfig.class);
		System.out.println(config);
	}
	
	@Test
	public void testLineManager() {
		List<Line> lines = LineManager.get(LineType.LINE_3X5);
		System.out.println(lines);
		
		List<Line> line10 = LineManager.get(LineType.LINE_3X5, ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		System.out.println(line10);
	}
}
