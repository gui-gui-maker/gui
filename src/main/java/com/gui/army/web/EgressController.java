package com.gui.army.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Egress;
import com.gui.army.bean.Member;
import com.gui.army.service.EgressManager;
import com.gui.pub.BeanMapTool;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;

@RestController
@RequestMapping("egress")
public class EgressController {

	@Autowired
	EgressManager egressManager;
	
	@RequestMapping(value= {"save","edit"})
	public Map<String,Object> save(@RequestBody List<Egress> egresses) throws Exception{
		
		egressManager.save(egresses);
		
		return ResultVO.result(ResultEnum.SUCCESS, egresses);
	}
	
	@RequestMapping(value="all")
	public Map<String,Object> findAll(HttpServletRequest request, @RequestBody RP<Egress> r){
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Egress> p = egressManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}
}
