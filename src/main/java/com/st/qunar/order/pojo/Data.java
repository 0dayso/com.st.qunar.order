package com.st.qunar.order.pojo;

/**
 * @author kxhu
 * 
 */
public class Data {
	//
	private String changeCode;
	// 订单变化说明：订单从“订座成功等待支付”变为“支付成功等到出票”
	private String orderDesc;
	// TTS 订单号
	private String orderNo;
	// 时间戳
	private String time;
	// 请求标志码:唯一标识，不重复Abc0000000000001
	private String transactionId;

	public String getChangeCode() {
		return changeCode;
	}

	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
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

}
