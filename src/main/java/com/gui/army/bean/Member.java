package com.gui.army.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gui.security.bean.User;

/**
 * 内部人员信息
 * 
 * @author guido
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true,value = {"egresses","users","vacations","visits"})
public class Member {

	@Id
	@Column(name = "id", length = 18)
	@GeneratedValue(generator = "g-assigned")
	@GenericGenerator(name = "g-assigned", strategy = "assigned")
	private String id;// 居民身份证
	private String name;// 姓名
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)//保存member时，不对organization作任何操作
	private Org org;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date birth;// 出生年月
	private String sex;// 性别
	private String nation;// 民族
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date timeOfEnlistment;// 入伍时间
	private String placeOfEnlistment;// 入伍地
	private String militaryRank;// 军衔
	private String position;// 职位
	private String politicCountenace;// 政治面貌
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date timeOfJoining;// 入团、党时间
	private String levelOfEducation;// 文化程度
	private String nativePlace;// 籍贯
	private String certificate;// 军人身份证

	private String address;// 家庭住址
	private String phone;// 联系电话
	private String resume;// 简历
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
	private List<Egress> egresses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
	private List<User> users;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
	private List<Vacation> vacations;
	
	@OneToMany(cascade= {CascadeType.REFRESH }, mappedBy="member")
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getPlaceOfEnlistment() {
		return placeOfEnlistment;
	}

	public void setPlaceOfEnlistment(String placeOfEnlistment) {
		this.placeOfEnlistment = placeOfEnlistment;
	}

	public Date getTimeOfEnlistment() {
		return timeOfEnlistment;
	}

	public void setTimeOfEnlistment(Date timeOfEnlistment) {
		this.timeOfEnlistment = timeOfEnlistment;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMilitaryRank() {
		return militaryRank;
	}

	public void setMilitaryRank(String militaryRank) {
		this.militaryRank = militaryRank;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPoliticCountenace() {
		return politicCountenace;
	}

	public void setPoliticCountenace(String politicCountenace) {
		this.politicCountenace = politicCountenace;
	}

	public Date getTimeOfJoining() {
		return timeOfJoining;
	}

	public void setTimeOfJoining(Date timeOfJoining) {
		this.timeOfJoining = timeOfJoining;
	}

	public String getLevelOfEducation() {
		return levelOfEducation;
	}

	public void setLevelOfEducation(String levelOfEducation) {
		this.levelOfEducation = levelOfEducation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<Egress> getEgresses() {
		return egresses;
	}

	public void setEgresses(List<Egress> egresses) {
		this.egresses = egresses;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Vacation> getVacations() {
		return vacations;
	}

	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}


	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
	
	

}
