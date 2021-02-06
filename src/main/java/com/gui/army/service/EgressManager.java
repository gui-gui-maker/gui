package com.gui.army.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
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
import com.gui.army.bean.Egress;
import com.gui.army.bean.Member;
import com.gui.army.dao.EgressRepository;
import com.gui.army.dao.MemberRepository;

@Service
public class EgressManager {
	
	@Autowired 
	EgressRepository egressRepository;
	
	@Autowired 
	MemberRepository memberRepository;
	
	public Page<Egress> findAll(Long start, Integer length,Egress egress) {
		long page = start/length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
        //Sort sort = new Sort(Sort.Direction.DESC, "id");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int)page - 1, length, Sort.Direction.ASC, "id");//PageRequest((int)page - 1, length, sort); // （当前页， 每页记录数， 排序方式）
        
        Page<Egress> p = egressRepository.findAll(new Specification<Egress>(){
            

			@Override
            public Predicate toPredicate(Root<Egress> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            	 List<Predicate> list = new ArrayList<Predicate>();
            	 //休假类型
					/*
					 * if(!StringUtils.isEmpty(egress.getVacation().getHolidayType())){
					 * list.add(criteriaBuilder.equal(root.get("holidayType").as(String.class),
					 * egress.getVacation().getHolidayType())); }
					 */
                 //姓名
                 Join<Object,Object> egressJoin = root.join("member", JoinType.LEFT);
                 if(egress.getMember()!=null && !StringUtils.isEmpty(egress.getMember().getName())){
                	 list.add(criteriaBuilder.like(egressJoin.get("member").get("name").as(String.class), "%"+egress.getMember().getName()+"%"));
                 }
                 //身份证
                 if(egress.getMember()!=null && !StringUtils.isEmpty(egress.getMember().getId())){
                	 list.add(criteriaBuilder.like(egressJoin.get("member").get("id").as(String.class), "%"+egress.getMember().getId()+"%"));
                 }
                 //开始时间
					/*
					 * if(startTime!=null){
					 * list.add(criteriaBuilder.greaterThan(root.get("departureTime").as(Date.class)
					 * , startTime)); }
					 */
                 //结束时间
					/*
					 * if(endTime!=null){
					 * list.add(criteriaBuilder.lessThan(root.get("departureTime").as(Date.class),
					 * endTime)); }
					 */
                 
                 Predicate[] p = new Predicate[list.size()];
                 return criteriaBuilder.and(list.toArray(p));
            }


        },pageable);


        
		return p;
	}

	public void save(Egress egress) {
		
		egressRepository.save(egress);
		
	}

	@Transactional
	public void save(List<Egress> egresses) throws Exception{
		for (Egress egress : egresses) {
			Member m = memberRepository.findById(egress.getApproverId()).get();
			egress.setApprover(m.getName());
			egressRepository.save(egress);
		}
	}
	

}
