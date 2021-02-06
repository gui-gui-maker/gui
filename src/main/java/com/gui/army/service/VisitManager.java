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
import com.gui.army.dao.VisitRepository;

@Service
public class VisitManager {

	@Autowired
	VisitRepository visitRepository;

	@Transactional
	public void save(Visit visit) {
		visitRepository.save(visit);
	}

	@Transactional
	public void batchSave(List<Visit> visits) {
		for (Visit visit : visits) {
			visitRepository.save(visit);
		}
	}

	@Transactional
	public void remove(List<Visit> visits) {
		for (Visit visit : visits) {
			visitRepository.delete(visit);
		}

	}

	@SuppressWarnings("serial")
	public Page<Visit> findAll(Long start, Integer length, Visit visit) {
		long page = start / length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
		// Sort sort = new Sort(Sort.Direction.DESC, "id"); //
		// 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int) page - 1, length, Sort.Direction.ASC, "id");// PageRequest((int)page -
																								// 1, length, sort); //
																								// （当前页， 每页记录数， 排序方式）

		Page<Visit> p = visitRepository.findAll(new Specification<Visit>() {

			@Override
			public Predicate toPredicate(Root<Visit> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();

				// 访客
				Join<Object, Object> joinV = root.join("visitor", JoinType.LEFT);
				if (visit.getVisitor() != null && !StringUtils.isEmpty(visit.getVisitor().getName())) {
					list.add(criteriaBuilder.like(joinV.get("visitor").get("name").as(String.class),
							"%" + visit.getVisitor().getName() + "%"));
				}
				if (visit.getVisitor() != null && !StringUtils.isEmpty(visit.getVisitor().getId())) {
					list.add(criteriaBuilder.like(joinV.get("visitor").get("id").as(String.class),
							"%" + visit.getVisitor().getId() + "%"));
				}
				// 被访人
				Join<Object, Object> joinM = root.join("member", JoinType.LEFT);
				if (visit.getMember() != null && !StringUtils.isEmpty(visit.getMember().getName())) {
					list.add(criteriaBuilder.like(joinM.get("visitor").get("name").as(String.class),
							"%" + visit.getVisitor().getName() + "%"));
				}
				if (visit.getMember() != null && !StringUtils.isEmpty(visit.getMember().getId())) {
					list.add(criteriaBuilder.like(joinM.get("member").get("id").as(String.class),
							"%" + visit.getMember().getId() + "%"));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		}, pageable);

		return p;
	}

}
