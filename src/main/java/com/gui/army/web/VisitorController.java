package com.gui.army.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Visitor;
import com.gui.army.service.VisitorManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;

@RestController
@RequestMapping("visitor")
public class VisitorController {

	@Autowired
	VisitorManager visitorManager;
	

	@PostMapping(value = "save")
	public Map<String, Object> save(@RequestBody Visitor visitor) {
		visitorManager.save(visitor);
		return ResultVO.result(ResultEnum.SUCCESS, visitor);
	}
	
	@PostMapping(value = {"batchSave","edit"})
	public Map<String, Object> batchSave(@RequestBody List<Visitor> visitors) {
		visitorManager.batchSave(visitors);
		return ResultVO.result(ResultEnum.SUCCESS, visitors);
	}
	
	@PostMapping(value = "remove")
	public Map<String, Object> remove(@RequestBody List<Visitor> visitors) {
		visitorManager.remove(visitors);
		return ResultVO.result(ResultEnum.SUCCESS, visitors);
	}
	
	
	
	
	@RequestMapping(value = "all")
	public Map<String, Object> all(HttpServletRequest request, @RequestBody RP<Visitor> r) {
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Visitor> p = visitorManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}
}
