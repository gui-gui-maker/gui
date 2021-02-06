package com.gui.army.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.army.bean.Car;
import com.gui.army.bean.Car;
import com.gui.army.service.CarManager;
import com.gui.pub.ResultEnum;
import com.gui.pub.ResultVO;
import com.gui.pub.bean.RP;

@RestController
@RequestMapping("car")
public class CarController {

	@Autowired
	CarManager carManager;
	

	@PostMapping(value = "save")
	public Map<String, Object> save(@RequestBody Car car) {
		carManager.save(car);
		return ResultVO.result(ResultEnum.SUCCESS, car);
	}
	
	@PostMapping(value = {"batchSave","edit"})
	public Map<String, Object> batchSave(@RequestBody List<Car> cars) {
		carManager.batchSave(cars);
		return ResultVO.result(ResultEnum.SUCCESS, cars);
	}
	
	@PostMapping(value = "remove")
	public Map<String, Object> remove(@RequestBody List<Car> cars) {
		carManager.remove(cars);
		return ResultVO.result(ResultEnum.SUCCESS, cars);
	}
	
	@RequestMapping(value = "all")
	public Map<String, Object> all(HttpServletRequest request, @RequestBody RP<Car> r) {
		int d = 0;
		try {
			d = Integer.parseInt(r.getDraw());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Car> p = carManager.findAll(r.getStart(), r.getLength(), r.getT());
		
		
		return ResultVO.result(ResultEnum.SUCCESS,p,d);
		
	}
	
}
