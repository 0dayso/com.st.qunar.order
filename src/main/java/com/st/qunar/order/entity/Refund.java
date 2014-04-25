package com.st.qunar.order.entity;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author kxhu
 * 
 */
@Embeddable
public class Refund {
	// 退款时间，指定单变为退款完成的时间
	private String refundTime;
	// 退款金额
	private String refundPrice;
	// 支付中心退款流水号
	private String refundNo;
	// 退款类型，分为线上退款和线下退款
	private String refundType;

	@XmlAttribute(name = "refund_time")
	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	@XmlAttribute(name = "refund_price")
	public String getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(String refundPrice) {
		this.refundPrice = refundPrice;
	}

	@XmlAttribute(name = "refund_no")
	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	@XmlAttribute(name = "type")
	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

}
