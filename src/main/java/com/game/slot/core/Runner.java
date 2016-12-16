package com.game.slot.core;

import java.math.BigDecimal;

import com.game.slot.config.ConfigUtil;
import com.game.slot.config.SlotConfig;
import com.game.slot.model.Screen;

public class Runner {

	public static void main(String[] args) {
		
		SlotConfig config = ConfigUtil.getConfig("slot", SlotConfig.class);
		SlotMachine slot = new SlotMachine(config);
		
		long beginTm = System.currentTimeMillis();
		
		for (int i = 0; i < 1000000; i++) {
			Screen screen = slot.spin();
//			SlotUtil.printScreen(screen);
			
			GameResult result = slot.calculate(screen, BigDecimal.TEN);
			
//			SlotUtil.printResult(screen, result);
		}
		
		System.out.println("cost:" + (System.currentTimeMillis() - beginTm) + "ms");
	}
}
