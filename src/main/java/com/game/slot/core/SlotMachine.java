package com.game.slot.core;

import static com.game.slot.common.SymbolType.NORMAL;
import static com.game.slot.common.SymbolType.WILD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.game.slot.common.LineRule;
import com.game.slot.common.SymbolType;
import com.game.slot.config.SlotConfig;
import com.game.slot.model.Coordinate;
import com.game.slot.model.Line;
import com.game.slot.model.LinePattern;
import com.game.slot.model.MatrixSymbol;
import com.game.slot.model.Pattern;
import com.game.slot.model.Screen;
import com.game.slot.model.Symbol;
import com.google.common.collect.Lists;

public class SlotMachine {

	private SlotConfig config;
	
	public SlotMachine(SlotConfig config) {
		this.config = config;
	}

	public Screen spin() {
		Screen screen = new Screen();
		
		List<List<Integer>> symbolColumns = Lists.transform(config.getReels(), reel -> reel.spin(config.getRowNum()));
		for (int x = 0; x < symbolColumns.size(); x++) {
			List<Integer> column = symbolColumns.get(x);
			for (int y = 0; y < column.size(); y++) {
				screen.put(new Coordinate(x, y), config.getSymbol(column.get(y)));
			}
		}
		return screen;
	}
	
	public GameResult calculate(Screen screen, BigDecimal bet) {
		Map<Line, List<MatrixSymbol>> lineMap = new TreeMap<>();
		
		config.getLines().stream().forEach(line -> {
			List<MatrixSymbol> lineSymbols = Lists.transform(line.getCoordinates(), c -> screen.get(c));
			lineMap.put(line, lineSymbols);
		});
		
		List<LinePattern> linePatterns = lineMap.entrySet().stream()
				.map(entry -> calculateLine(entry.getKey(), entry.getValue()))
				.filter(pattern -> pattern != null).collect(Collectors.toList());
		
		Map<SymbolType, List<Pattern>> patterns = calculateScatter(screen);
		
		GameResult result = new GameResult();
		result.setBet(bet);
		result.setScreen(screen);
		result.setLinePatterns(linePatterns);
		result.setPatterns(patterns);
		
		return result;
	}
	
	/**
	 * 计算散点symbol(所有非线上symbol)
	 * @param screen
	 * @return
	 */
	private Map<SymbolType, List<Pattern>> calculateScatter(Screen screen) {
		
		Map<SymbolType, List<Pattern>> result = screen.getMatrixSymbols().stream()
				.filter(matrixSymbol -> matrixSymbol.getType().compareTo(WILD) > 0)	//找出特殊symbol
				.collect(Collectors.groupingBy(MatrixSymbol::getSettleId)).entrySet().stream()	//按settleId分组
				.filter(entry -> {	//过滤掉数量不足的组合
					int count = entry.getValue().size();
					int minCount = config.getPayTable().getMinCount(entry.getKey());
					return count >= minCount;
				}).map(entry -> {	//转换成pattern
					int settleId = entry.getKey();
					List<MatrixSymbol> symbols = entry.getValue();
					SymbolType type = symbols.get(0).getType();
					int count = symbols.size();
					BigDecimal multiple = config.getPayTable().getMultiple(settleId, count);
					
					Pattern pattern = new Pattern();
					pattern.setType(type);
					pattern.setSettleId(settleId);
					pattern.setCount(count);
					pattern.setSymbols(symbols);
					pattern.setBaseMultiple(multiple);
					return pattern;
				}).collect(Collectors.groupingBy(Pattern::getType));	//按symbolType分组
		
		return result;
	}
	
	public static void main(String[] args) {
		List<Symbol> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(new Symbol(i + 1, SymbolType.values()[i % 4]));
		}
		System.out.println(list);
		Map<SymbolType, Integer> map = list.stream().collect(Collectors.groupingBy(Symbol::getType, Collectors.summingInt(p -> 1)));
		System.out.println(map);
		
	}
	
	/**
	 * 计算线中奖情况
	 */
	private LinePattern calculateLine(Line payLine, List<MatrixSymbol> lineSymbols) {
		LinePattern pattern = _calculateLine(payLine, lineSymbols);
		if (config.getLineRule() == LineRule.BOTH_SIDE) {
			lineSymbols = Lists.reverse(lineSymbols);
			LinePattern reversePattern = _calculateLine(payLine, lineSymbols);
			if (reversePattern != null && reversePattern.compareTo(pattern) > 0){
				pattern = reversePattern;
			}
		}
		return pattern;
	}
	
	private LinePattern _calculateLine(Line payLine, List<MatrixSymbol> lineSymbols) {
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
			int minCount = config.getPayTable().getMinCount(target.getSettleId());
			if (count >= minCount) {
				LinePattern pattern = new LinePattern();
				pattern.setLine(payLine);
				pattern.setType(target.getType());
				pattern.setSettleId(target.getSettleId());
				pattern.setCount(count);
				
				BigDecimal multiple = config.getPayTable().getMultiple(target.getSettleId(), count);
				pattern.setBaseMultiple(multiple);
				
				List<MatrixSymbol> symbols = lineSymbols.subList(0, count);
				pattern.setSymbols(symbols);
				return pattern;
			}
		}
		return null;
	}
	
	public SlotConfig getConfig() {
		return config;
	}

	public void setConfig(SlotConfig config) {
		this.config = config;
	}
	
}
