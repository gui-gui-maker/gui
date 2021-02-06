package com.gui.army.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 访客
 * @author guido
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true,value= {"visits"})
public class Visitor {
	@Id
	@Column(name="id",length=18)
	@GeneratedValue(generator = "g-assigned")
    @GenericGenerator(name = "g-assigned", strategy = "assigned")
	private String id;//身份证号
	
	private String name;//姓名
	
	private String sex;//性别
	
	private String phone;//电话
	
	@OneToMany(cascade= {CascadeType.REFRESH},mappedBy="visitor")
	private List<Visit> visits;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	
	

}
