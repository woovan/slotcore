package com.game.slot.core;

import java.math.BigDecimal;

import com.game.slot.config.ConfigUtil;
import com.game.slot.config.SlotConfig;
import com.game.slot.model.Screen;

public class Runner {
	
	public static final int PLAY_COUNT = 10;
	
	public static final boolean PRINT_LOG = true;

	public static void main(String[] args) {
		
		SlotConfig config = ConfigUtil.getConfig("slot", SlotConfig.class);
		SlotMachine slot = new SlotMachine(config);
		
		long beginTm = System.currentTimeMillis();
		
		for (int i = 0; i < PLAY_COUNT; i++) {
			Screen screen = slot.spin();
			if (PRINT_LOG) screen.print();
			
			RoundResult result = slot.calculate(screen, BigDecimal.TEN);
			if (PRINT_LOG) result.print();
		}
		
		System.out.println("cost:" + (System.currentTimeMillis() - beginTm) + "ms");
	}
}
