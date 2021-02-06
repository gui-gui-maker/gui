package com.gui.army.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.gui.army.bean.CodeTable;
import com.gui.army.bean.CodeTableValue;
import com.gui.army.dao.CodeTableValueRepository;

@Service
public class CodeTableValueManager {

	@Autowired
	CodeTableValueRepository codeTableValueRepository;

	public List<CodeTableValue> findAll() {
		return codeTableValueRepository.findAll();
	}

	public List<CodeTableValue> findByCodeTable(CodeTable codeTable) {
		if(codeTable == null || StringUtils.isEmpty(codeTable.getId())) {
			return new ArrayList<CodeTableValue>();
		}
		
		return codeTableValueRepository.findByCodeTable(codeTable);
	}
	
	@Transactional
	public void save(CodeTableValue codeTableValue) {
		codeTableValueRepository.save(codeTableValue);
	}
	
	@Transactional
	public void save(List<CodeTableValue> codeTableValues) {
		for (CodeTableValue codeTableValue : codeTableValues) {
			codeTableValueRepository.save(codeTableValue);
		}
	}
	
	@Transactional
	public void remove(List<CodeTableValue> codeTableValues) {
		for (CodeTableValue codeTableValue : codeTableValues) {
			codeTableValueRepository.delete(codeTableValue);
		}
	}
}
