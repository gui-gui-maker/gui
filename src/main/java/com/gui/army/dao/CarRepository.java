package com.gui.army.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui.army.bean.Car;
@Repository
public interface CarRepository extends JpaRepository<Car,String>{

	Page<Car> findAll(Specification<Car> specification, Pageable pageable);

}
