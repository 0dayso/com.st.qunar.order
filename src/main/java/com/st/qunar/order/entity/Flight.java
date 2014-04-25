package com.st.qunar.order.entity;

import javax.persistence.CascadeType;
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
@Table(name = "qn_order_flight")
public class Flight extends IdEntity {
	// 航班号
	private String code;
	// 舱位代码
	private String cabin;
	// 儿童舱位
	private String ccabin;
	// 起飞城市三字码
	private String dep;
	// 到达城市三字码
	private String arr;
	// 出发日期
	private String depDay;
	// 出发时间
	private String depTime;
	// 到达时间
	private String arrTime;
	// 真实航班号,若不为空则是共享航班
	private String realCode;

	private Order order;

	@XmlAttribute
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlAttribute
	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	@XmlAttribute
	public String getCcabin() {
		return ccabin;
	}

	public void setCcabin(String ccabin) {
		this.ccabin = ccabin;
	}

	@XmlAttribute
	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	@XmlAttribute
	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	@XmlAttribute
	public String getDepDay() {
		return depDay;
	}

	public void setDepDay(String depDay) {
		this.depDay = depDay;
	}

	@XmlAttribute
	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	@XmlAttribute
	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	@XmlAttribute
	public String getRealCode() {
		return realCode;
	}

	public void setRealCode(String realCode) {
		this.realCode = realCode;
	}

	@XmlTransient
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "order_id")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
