/*******************************************************************************
 * Copyright (c) 2005, 2014 st
 *
 *******************************************************************************/
package com.st.qunar.order.entity;

/**
 * @author kxhu
 * 
 */
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;

/**
 * 使用JAXB2.0标注的待转换Java Bean.
 */
// 根节点
@XmlRootElement
public class Result {
	// 接口返回相应的状态,ok 是成功,error是失败
	private String status;
	// 返回成功无此节点
	private Msg msg;
	// 订单信息节点
	private List<Order> orders = Lists.newArrayList();

	@XmlAttribute
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement
	public Msg getMsg() {
		return msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	// @XmlTransient
	@XmlElement(name = "order")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
