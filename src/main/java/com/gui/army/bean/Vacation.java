package com.gui.army.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 假期，每个人在每一年有哪些假期
 * 
 * @author guido
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true,value = {"egresses"})
public class Vacation {

	@Id
	@Column(name="id",length=32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String year;// 年份
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	private Member member;
	private String holidayType; // 事假、年假、病假、婚假、临时外出
	private Integer vacationDays; // 假期天数
	private Integer daysOff; // 已经休假的天数
	private Integer vacationRemain; // 剩余假期
	@OneToMany(cascade=CascadeType.DETACH,mappedBy="vacation")//游离状态
	private List<Egress> egresses;

	

	public String getHolidayType() {
		return holidayType;
	}

	public Integer getDaysOff() {
		return daysOff;
	}

	public void setDaysOff(Integer daysOff) {
		this.daysOff = daysOff;
	}

	public Integer getVacationRemain() {
		return vacationRemain;
	}

	public void setVacationRemain(Integer vacationRemain) {
		this.vacationRemain = vacationRemain;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVacationDays() {
		return vacationDays;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setVacationDays(Integer vacationDays) {
		this.vacationDays = vacationDays;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Egress> getEgresses() {
		return egresses;
	}

	public void setEgresses(List<Egress> egresses) {
		this.egresses = egresses;
	}
	
	

}
