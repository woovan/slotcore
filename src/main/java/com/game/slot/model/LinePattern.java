package com.game.slot.model;


public class LinePattern extends Pattern {

	private Line line;
	
	@Override
	public String toString() {
		return "line" + line.getId() + " : " + super.toString();
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

}
