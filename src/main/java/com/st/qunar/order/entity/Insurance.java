package com.st.qunar.order.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author kxhu
 * 
 */
@Entity
@Table(name = "qn_order_insurance")
public class Insurance extends IdEntity {
	// 保险单号null表示没有保单号数字表示有保单号
	private String insuranceNo;
	// 仅往返订单显示，第一程或第二程
	private String bxFlight;
	// 保险状态：待支付,出保中,已出保险,保单取消等待退款,退保成功等待退款,退款完成,已拆分
	private String bxStatus;

	private Passenger passenger;

	@XmlAttribute
	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	@XmlAttribute
	public String getBxFlight() {
		return bxFlight;
	}

	public void setBxFlight(String bxFlight) {
		this.bxFlight = bxFlight;
	}

	@XmlAttribute
	public String getBxStatus() {
		return bxStatus;
	}

	public void setBxStatus(String bxStatus) {
		this.bxStatus = bxStatus;
	}

	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "passenger_id")
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
}
