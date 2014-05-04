/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.st.qunar.order.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.st.qunar.order.pojo.Data;
import com.st.qunar.order.pojo.OrderStatusUpdateInfo;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class StatusChangeLogServiceTest extends SpringTransactionalTestCase {

	@Autowired
	private StatusChangeLogService statusChangeLogService;

	@Rollback(false)
	@Test
	public void update() throws Exception {
		OrderStatusUpdateInfo orderStatusUpdateInfo = new OrderStatusUpdateInfo();
		Data data = new Data();
		data.setChangeCode("0201");
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
}
