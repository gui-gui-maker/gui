package com.gui.army.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Car {
	
	@Id
	@Column(name="id",length=32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;//牌照号
	
	private String licence;//牌照号
	
	private String brand;//品牌
	
	private String volume;//排量
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date dateOfManufactrue;//生产日期
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date dateOfRegistration;//上牌日期
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date lastInspectionDate;//检验日期
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public Date getDateOfManufactrue() {
		return dateOfManufactrue;
	}
	
	public void setDateOfManufactrue(Date dateOfManufactrue) {
		this.dateOfManufactrue = dateOfManufactrue;
	}
	
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	
	public Date getLastInspectionDate() {
		return lastInspectionDate;
	}
	
	public void setLastInspectionDate(Date lastInspectionDate) {
		this.lastInspectionDate = lastInspectionDate;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}
	
	
}
