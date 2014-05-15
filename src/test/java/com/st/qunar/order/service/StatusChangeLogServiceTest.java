/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.st.qunar.order.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.st.qunar.order.pojo.AccountConfig;
import com.st.qunar.order.pojo.Data;
import com.st.qunar.order.pojo.OrderStatusUpdateInfo;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class StatusChangeLogServiceTest extends SpringTransactionalTestCase {

	@Autowired
	private StatusChangeLogService statusChangeLogService;

	@Rollback(false)
	@Test
	public void updateDb() throws Exception {
		OrderStatusUpdateInfo orderStatusUpdateInfo = new OrderStatusUpdateInfo();
		Data data = new Data();
		data.setChangeCode("0901");
		data.setOrderDesc("未出票申请退款：订单状态从“支付成功等待出票”变为“未出票申请退款”");
		data.setOrderNo("ygz140410144733048");
		data.setTime("1387876322574");
		data.setTransactionId("tes1312241711047530602");
		orderStatusUpdateInfo.setData(data);
		orderStatusUpdateInfo.setNotifyType("ORDER");
		orderStatusUpdateInfo.setSign("CAFF1E340C511BBDADAC8BD5CE1F91CE");
		orderStatusUpdateInfo.setVersion("1.0");
		statusChangeLogService.updateOrderStatus(orderStatusUpdateInfo);
	}

	@Test
	public void updateHttp() throws Exception {
		// data=com.st.qunar.order.pojo.Data@110bcb82[changeCode=0101,orderDesc=订单状态从“订座成功等待支付”变成“支付成功等待出票”,orderNo=ygz140509165402046,time=1399625811166,transactionId=ygz1405091654020460101],
		// notifyType=ORDER,sign=212EB3E9EE0F8EF9B6D054EF3BDAD2E1,version=1.0]
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		OrderStatusUpdateInfo bean = new OrderStatusUpdateInfo();
		Data data = new Data();
		data.setChangeCode("0101");
		data.setOrderDesc("订单状态从“订座成功等待支付”变成“支付成功等待出票”");
		data.setOrderNo("ygz140509174917705");
		data.setTime("1399629303361");
		data.setTransactionId("ygz1405091749177050101");
		bean.setData(data);

		String dataJsonStr = mapper.toJson(data);
		dataJsonStr = dataJsonStr.replaceAll("time\":\"", "time\":");
		dataJsonStr = dataJsonStr.replaceAll("\",\"transactionId", ",\"transactionId");
		String md5Str = dataJsonStr + "secCode=" + AccountConfig.QUNAR_ORDER_STATUS_UPDATE_SEC_CODE;
		String locSign = DigestUtils.md5Hex(md5Str).toUpperCase();
		// AccountConfig.QUNAR_ORDER_STATUS_UPDATE_SEC_CODE)

		System.out.println("orgStr:" + md5Str);
		System.out.println("locSign:" + locSign);
		System.out.println("qnSign::B3971EE64D7F4F289F2992FFCE43FC85");
		bean.setSign(locSign);
		bean.setVersion("1.0");
		bean.setNotifyType("ORDER");

		// String beanString = mapper.toJson(bean);
		//
		// System.out.println("Bean:" + beanString);// 42.121.4.104
		// String exportContent = Request.Post("http://localhost:9000/qunar/order/status/update")
		// .bodyString(beanString, ContentType.APPLICATION_JSON).execute().returnContent().asString();
		// System.out.println("exportContent:" + exportContent);
	}
}
