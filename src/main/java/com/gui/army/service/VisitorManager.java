package com.gui.army.service;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.gui.army.bean.Visit;
import com.gui.army.bean.Visitor;
import com.gui.army.dao.VisitorRepository;

@Service
public class VisitorManager {

	@Autowired
	VisitorRepository visitorRepository;
	
	@Transactional
	public void save(Visitor visitor) {
		visitorRepository.save(visitor);
	}

	@Transactional
	public void batchSave(List<Visitor> visitors) {
		for (Visitor visitor : visitors) {
			visitorRepository.save(visitor);
		}
	}

	@Transactional
	public void remove(List<Visitor> visitors) {
		for (Visitor visitor : visitors) {
			visitorRepository.delete(visitor);
		}

	}

	@SuppressWarnings("serial")
	public Page<Visitor> findAll(Long start, Integer length, Visitor visitor) {
		long page = start / length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
		// Sort sort = new Sort(Sort.Direction.DESC, "id"); //
		// 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int) page - 1, length, Sort.Direction.ASC, "id");// PageRequest((int)page -
																								// 1, length, sort); //
																								// （当前页， 每页记录数， 排序方式）

		Page<Visitor> p = visitorRepository.findAll(new Specification<Visitor>() {

			@Override
			public Predicate toPredicate(Root<Visitor> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();

				
				if (!StringUtils.isEmpty(visitor.getId())) {
					list.add(criteriaBuilder.like(root.get("id").as(String.class),
							"%" + visitor.getId() + "%"));
				}
				if (!StringUtils.isEmpty(visitor.getName())) {
					list.add(criteriaBuilder.like(root.get("name").as(String.class),
							"%" + visitor.getName() + "%"));
				}
				// 被访人
				
				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		}, pageable);

		return p;
	}
}
