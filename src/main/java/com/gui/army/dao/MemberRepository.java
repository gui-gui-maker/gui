package com.gui.army.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.Member;
import com.gui.army.bean.Org;
@Repository
public interface MemberRepository extends JpaRepository<Member,String> {

	Page<Member> findAll(Specification<Member> specification, Pageable pageable);

	
	List<Member> findByOrganization(Org organization);
	
	@Query(name="from Member where organization.id=?")
	List<Member> findByOrganizationId(String id);

}
