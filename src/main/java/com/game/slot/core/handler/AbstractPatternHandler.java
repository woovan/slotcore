package com.game.slot.core.handler;

import com.game.slot.config.SlotConfig;

public abstract class AbstractPatternHandler {

	private SlotConfig config;
	
	public AbstractPatternHandler(SlotConfig config) {
		this.config = config;
	}

	public SlotConfig getConfig() {
		return config;
	}
	
}
