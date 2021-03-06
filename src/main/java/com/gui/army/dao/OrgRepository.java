package com.gui.army.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.Org;

@Repository
public interface OrgRepository extends JpaRepository<Org, String> {

	Page<Org> findAll(Specification<Org> specification, Pageable pageable);
	
	List<Org> findByLevelCode(String level);
}
