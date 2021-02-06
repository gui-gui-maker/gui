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

import com.gui.army.bean.Visit;
import com.gui.army.service.VisitManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;

@RestController
@RequestMapping("visit")
public class VisitController {

	@Autowired
	VisitManager visitManager;
	

	@PostMapping(value = "save")
	public Map<String, Object> save(@RequestBody Visit visitorVisits) {
		visitManager.save(visitorVisits);
		return ResultVO.result(ResultEnum.SUCCESS, visitorVisits);
	}
	
	@PostMapping(value = {"batchSave","edit"})
	public Map<String, Object> batchSave(@RequestBody List<Visit> visits) {
		visitManager.batchSave(visits);
		return ResultVO.result(ResultEnum.SUCCESS, visits);
	}
	
	@PostMapping(value = "remove")
	public Map<String, Object> remove(@RequestBody List<Visit> visitorVisitss) {
		visitManager.remove(visitorVisitss);
		return ResultVO.result(ResultEnum.SUCCESS, visitorVisitss);
	}
	
	
	
	
	@RequestMapping(value = "all")
	public Map<String, Object> all(HttpServletRequest request, @RequestBody RP<Visit> r) {
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Visit> p = visitManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}
}
