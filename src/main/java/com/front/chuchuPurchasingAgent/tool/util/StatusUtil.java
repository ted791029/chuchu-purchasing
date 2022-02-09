package com.front.chuchuPurchasingAgent.tool.util;

import java.util.HashMap;
import java.util.Map;

public class StatusUtil {
	
	public static Map<String, String> getStatusMap(){
		Map<String, String> map = new HashMap<>();
		map.put("1", "開啟");
		map.put("0", "關閉");
		return map;
	}
}
