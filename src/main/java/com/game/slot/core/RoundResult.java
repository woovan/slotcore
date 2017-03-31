package com.game.slot.core;

import static com.game.slot.common.SymbolType.WILD;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.game.slot.common.SymbolType;
import com.game.slot.model.LinePattern;
import com.game.slot.model.Pattern;
import com.game.slot.model.Screen;
import com.game.slot.model.Setting;

public class RoundResult implements IRoundResult {
	
	/** ��Ϸ���� */
	private Setting setting;
	
	/** ��Ļ��ʾ */
	private Screen screen;

	/** ��ͨͼ���н����� */
	private List<LinePattern> linePatterns;
	
	/** ����ͼ���н�ͼ�� SymbolTypeΪ BONUS SCATTER�� */
	private Map<SymbolType, Pattern> patterns;
	
	/** bonus��Ϸ��� û��bonusΪnull */
	private BonusResult bonusResult;
	
	
	public boolean isWin() {
		return CollectionUtils.isNotEmpty(linePatterns) || MapUtils.isNotEmpty(patterns);
	}
	
	public boolean isWin(SymbolType symbolType) {
		return symbolType.compareTo(WILD) <= 0 ? CollectionUtils.isNotEmpty(linePatterns) : patterns.containsKey(symbolType);
	}
	
	public BigDecimal winCoins() {
		if (CollectionUtils.isNotEmpty(linePatterns)) {
			BigDecimal sumMultiple = linePatterns.stream().map(pattern -> pattern.getTotalMultiple()).reduce(BigDecimal::add).get();
			return getBet().multiply(sumMultiple);
		}
		return BigDecimal.ZERO;
	}
	
	public BigDecimal bonusWinCoins() {
		return BigDecimal.ZERO;
	}
	
	public void print() {
		linePatterns.forEach(pattern -> screen.print(pattern));
		
		patterns.entrySet().forEach(entry -> {
			System.out.print(entry.getKey() + ": ");
			screen.print(entry.getValue());
		});
		System.out.println("______________________________");
	}
	
	@Override
	public String toString() {
		return linePatterns.toString();
	}

	public BigDecimal getBet() {
		return setting.getBet();
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<LinePattern> getLinePatterns() {
		return linePatterns;
	}

	public void setLinePatterns(List<LinePattern> linePatterns) {
		this.linePatterns = linePatterns;
	}

	public Map<SymbolType, Pattern> getPatterns() {
		return patterns;
	}

	public void setPatterns(Map<SymbolType, Pattern> patterns) {
		this.patterns = patterns;
	}

}
