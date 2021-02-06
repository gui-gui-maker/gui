package com.gui.army.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.CodeTable;
import com.gui.army.bean.CodeTableValue;
import com.gui.army.service.CodeTableValueManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;

@RestController
@RequestMapping("codeTableValue")
public class CodeTableValueController {

	@Autowired
	CodeTableValueManager codeTableValueManager;

	@RequestMapping(value="all")
	public Map<String, Object> findAll(@RequestBody CodeTableValue codeTableValue) {

		List<CodeTableValue> list = codeTableValueManager.findAll();

		return ResultVO.result(ResultEnum.SUCCESS, list);

	}
	
	@RequestMapping(value="remove")
	public Map<String, Object> remove(@RequestBody List<CodeTableValue> codeTableValues) {
		
		codeTableValueManager.remove(codeTableValues);
		
		return ResultVO.result(ResultEnum.SUCCESS, codeTableValues);
		
	}
	
	@RequestMapping(value= {"save","edit"})
	public Map<String, Object> save(@RequestBody List<CodeTableValue> codeTableValues) {
		
		codeTableValueManager.save(codeTableValues);
		
		return ResultVO.result(ResultEnum.SUCCESS, codeTableValues);
		
	}
	@RequestMapping(value="findByCodeTable")
	public Map<String, Object> findByCodeTable(String id) {
		
		
		List<CodeTableValue> list = codeTableValueManager.findByCodeTable(new CodeTable(id));
		
		return ResultVO.result(ResultEnum.SUCCESS, list);
		
	}

}
