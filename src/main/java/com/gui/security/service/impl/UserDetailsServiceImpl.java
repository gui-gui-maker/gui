package com.gui.security.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gui.jwt.JwtAuthUser;
import com.gui.security.bean.User;
import com.gui.security.dao.UserDao;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user==null ){
			// 实际当用户不存在时，应该页面显示错误信息，并跳转到登录界面
			throw new UsernameNotFoundException("该用户不存在！");
		}
		
        System.out.println("UserDetailsServiceImpl==>loadUserByUsername:"+user.toString());
        return new JwtAuthUser(user);
	}

}
