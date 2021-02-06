package com.gui.army.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.CodeTable;
import com.gui.army.bean.CodeTableValue;
@Repository
public interface CodeTableValueRepository extends JpaRepository<CodeTableValue,String> {

	//@Query(name = "from CodeTableValue where codeTable.id=?1")
	List<CodeTableValue> findByCodeTable(CodeTable codeTable);
	

}
