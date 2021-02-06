package com.gui.army.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.CodeTable;
import com.gui.army.service.CodeTableManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
@RestController
@RequestMapping("codeTable")
public class CodeTableController {

	@Autowired
	CodeTableManager codeTableManager;
	
	@RequestMapping(value="all")
	public Map<String,Object> findAll(){
		
		List<CodeTable> list = codeTableManager.findAll();
		
		return ResultVO.result(ResultEnum.SUCCESS, list);
		
	}
	
	@RequestMapping(value="remove")
	public Map<String,Object> remove(@RequestBody List<CodeTable> codeTables){
		
		codeTableManager.remove(codeTables);
		
		return ResultVO.result(ResultEnum.SUCCESS, codeTables);
		
	}
	
	@RequestMapping(value="save")
	public Map<String,Object> save(@RequestBody CodeTable codeTable){
		
		codeTableManager.save(codeTable);
		
		return ResultVO.result(ResultEnum.SUCCESS, codeTable );
		
	}
	
	@RequestMapping(value= {"batchSave","edit"})
	public Map<String,Object> batchSave(@RequestBody List<CodeTable> codeTables){
		
		codeTableManager.save(codeTables);
		
		return ResultVO.result(ResultEnum.SUCCESS, codeTables );
		
	}
}
