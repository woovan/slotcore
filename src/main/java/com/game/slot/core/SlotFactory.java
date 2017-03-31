package com.game.slot.core;

import org.apache.commons.lang3.StringUtils;

import com.game.slot.config.ConfigUtil;
import com.game.slot.config.SlotConfig;


public class SlotFactory {

	public static SlotMachine getSlotMachine(String slotName) {
		
		SlotMachine slotMachine = _getSlotMachine(slotName);
		
		String freeSpinName = slotMachine.getConfig().getFreeSpin();
		if (StringUtils.isNoneBlank(freeSpinName)) {
			SlotMachine freeSpinSlotMachine = _getSlotMachine(freeSpinName);
			slotMachine.setFreeSpinSlotMachine(freeSpinSlotMachine);
		}
		return slotMachine;
	}
	
	private static SlotMachine _getSlotMachine(String slotName) {
		SlotConfig config = ConfigUtil.getConfig(slotName, SlotConfig.class);
		return config != null ? new SlotMachine(config) : null;
	}
}
