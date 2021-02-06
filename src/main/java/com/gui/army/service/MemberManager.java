package com.gui.army.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.gui.army.bean.Member;
import com.gui.army.dao.MemberRepository;
@Service
public class MemberManager {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> findAll(){
	
		return memberRepository.findAll();
	}

	public Page<Member> findAll(Long start, Integer length, Member member) {
		long page = start/length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
        //Sort sort = new Sort(Sort.Direction.DESC, "id");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int)page - 1, length, Sort.Direction.ASC, "id");//PageRequest((int)page - 1, length, sort); // （当前页， 每页记录数， 排序方式）
        
        Page<Member> p = memberRepository.findAll(new Specification<Member>(){
            

			@Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            	 List<Predicate> list = new ArrayList<Predicate>();
                 if(!StringUtils.isEmpty(member.getId().toString())){
                     list.add(criteriaBuilder.equal(root.get("id").as(String.class), member.getId()));
                 }
                 if(!StringUtils.isEmpty(member.getName())){
                	 list.add(criteriaBuilder.equal(root.get("name").as(String.class), member.getName()));
                 }
                 
                 Predicate[] p = new Predicate[list.size()];
                 return criteriaBuilder.and(list.toArray(p));
            }


        },pageable);


        
		return p;
	}

	public void save(Member member) {
		
		memberRepository.save(member);
	}
	
	@Transactional
	public void remove(List<Member> members) throws Exception{
		for (Member member : members) {
			memberRepository.delete(member);
		}
	}

	@Transactional
	public void save(List<Member> members) throws Exception{
		for (Member member : members) {
			memberRepository.save(member);
		}
	}

	public List<Map<String, Object>> findMemberOptions() {
			String sql = "select name as label,id as value from member ";
			return jdbcTemplate.query(sql,new Object[] {},  new RowMapper<Map<String,Object>>(){
				@Override
				public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("label", rs.getString("label"));
					map.put("value", rs.getString("value"));
					return map;
				}
			});
	}



}
