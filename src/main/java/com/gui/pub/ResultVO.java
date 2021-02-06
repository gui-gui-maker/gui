package com.gui.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.gui.army.bean.Egress;
import com.gui.army.bean.Vacation;

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
	public static <T> Map<String, Object> result(ResultEnum respCode,Page<T> p,int d) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", respCode.getCode());
		map.put("message", respCode.getMessage());
		map.put("data", p.getContent());
		map.put("recordsTotal", p.getTotalElements());
		map.put("recordsFiltered", p.getTotalElements());
		map.put("draw", d);
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

	public static <T> Map<String, Object> result(ResultEnum re, Page<T> p, List<Map<String, Object>> l, int d) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", re.getCode());
		map.put("message", re.getMessage());
		map.put("data", l);
		map.put("recordsTotal", p.getTotalElements());
		map.put("recordsFiltered", p.getTotalElements());
		map.put("draw", d);
		return map;
	}

}
