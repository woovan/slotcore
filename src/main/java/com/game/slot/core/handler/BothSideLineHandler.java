package com.game.slot.core.handler;

import java.util.List;

import com.game.slot.config.SlotConfig;
import com.game.slot.model.LinePattern;
import com.game.slot.model.MatrixSymbol;
import com.google.common.collect.Lists;

public class BothSideLineHandler extends DefaultLineHandler implements IPatternHandler {

	public BothSideLineHandler(SlotConfig config) {
		super(config);
	}

	public LinePattern handle(List<MatrixSymbol> lineSymbols) {
		LinePattern pattern = super.handle(lineSymbols);
		lineSymbols = Lists.reverse(lineSymbols);
		LinePattern reversePattern = super.handle(lineSymbols);
		if (reversePattern != null && reversePattern.compareTo(pattern) > 0){
			pattern = reversePattern;
		}
		return pattern;
	}

}
