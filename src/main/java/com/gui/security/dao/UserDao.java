package com.gui.security.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.Egress;
import com.gui.security.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	@Query("from User u where u.username=?1")
	User findByUsername(String username);


	Page<User> findAll(Specification<User> specification, Pageable pageable);

	

}
