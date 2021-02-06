package com.gui.jwt;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultVO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static Map<String, Object> result(ResultEnum respCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", respCode.getCode());
		map.put("message", respCode.getMessage());
		map.put("data", null);
		return map;
	}
	
	public static Map<String, Object> result(ResultEnum respCode,Object detail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", respCode.getCode());
		map.put("message", respCode.getMessage());
		map.put("data", detail);
		return map;
	}

	/**
	 * description: 返回响应信息及Token
	 *
	 * @param respCode
	 * @param jwtToken
	 * @param success
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 */
	public final static Map<String, Object> result(ResultEnum respCode, String jwtToken) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jwtToken", jwtToken);
		map.put("code", respCode.getCode());
		map.put("message", respCode.getMessage());
		map.put("data", null);
		return map;
	}

}
