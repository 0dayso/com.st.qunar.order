package com.st.qunar.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.common.collect.Lists;

/**
 * @author kxhu
 * 
 */
@Entity
@Table(name = "qn_order_passenger")
public class Passenger extends IdEntity {
	// 乘机人姓名
	private String name;
	// 乘机人类型0 为成人,1为儿童
	private String ageType;
	// 乘机人证件类型:NI=身份证,PP=护照,ID=其他,HX=回乡证,TB=台胞证,GA=港澳通行证,HY=国际海员证
	private String cardType;
	// 实际最终售价
	private String price;
	// 报价类型，如：成人、成人套餐、儿童、儿童套餐、未知
	private String priceType;
	// 乘机人证件号
	private String cardNum;
	// 票号
	private String eticketNum;
	// 保险数量
	private String insuranceCount;
	// 保单所在保险公司名字
	private String bxSource;
	// 保单所属保险产品名字
	private String bxName;

	// 乘机人有几份保险有几条
	private List<Insurance> insurances = Lists.newArrayList();

	private Order order;

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeType() {
		return ageType;
	}

	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}

	@XmlAttribute
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@XmlAttribute
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@XmlAttribute
	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@XmlAttribute
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@XmlAttribute
	public String getEticketNum() {
		return eticketNum;
	}

	public void setEticketNum(String eticketNum) {
		this.eticketNum = eticketNum;
	}

	@XmlAttribute
	public String getInsuranceCount() {
		return insuranceCount;
	}

	public void setInsuranceCount(String insuranceCount) {
		this.insuranceCount = insuranceCount;
	}

	@XmlAttribute
	public String getBxSource() {
		return bxSource;
	}

	public void setBxSource(String bxSource) {
		this.bxSource = bxSource;
	}

	@XmlAttribute
	public String getBxName() {
		return bxName;
	}

	public void setBxName(String bxName) {
		this.bxName = bxName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger")
	@XmlElement(name = "insurance")
	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		for (Insurance insurance : insurances) {
			insurance.setPassenger(this);
		}
		this.insurances = insurances;
	}

	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "order_id")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
