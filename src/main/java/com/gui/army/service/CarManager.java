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
import com.gui.army.bean.Car;
import com.gui.army.dao.CarRepository;

@Service
public class CarManager {

	@Autowired
	CarRepository carRepository;
	
	@Transactional
	public void save(Car car) {
		carRepository.save(car);
	}

	@Transactional
	public void batchSave(List<Car> cars) {
		for (Car car : cars) {
			carRepository.save(car);
		}
	}

	@Transactional
	public void remove(List<Car> cars) {
		for (Car car : cars) {
			carRepository.delete(car);
		}

	}

	@SuppressWarnings("serial")
	public Page<Car> findAll(Long start, Integer length, Car car) {
		long page = start / length;
		page++;
		// 排序方式，这里是以“recordNo”为标准进行降序
		// Sort sort = new Sort(Sort.Direction.DESC, "id"); //
		// 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest((int) page - 1, length, Sort.Direction.ASC, "id");// PageRequest((int)page -
																								// 1, length, sort); //
																								// （当前页， 每页记录数， 排序方式）

		Page<Car> p = carRepository.findAll(new Specification<Car>() {

			@Override
			public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();

				
				if (!StringUtils.isEmpty(car.getLicence())) {
					list.add(criteriaBuilder.like(root.get("licence").as(String.class),
							"%" + car.getLicence() + "%"));
				}
				if (!StringUtils.isEmpty(car.getBrand())) {
					list.add(criteriaBuilder.like(root.get("brand").as(String.class),
							"%" + car.getBrand() + "%"));
				}
				// 被访人
				
				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		}, pageable);

		return p;
	}
	
}
