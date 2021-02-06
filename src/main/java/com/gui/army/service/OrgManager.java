package com.gui.army.service;

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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gui.army.bean.Member;
import com.gui.army.bean.Org;
import com.gui.army.dao.MemberRepository;
import com.gui.army.dao.OrgRepository;
import com.gui.exception.VeException;
import com.gui.hik.service.HikCameraManager;

@Service
public class OrgManager {
	
	@Autowired
	OrgRepository orgRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	HikCameraManager hikCameraManager;

	public Page<Org> findAll(Long start, Integer length, Org organization) {
		long page = start/length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
        //Sort sort = new Sort(Sort.Direction.DESC, "id");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int)page - 1, length, Sort.Direction.ASC, "id");//PageRequest((int)page - 1, length, sort); // （当前页， 每页记录数， 排序方式）
        
        @SuppressWarnings("serial")
		Page<Org> p = orgRepository.findAll(new Specification<Org>(){
            

			@Override
            public Predicate toPredicate(Root<Org> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            	 List<Predicate> list = new ArrayList<Predicate>();
                 
                 if(!StringUtils.isEmpty(organization.getName())){
                	 list.add(criteriaBuilder.equal(root.get("name").as(String.class), organization.getName()));
                 }
                 
                 Predicate[] p = new Predicate[list.size()];
                 return criteriaBuilder.and(list.toArray(p));
            }


        },pageable);
		return p;
	}
	
	public List<Org> findByLevelCode(String level){
		
		return orgRepository.findByLevelCode(level);
	}

	public List<Org> findAll() {
		// TODO Auto-generated method stub
		return orgRepository.findAll();
	}
	@Transactional
	public void save(Org organization) {
		// TODO Auto-generated method stub
		orgRepository.save(organization);
	}

	@Transactional
	public void remove(String id)  throws Exception {
		
		List<Member> members = memberRepository.findByOrganizationId(id);
		if(members.size()>0) {
			throw new VeException("部门下有人员，不能删除。");
		}
		orgRepository.deleteById(id);
		
	}
	
	@Transactional
	public void save(List<Org> organizations) throws Exception{
		JSONArray ja = JSONArray.parseArray(JSON.toJSONString(organizations)) ;
		//保存到hik
		hikCameraManager.cameraPreviewURL("/api/resource/v1/org/batch/add",ja.toJSONString());
		//保存到本地数据库
		for (Org org : organizations) {
			orgRepository.save(org);
		}
	}
}
