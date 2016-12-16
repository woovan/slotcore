package com.game.slot.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class ConfigUtil {

	private static final String CONFIG_FILE_FORMAT = "/slotconfig/%s.json";
	
	public static <T> T getConfig(String fileName, Class<T> clazz) {
		String configText = getConfigText(fileName);
		if (StringUtils.isNotBlank(configText)) {
			return JSON.parseObject(configText, clazz);
		}
		return null;
	}
	
	public static <T> List<T> getConfigList(String fileName, Class<T> clazz) {
		String configText = getConfigText(fileName);
		if (StringUtils.isNotBlank(configText)) {
			return JSON.parseArray(configText, clazz);
		}
		return new ArrayList<>();
	}
	
	private static String getConfigText(String fileName) {
		String configFile = String.format(CONFIG_FILE_FORMAT, fileName);
		
		String configText = null;
		try {
			String path = ConfigUtil.class.getResource(configFile).getPath();
			path = path.substring(1, path.length());
			configText = new String(Files.readAllBytes(Paths.get(path)));
		} catch (Exception e) {
			System.out.println("File not found:" + configFile);
		}
		return configText;
	}
	
}
