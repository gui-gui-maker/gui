package com.gui.security.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;
import com.gui.security.bean.Role;
import com.gui.security.service.RoleManager;

@RestController
@RequestMapping("role")
public class RoleAction {

	@Autowired
	RoleManager roleManager;

	
	@PostMapping(value = "save")
	public Map<String, Object> save(@RequestBody Role role) {
		roleManager.save(role);
		return ResultVO.result(ResultEnum.SUCCESS, role);
	}
	
	@PostMapping(value = {"batchSave","edit"})
	public Map<String, Object> batchSave(@RequestBody List<Role> roles) {
		roleManager.batchSave(roles);
		return ResultVO.result(ResultEnum.SUCCESS, roles);
	}
	
	@PostMapping(value = "remove")
	public Map<String, Object> remove(@RequestBody List<Role> roles) {
		roleManager.remove(roles);
		return ResultVO.result(ResultEnum.SUCCESS, roles);
	}
	
	
	
	
	@RequestMapping(value = "all")
	public Map<String, Object> all(HttpServletRequest request, @RequestBody RP<Role> r) {
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Role> p = roleManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}

}
