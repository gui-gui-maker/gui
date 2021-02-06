package com.gui.army.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.CodeTable;
@Repository
public interface CodeTableRepository extends JpaRepository<CodeTable,String>{

}
