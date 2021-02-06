package com.gui.army.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.Visit;
@Repository
public interface VisitRepository extends JpaRepository<Visit,String> {

	Page<Visit> findAll(Specification<Visit> specification, Pageable pageable);
	
	

}
