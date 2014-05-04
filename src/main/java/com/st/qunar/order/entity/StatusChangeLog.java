package com.st.qunar.order.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author kxhu
 * 
 */
@Entity
@Table(name = "qn_order_status_change_log")
public class StatusChangeLog extends IdEntity {
	// 推送类型
	private String notifyType;
	// 签名
	private String sign;
	// 接口版本
	private String version;
	// 订单变化编码
	private String changeCode;
	// 变化前状态
	private String beforeStatus;
	// 变化后状态
	private String afterStatus;
	// 订单变化说明
	private String orderDesc;
	// 订单号
	private String orderNo;
	// 时间戳
	private String time;
	// 请求标志码
	private String transactionId;

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChangeCode() {
		return changeCode;
	}

	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}

	public String getBeforeStatus() {
		return beforeStatus;
	}

	public void setBeforeStatus(String beforeStatus) {
		this.beforeStatus = beforeStatus;
	}

	public String getAfterStatus() {
		return afterStatus;
	}

	public void setAfterStatus(String afterStatus) {
		this.afterStatus = afterStatus;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
