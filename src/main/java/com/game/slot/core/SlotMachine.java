package com.game.slot.core;

import static com.game.slot.common.Direction.LEFT;
import static com.game.slot.common.SymbolType.NORMAL;
import static com.game.slot.common.SymbolType.WILD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;

import com.game.slot.common.Direction;
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
		Map<Line, List<Symbol>> lineMap = new TreeMap<>();
		
		config.getLines().stream().forEach(line -> {
			List<Symbol> lineSymbols = Lists.transform(line.getCoordinates(), c -> screen.get(c));
			lineMap.put(line, lineSymbols);
		});
		
		List<LinePattern> linePatterns = lineMap.entrySet().stream().map(entry -> calculateLine(entry.getKey(), entry.getValue()))
			.filter(pattern -> pattern != null).collect(Collectors.toList());
		
		Map<SymbolType, List<Pattern>> patterns = calculateScatter(screen);
		
		GameResult result = new GameResult();
		result.setBet(bet);
		result.setLinePatterns(linePatterns);
		result.setPatterns(patterns);
		
		return result;
	}
	
	private Map<SymbolType, List<Pattern>> calculateScatter(Screen screen) {
		Map<SymbolType, List<Pattern>> result = new HashMap<>();
		
		Map<Symbol, List<Coordinate>> symbolMap = screen.getMatrixSymbols().stream()
				.filter(matrixSymbol -> matrixSymbol.getSymbol().getType().compareTo(WILD) > 0)
				.collect(Collectors.groupingBy(MatrixSymbol::getSymbol, Collectors.mapping(MatrixSymbol::getCoordinate, Collectors.toList())));
		
		if (MapUtils.isNotEmpty(symbolMap)) {
			result = symbolMap.entrySet().stream().filter(entry -> {
				int count = entry.getValue().size();
				int minCount = config.getPayTable().getMinCount(entry.getKey().getId());
				return count >= minCount;
			}).map(entry -> {
				Symbol winSymbol = entry.getKey();
				List<Coordinate> winCoordinates = entry.getValue();
				int count = winCoordinates.size();
				BigDecimal multiple = config.getPayTable().getMultiple(winSymbol.getId(), count);
				
				Pattern pattern = new Pattern();
				pattern.setWinSymbol(winSymbol);
				pattern.setCount(count);
				pattern.setMultiple(multiple);
				pattern.setWinCoordinates(winCoordinates);
				return pattern;
				
			}).collect(Collectors.groupingBy(pattern -> pattern.getWinSymbol().getType()));
			
		}
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
	
	private LinePattern calculateLine(Line payLine, List<Symbol> lineSymbols) {
		LinePattern pattern = calculateLine(payLine, lineSymbols, Direction.LEFT);
		if (config.getLineRule() == LineRule.BOTH_SIDE) {
			lineSymbols = Lists.reverse(lineSymbols);
			LinePattern reversePattern = calculateLine(payLine, lineSymbols, Direction.RIGHT);
			if (reversePattern != null && reversePattern.compareTo(pattern) > 0){
				pattern = reversePattern;
			}
		}
		return pattern;
	}
	
	private LinePattern calculateLine(Line payLine, List<Symbol> lineSymbols, Direction direction) {
		int length = payLine.getLength();
		Symbol[] line = lineSymbols.toArray(new Symbol[length]);
		
		Symbol target = line[0];
		if (target.getType() == NORMAL || target.getType() == WILD) {
			int count = 1;
			for (int i = 1; i < line.length; i++) {
				if (line[i].getId() == target.getId() || line[i].getType() == WILD) {
					count ++;
				} else if (target.getType() == WILD && line[i].getType() == NORMAL) {
					target = line[i];
					count ++;
				} else {
					break;
				}
			}
			int minCount = config.getPayTable().getMinCount(target.getId());
			if (count >= minCount) {
				LinePattern pattern = new LinePattern();
				pattern.setLine(payLine);
				pattern.setWinSymbol(target);
				pattern.setCount(count);
				BigDecimal multiple = config.getPayTable().getMultiple(target.getId(), count);
				pattern.setMultiple(multiple);
				List<Coordinate> winCoordinates = (direction == LEFT ? payLine.getCoordinates().subList(0, count) 
						: payLine.getCoordinates().subList(length - count, length));
				pattern.setWinCoordinates(winCoordinates);
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
