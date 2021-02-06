package com.gui.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.gui.security.bean.Role;
import com.gui.security.dao.RoleRepository;

@Service
public class RoleManager {
	
	@Autowired
	RoleRepository roleRepository;

	@Transactional
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Transactional
	public void batchSave(List<Role> roles) {
		for (Role role : roles) {
			roleRepository.save(role);
		}
	}

	@Transactional
	public void remove(List<Role> roles) {
		for (Role role : roles) {
			roleRepository.delete(role);
		}
		
	}

	public Page<Role> findAll(Long start, Integer length, Role role) {
		long page = start / length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
		// Sort sort = new Sort(Sort.Direction.DESC, "id"); //
		// 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int) page - 1, length, Sort.Direction.ASC, "id");// PageRequest((int)page -
																								// 1, length, sort); //
																								// （当前页， 每页记录数， 排序方式）

		Page<Role> p = roleRepository.findAll(new Specification<Role>() {

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();

				//编码
				if (!StringUtils.isEmpty(role.getCode())) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class),"%"+ role.getCode()+"%"));
				}
				// 角色名称
				if (!StringUtils.isEmpty(role.getName())) {
					list.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+ role.getName()+"%"));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		}, pageable);

		return p;
	}

}
