package com.gui.army.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gui.army.bean.CodeTable;
import com.gui.army.dao.CodeTableRepository;

@Service
public class CodeTableManager {
	
	@Autowired
	CodeTableRepository codeTableRepository;

	public List<CodeTable> findAll() {
		// TODO Auto-generated method stub
		return codeTableRepository.findAll();
	}

	@Transactional
	public void save(CodeTable codeTable) {
		codeTableRepository.save(codeTable);
	}

	@Transactional
	public void save(List<CodeTable> codeTables) {
		for (CodeTable codeTable : codeTables) {
			codeTableRepository.save(codeTable);
		}
	}

	@Transactional
	public void remove(List<CodeTable> codeTables) {
		for (CodeTable codeTable : codeTables) {
			codeTableRepository.delete(codeTable);
		}
	}

}
