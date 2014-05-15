/**
 * 
 */
package com.st.qunar.order.pojo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springside.modules.mapper.JsonMapper;

/**
 * @author kxhu
 * 
 */
/*
 * {
 * "data":{
 * "changeCode":"0101",
 * "orderDesc":"订单状态从\\“订座成功等待支付\\”变为\\“支付成功等到出票\\”",
 * "orderNo":"tes131224171104753",
 * "transactionId":"tes1312241711047530602"
 * },
 * "notifyType":"ORDER",
 * "sign":"CAFF1E340C511BBDADAC8BD5CE1F91CE",
 * "version":"1.0"
 * }
 */
public class OrderStatusUpdateInfo {
	// 业务参数
	private Data data;
	// 推送通知类型，为ORDE
	private String notifyType;
	// 签名校验码
	private String sign;
	// 接口版本号，默认为1.0
	private String version;

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

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public boolean signAssertSucc() {
		String beanJsonStr = JsonMapper.nonDefaultMapper().toJson(this.getData());
		beanJsonStr = beanJsonStr.replaceAll("time\":\"", "time\":");
		beanJsonStr = beanJsonStr.replaceAll("\",\"transactionId", ",\"transactionId");
		String locSign = DigestUtils
				.md5Hex(beanJsonStr + "secCode=" + AccountConfig.QUNAR_ORDER_STATUS_UPDATE_SEC_CODE).toUpperCase();
		if (locSign.equalsIgnoreCase(sign)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
