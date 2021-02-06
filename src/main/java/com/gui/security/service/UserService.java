package com.gui.security.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.gui.security.bean.User;

@Transactional(rollbackFor = Exception.class)
public interface UserService {
	
	User findByUserName(String username);
	 
    User add(String userName,String password);

	void save(User user);

	Page<User> findAll(Long start, Integer length, User t);

	void batchSave(List<User> users);

	void remove(List<User> users);

	void resetPassword(String username, String newp, String oldp);

	void resetPassword(String username);

}
