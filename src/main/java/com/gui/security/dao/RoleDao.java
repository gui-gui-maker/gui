package com.gui.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui.security.bean.Role;
@Repository
public interface RoleDao extends JpaRepository<Role, String>{

}
