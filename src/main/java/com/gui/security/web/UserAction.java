package com.gui.security.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Egress;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;
import com.gui.security.bean.User;
import com.gui.security.service.UserService;

@RestController
@RequestMapping("user")
public class UserAction {

	@Autowired
	UserService userService;

	@GetMapping(value = "findUser/{userName}")
	public Map<String, Object> findUser(@PathVariable String userName) {
		User user = userService.findByUserName(userName);
		return ResultVO.result(ResultEnum.SUCCESS, user);
	}
	
	@PostMapping(value = "save")
	public Map<String, Object> save(@RequestBody User user) {
		userService.save(user);
		return ResultVO.result(ResultEnum.SUCCESS, user);
	}
	
	@PostMapping(value = {"batchSave","edit"})
	public Map<String, Object> batchSave(@RequestBody List<User> users) {
		userService.batchSave(users);
		return ResultVO.result(ResultEnum.SUCCESS, users);
	}
	
	@PostMapping(value = "remove")
	public Map<String, Object> remove(@RequestBody List<User> users) {
		userService.remove(users);
		return ResultVO.result(ResultEnum.SUCCESS, users);
	}
	
	@PostMapping(value = "reset")
	public Map<String, Object> rest(String username) {
		userService.resetPassword(username);
		return ResultVO.result(ResultEnum.SUCCESS, username);
	}
	
	@PostMapping(value = "editPassword")
	public Map<String, Object> remove(String username,String newPassword,String oldPassword) {
		userService.resetPassword(username, newPassword, oldPassword);
		return ResultVO.result(ResultEnum.SUCCESS, username);
	}
	
	
	
	@RequestMapping(value = "all")
	public Map<String, Object> all(HttpServletRequest request, @RequestBody RP<User> r) {
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<User> p = userService.findAll(r.getStart(), r.getLength(), r.getT());
		
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}

}
