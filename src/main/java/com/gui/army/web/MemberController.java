package com.gui.army.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Member;
import com.gui.army.service.MemberManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;
@RestController
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberManager memberManager;
	
	@RequestMapping(value="save")
	public Map<String,Object> save(@RequestBody Member member) throws Exception{
		
		memberManager.save(member);
		
		return ResultVO.result(ResultEnum.SUCCESS, member);
	}
	@RequestMapping(value="edit")
	public Map<String,Object> edit(@RequestBody List<Member> members) throws Exception{
		
		memberManager.save(members);
		
		return ResultVO.result(ResultEnum.SUCCESS, members);
	}
	
	@RequestMapping(value="remove")
	public Map<String,Object> remove(@RequestBody List<Member> members) throws Exception{
		
		memberManager.remove(members);
		
		return ResultVO.result(ResultEnum.SUCCESS, members);
	}
	@RequestMapping(value="all")
	public Map<String,Object> findAll(HttpServletRequest request, @RequestBody RP<Member> r){
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		Page<Member> p = memberManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}
	
	@RequestMapping(value="findMemberOptions")
	public Map<String,Object> findMemberDict(HttpServletRequest request){
		List<Map<String,Object>> list = memberManager.findMemberOptions();
		
		return ResultVO.result(ResultEnum.SUCCESS,list);
		
	}

}
