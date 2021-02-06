package com.gui.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.gui.exception.VeException;
import com.gui.security.bean.JwtUserDetails;
import com.gui.security.bean.Role;
import com.gui.security.bean.User;
//import com.gui.security.dao.RoleDao;
import com.gui.security.dao.UserDao;
import com.gui.security.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	/*
	 * @Autowired private RoleDao roleDao;
	 */
	@Override
	public User findByUserName(String username) {
		User user = userDao.findByUsername(username);
		if(user==null){
			// 实际当用户不存在时，应该页面显示错误信息，并跳转到登录界面
			throw new UsernameNotFoundException("该用户不存在！");
		}
        System.out.println("UserDetailsServiceImpl==>loadUserByUsername:"+user.toString());
		return user;
	}

	@Override
	public User add(String userName, String password) {
		// TODO Auto-generated method stub
		return userDao.save(new User(userName,password));
	}

	@Override
	public void save(User user) {
		String pass = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(pass);
		userDao.save(user);
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = "123456";
		System.out.println(encoder.encode(pwd));
	}

	@SuppressWarnings("serial")
	@Override
	public Page<User> findAll(Long start, Integer length, User user) {
		long page = start / length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
		// Sort sort = new Sort(Sort.Direction.DESC, "id"); //
		// 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int) page - 1, length, Sort.Direction.ASC, "id");// PageRequest((int)page -
																								// 1, length, sort); //
																								// （当前页， 每页记录数， 排序方式）

		Page<User> p = userDao.findAll(new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				// 休假类型

				if (!StringUtils.isEmpty(user.getUsername())) {
					list.add(criteriaBuilder.equal(root.get("username").as(String.class), user.getUsername()));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		}, pageable);

		return p;
	}

	@Override
	public void batchSave(List<User> users) {
		for (User user : users) {
			String pass = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(pass);
			userDao.save(user);
		}
	}
	@Override
	public void resetPassword(String username,String newp,String oldp) {
			User user  = userDao.findByUsername(username);
			if(user == null) {
				throw new VeException("用户不存在！");
			}
			if(!bCryptPasswordEncoder.encode(oldp).equals(user.getPassword())) {
				throw new VeException("原密码输入错误！");
			}
			String newpass = bCryptPasswordEncoder.encode(newp);
			user.setPassword(newpass);
			userDao.save(user);
	}

	@Override
	public void resetPassword(String username) {
			User user  = userDao.findByUsername(username);
			if(user == null) {
				throw new VeException("用户不存在！");
			}
			String newpass = bCryptPasswordEncoder.encode(user.getUsername()+"123");
			user.setPassword(newpass);
			userDao.save(user);
	}
	
	@Override
	public void remove(List<User> users) {
		for (User user : users) {
			userDao.delete(user);
		}
	}

}
