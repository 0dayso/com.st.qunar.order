/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.st.qunar.order.web.order;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.st.qunar.order.pojo.OrderStatusUpdateInfo;
import com.st.qunar.order.pojo.OrderStatusUpdateResp;
import com.st.qunar.order.service.StatusChangeLogService;

/**
 * Task管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /task/
 * Create page : GET /task/create
 * Create action : POST /task/create
 * Update page : GET /task/update/{id}
 * Update action : POST /task/update
 * Delete action : GET /task/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	StatusChangeLogService orderStatusService;

	@RequestMapping(value = "status/update")
	@ResponseBody
	public OrderStatusUpdateResp statusUpdate(@RequestBody OrderStatusUpdateInfo orderStatusUpdateInfo)
			throws IOException {
		OrderStatusUpdateResp resp = new OrderStatusUpdateResp();
		if (orderStatusUpdateInfo.signAssertSucc()) {
			resp.setErrMsg("signsucc");
			resp.setResult("SUCCESS");
			// 签名验证成功开始更新数据库对应订单的状态
			orderStatusService.updateOrderStatus(orderStatusUpdateInfo);
		} else {
			resp.setErrMsg("signfail");
			resp.setResult("FAILED");
		}
		resp.setTransactionId(orderStatusUpdateInfo.getData().getTransactionId());
		return resp;
	}

	@RequestMapping(value = "export")
	public void mockQnOrderExportServer(HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		String xml = FileUtils.readFileToString(new File("qn前50个订单.xml"));
		System.out.println("getFile:" + xml);
		pw.write(xml);
		pw.close();
	}

}
