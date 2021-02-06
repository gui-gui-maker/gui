package com.gui.army.bean;
/**
 * 请假外出
 * @author guido
 *
 */

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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Egress {
	@Id
	@Column(name="id",length=32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
	private Member member;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
	private Vacation vacation;//假期类型
	private String reason;//事由
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date expectedTimeOfDeparture;//预计离开时间
	private Integer daysAway;//请假天数
	private String approver;//一级审核人
	private String approverId;//
	private String lastResult;//最终审批结果：1、通过；2、未通过。
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date approveTime;//审批时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date departureTime;//实际离开时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date returnTime;//实际返回时间
	private Integer actualDaysOff;//实际休假天数
	private String state;//状态:未提交、待审核、审核中、已审核、离开、已归返、已销假
	@OneToMany(cascade= {CascadeType.REFRESH},mappedBy="egress",fetch = FetchType.EAGER)
	private List<EgressApprove> approves;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
	private Photo in;//图片
	
	private String remark;//备注
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
	private Photo out;//图片
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<EgressApprove> getApproves() {
		return approves;
	}

	public void setApproves(List<EgressApprove> approves) {
		this.approves = approves;
	}

	public String getId() {
		return id;
	}

	public String getApprover() {
		return approver;
	}
	
	public void setApprover(String approver) {
		this.approver = approver;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Vacation getVacation() {
		return vacation;
	}

	public void setVacation(Vacation vacation) {
		this.vacation = vacation;
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getExpectedTimeOfDeparture() {
		return expectedTimeOfDeparture;
	}
	public void setExpectedTimeOfDeparture(Date expectedTimeOfDeparture) {
		this.expectedTimeOfDeparture = expectedTimeOfDeparture;
	}
	public Integer getDaysAway() {
		return daysAway;
	}
	public void setDaysAway(Integer daysAway) {
		this.daysAway = daysAway;
	}
	
	public String getLastResult() {
		return lastResult;
	}
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public Integer getActualDaysOff() {
		return actualDaysOff;
	}
	public void setActualDaysOff(Integer actualDaysOff) {
		this.actualDaysOff = actualDaysOff;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
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
