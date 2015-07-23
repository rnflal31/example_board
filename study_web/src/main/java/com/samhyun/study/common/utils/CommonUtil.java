package com.samhyun.study.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public final class CommonUtil {
	
    public static Map<String, Object> toMap(Object obj){
    	ObjectMapper mapper = new ObjectMapper();
    	HashMap<String, Object> converted = mapper.convertValue(obj, HashMap.class);
    	return converted;
    }
}
