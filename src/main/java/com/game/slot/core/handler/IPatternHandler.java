package com.game.slot.core.handler;

import java.util.List;

import com.game.slot.model.MatrixSymbol;
import com.game.slot.model.Pattern;

public interface IPatternHandler {
	
	Pattern handle(List<MatrixSymbol> symbols);
	
}
