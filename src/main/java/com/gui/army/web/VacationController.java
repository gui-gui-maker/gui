package com.gui.army.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Member;
import com.gui.army.bean.Vacation;
import com.gui.army.bean.Vacation;
import com.gui.army.service.VacationManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;

@RestController
@RequestMapping("vacation")
public class VacationController {
	
	@Autowired
	VacationManager vacationManager;
	
	@RequestMapping(value="save")
	public Map<String,Object> save(@RequestBody List<Vacation> vacations) throws Exception{
		
		vacationManager.save(vacations);
		
		return ResultVO.result(ResultEnum.SUCCESS, vacations);
	}
	@RequestMapping(value="edit")
	public Map<String,Object> edit(@RequestBody List<Vacation> vacations) throws Exception{
		
		vacationManager.save(vacations);
		
		return ResultVO.result(ResultEnum.SUCCESS, vacations);
	}
	
	@RequestMapping(value="remove")
	public Map<String,Object> remove(@RequestBody List<Vacation> vacations) throws Exception{
		
		vacationManager.remove(vacations);
		
		return ResultVO.result(ResultEnum.SUCCESS, vacations);
	}
	
	@RequestMapping(value="all")
	public Map<String, Object> findAll(HttpServletRequest request, @RequestBody RP<Vacation> r){
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Vacation> p = vacationManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
	}
	@RequestMapping(value="findVacationsByMember")
	public Map<String, Object> findVacationByMember(HttpServletRequest request, @RequestBody Vacation vacation){

		Member member = vacation.getMember();
		List<Vacation> list = vacationManager.findByYearAndMember(vacation.getYear(),member);
		List<Map<String,Object>> options = new ArrayList<>();
		for (Vacation v : list) {
			Map<String,Object> m = new HashMap<>();
			m.put("label", v.getMember().getName()+"-"+v.getHolidayType());
			m.put("value", v.getId());
			options.add(m);
		}
		return ResultVO.result(ResultEnum.SUCCESS,options);
	}

}
