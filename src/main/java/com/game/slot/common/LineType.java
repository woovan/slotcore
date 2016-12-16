package com.game.slot.common;

public enum LineType {

	LINE_3X3("line3x3"),
	LINE_3X5("line3x5")
	
	;
	
	private String configFile;
	
	private LineType(String configFile) {
		this.configFile = configFile;
	}

	public String getConfigFile() {
		return configFile;
	}
	
}
