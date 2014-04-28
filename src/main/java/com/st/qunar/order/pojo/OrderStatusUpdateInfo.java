/**
 * 
 */
package com.st.qunar.order.pojo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springside.modules.mapper.JsonMapper;

import com.st.qunar.order.utilities.MD5;

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
		String beanJsonStr = JsonMapper.nonDefaultMapper().toJson(this);
		String locSign = MD5.MD5Encode(beanJsonStr + "secCode=" + "");
		if (locSign.equalsIgnoreCase(sign)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		OrderStatusUpdateInfo bean = new OrderStatusUpdateInfo();
		Data data = new Data();
		data.setChangeCode("0101");
		data.setOrderDesc("订单状态从“订座成功等待支付”变为“支付成功等到出票”");
		data.setOrderNo("tes131224171104753");
		data.setTransactionId("1387876322574");
		data.setTransactionId("tes1312241711047530602");
		bean.setData(data);
		bean.setNotifyType("ORDER");
		bean.setSign("CAFF1E340C511BBDADAC8BD5CE1F91CE");
		bean.setVersion("1.0");
		String beanString = mapper.toJson(bean);
		System.out.println("Bean:" + beanString);
		// String exportContent = Request.Post("http://localhost:8080/qunar/order/status/update")
		// .bodyString(beanString, ContentType.APPLICATION_JSON).execute().returnContent().asString();
		// System.out.println("exportContent:" + exportContent);
	}

}
