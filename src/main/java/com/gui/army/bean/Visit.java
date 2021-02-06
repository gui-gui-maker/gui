package com.gui.army.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "visit")
public class Visit {

	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
	private Visitor visitor;// 访客
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date estimateTime;
	private Float hours;// 访问时长
	private String reason;// 事由
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
	private Member member;// 被访问人
	// cascade:表的级联操作
	// referencedColumnName：参考列名,默认的情况下是列表的主键
	// nullable=是否可以为空，
	// insertable：是否可以插入，
	// updatable：是否可以更新
	// columnDefinition=列定义，
	// foreignKey=外键
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // JPA注释： 一对一 关系
	@JoinColumn(name = "photo_in_id", referencedColumnName = "id", nullable = true)
	private Photo in;// 图片

	private String remark;// 备注

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // JPA注释： 一对一 关系
	@JoinColumn(name = "photo_out_id", referencedColumnName = "id", nullable = true)
	private Photo out;// 图片

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Date getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(Date estimateTime) {
		this.estimateTime = estimateTime;
	}

	public Float getHours() {
		return hours;
	}

	public void setHours(Float hours) {
		this.hours = hours;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Photo getIn() {
		return in;
	}

	public void setIn(Photo in) {
		this.in = in;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Photo getOut() {
		return out;
	}

	public void setOut(Photo out) {
		this.out = out;
	}
	
	

}
