package com.gui.army.web;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Visitor;
import com.gui.army.service.GuestManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;

@RestController
@RequestMapping("guest")
public class GuestController {
	@Autowired
	GuestManager guestManager;
	
	public Map<String,Object> add(Visitor guest) throws Exception{
		
		return ResultVO.result(ResultEnum.SUCCESS, guest);
	}
	
	@RequestMapping(value="all")
	public Map<String,Object> findAll(@PathParam(value="draw") String draw,
			@PathParam(value="id") String id,
			@PathParam(value="name") String name,
			@PathParam(value="phone") String phone,
			@PathParam(value="length") Integer length,
			@PathParam(value="start") Long start){
		int d = 0;
		try {
			d = Integer.parseInt(draw);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Visitor guest = new Visitor();
		guest.setId(id);
		guest.setName(name);
		guest.setPhone(phone);
		Page<Visitor> p = guestManager.findAll(start, length, guest);
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}

}
