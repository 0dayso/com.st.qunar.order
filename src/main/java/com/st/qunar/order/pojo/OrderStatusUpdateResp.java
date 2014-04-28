/**
 * 
 */
package com.st.qunar.order.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author kxhu
 * 
 */

public class OrderStatusUpdateResp {
	private String transactionId;
	private String result;
	private String errMsg;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
