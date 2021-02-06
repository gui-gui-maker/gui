package com.gui.army.web;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Org;
import com.gui.army.service.OrgManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;

@RestController
@RequestMapping("organization")
public class OrgController {
	@Autowired
	OrgManager orgManager;
	
	@RequestMapping(value="all")
	public Map<String, Object> findAll(@PathParam(value="draw") String draw,
			@PathParam(value="id") String id,
			@PathParam(value="name") String name,
			@PathParam(value="organizationname") String organizationname,
			@PathParam(value="length") Integer length,
			@PathParam(value="start") Long start){
		int d = 0;
		try {
			d = Integer.parseInt(draw);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Org organization = new Org();
		organization.setId(id);
		organization.setName(name);
		Page<Org> p = orgManager.findAll(start, length, organization);
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
	}

	@RequestMapping(value="findByLevel")
	public Map<String, Object> findAll(@RequestParam String level){
		
		return ResultVO.result(ResultEnum.SUCCESS,orgManager.findByLevelCode(level));
	}
	@RequestMapping(value="findAll")
	public Map<String, Object> findAll(){
		
		return ResultVO.result(ResultEnum.SUCCESS,orgManager.findAll());
	}
	
	@RequestMapping(value= {"save"})
	public Map<String, Object> add(@RequestBody Org organization){
		
		orgManager.save(organization);
		
		return ResultVO.result(ResultEnum.SUCCESS,organization);
	}
	@RequestMapping(value= {"batchSave","edit"})
	public Map<String, Object> save(@RequestBody List<Org> organizations) throws Exception{
		
		orgManager.save(organizations);
		
		return ResultVO.result(ResultEnum.SUCCESS,organizations);
	}
	@RequestMapping(value= {"remove"})
	public Map<String, Object> remove(String id) throws Exception{
		
		orgManager.remove(id);
		
		return ResultVO.result(ResultEnum.SUCCESS,id);
	}
	
}
