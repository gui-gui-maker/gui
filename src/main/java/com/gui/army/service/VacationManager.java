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
import com.gui.army.bean.Member;
import com.gui.army.bean.Vacation;
import com.gui.army.dao.VacationRepository;

@Service
public class VacationManager {
	@Autowired
	VacationRepository vacationRepository;
	
	public Page<Vacation> findAll(Long start, Integer length, Vacation vacation) {
		long page = start/length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
        //Sort sort = new Sort(Sort.Direction.DESC, "id");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int)page - 1, length, Sort.Direction.ASC, "id");//PageRequest((int)page - 1, length, sort); // （当前页， 每页记录数， 排序方式）
        
        @SuppressWarnings("serial")
		Page<Vacation> p = vacationRepository.findAll(new Specification<Vacation>(){
            

			@Override
            public Predicate toPredicate(Root<Vacation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            	 List<Predicate> list = new ArrayList<Predicate>();
            	 Join<Object,Object> vacationJoin = root.join("member", JoinType.LEFT);
            	 
                 if(null!=vacation.getMember()&&!StringUtils.isEmpty(vacation.getMember().getId())){
                	 list.add(criteriaBuilder.like(vacationJoin.get("member").get("id").as(String.class), "%"+vacation.getMember().getId()+"%"));
                 }
                 
                 if(null!=vacation.getMember()&&!StringUtils.isEmpty(vacation.getMember().getName())){
                	 list.add(criteriaBuilder.like(vacationJoin.get("member").get("name").as(String.class), "%"+vacation.getMember().getName()+"%"));
                 }
                 
                 Predicate[] p = new Predicate[list.size()];
                 return criteriaBuilder.and(list.toArray(p));
            }


        },pageable);


        
		return p;
	}

	@Transactional
	public void save(List<Vacation> vacations) {
		for (Vacation vacation : vacations) {
			vacationRepository.save(vacation);
		}
		
	}

	@Transactional
	public void remove(List<Vacation> vacations) {
		for (Vacation vacation : vacations) {
			vacationRepository.delete(vacation);
		}
		
	}

	public List<Vacation> findByYearAndMember(String year, Member member) {
		// TODO Auto-generated method stub
		return vacationRepository.findByYearAndMember(year,member);
	}

}
