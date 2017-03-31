package com.game.slot.core.handler;

import static com.game.slot.common.SymbolType.NORMAL;
import static com.game.slot.common.SymbolType.WILD;

import java.math.BigDecimal;
import java.util.List;

import com.game.slot.config.SlotConfig;
import com.game.slot.model.LinePattern;
import com.game.slot.model.MatrixSymbol;
import com.game.slot.model.Symbol;

public class DefaultLineHandler extends AbstractPatternHandler implements IPatternHandler {

	public DefaultLineHandler(SlotConfig config) {
		super(config);
	}

	public LinePattern handle(List<MatrixSymbol> lineSymbols) {
		MatrixSymbol[] line = lineSymbols.toArray(new MatrixSymbol[lineSymbols.size()]);
		
		Symbol target = line[0].getSymbol();
		if (target.getType() == NORMAL || target.getType() == WILD) {
			int count = 1;
			for (int i = 1; i < line.length; i++) {
				if (line[i].getSymbol().match(target) || line[i].getType() == WILD) {
					count ++;
				} else if (target.getType() == WILD && line[i].getType() == NORMAL) {
					target = line[i].getSymbol();
					count ++;
				} else {
					break;
				}
			}
			int minCount = getConfig().getPayTable().getMinCount(target.getSettleId());
			if (count >= minCount) {
				LinePattern pattern = new LinePattern();
//				pattern.setLine(payLine);
				pattern.setType(target.getType());
				pattern.setSettleId(target.getSettleId());
				pattern.setCount(count);
				
				BigDecimal multiple = getConfig().getPayTable().getMultiple(target.getSettleId(), count);
				pattern.setBaseMultiple(multiple);
				
				List<MatrixSymbol> symbols = lineSymbols.subList(0, count);
				pattern.setSymbols(symbols);
				return pattern;
			}
		}
		return null;
	}

	@Override
	public SlotConfig getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

}
