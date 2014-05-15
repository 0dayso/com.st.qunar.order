/**
 * 
 */
package com.st.qunar.order.service;

import java.io.IOException;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springside.modules.mapper.JaxbMapper;

import com.st.qunar.order.entity.Order;
import com.st.qunar.order.entity.Result;
import com.st.qunar.order.pojo.AccountConfig;

/**
 * @author kxhu
 * 
 */
@Component
@Lazy(value = false)
public class OrderExportService {

	private static Logger logger = LoggerFactory.getLogger(OrderExportService.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private CommonCountService commonCountService;

	private int exportCount = 0;

	@Scheduled(fixedDelay = 5 * 60 * 1000)
	public void incrExport() {
		System.out.println("import qunar order count:" + exportCount++);
		try {
			// 增量导出
			byte[] exportContentBytes = Request
					.Post(AccountConfig.QUNAR_ORDER_EXPORT_URL)
					.bodyForm(
							Form.form()
									.add("user", AccountConfig.QUNAR_ORDER_EXPORT_USER_VALUE)
									.add("pass", AccountConfig.QUNAR_ORDER_EXPORT_PASS_VALUE)
									.add("type", "incr")
									.add("lastId",
											commonCountService.getCommonCountByTypeName(
													CommonCountService.COMM_COUNT_QN_ORDER_INCR_EXP_LAS_ID).getCount()
													+ "").build()).execute().returnContent().asBytes();
			logger.warn("export resp:\n" + new String(exportContentBytes, "UTF-8"));
			// 导出结果xml转为对象
			Result result = JaxbMapper.fromXml(new String(exportContentBytes, "UTF-8"), Result.class);
			if (!result.getStatus().equals("ok")) {
				logger.error("export content error:", result.getMsg().getContent());
			} else {
				orderService.saveOrders(result.getOrders());
				Long lastId = result.getOrders().get(result.getOrders().size() - 1).getOrderId();
				commonCountService
						.updateCountByTypeName(CommonCountService.COMM_COUNT_QN_ORDER_INCR_EXP_LAS_ID, lastId);
				logger.warn("export ok");
			}
		} catch (IOException e) {
			logger.error("export post request error", e);
			e.printStackTrace();
		}
	}

	public Order exactExport(String orderNo) {
		System.out.println("import qunar order exact type:");
		Order order = null;
		try {
			// 精确导出
			byte[] exportContentBytes = Request
					.Post(AccountConfig.QUNAR_ORDER_EXPORT_URL)
					.bodyForm(
							Form.form().add("user", AccountConfig.QUNAR_ORDER_EXPORT_USER_VALUE)
									.add("pass", AccountConfig.QUNAR_ORDER_EXPORT_PASS_VALUE).add("type", "exact")
									.add("orderNo", orderNo).build()).execute().returnContent().asBytes();
			logger.warn("exact export resp:\n" + new String(exportContentBytes, "UTF-8"));
			// 导出结果xml转为对象
			Result result = JaxbMapper.fromXml(new String(exportContentBytes, "UTF-8"), Result.class);
			if (!result.getStatus().equals("ok")) {
				logger.error("exact export content error:", result.getMsg().getContent());
			} else {
				if ((result.getOrders() != null) && (result.getOrders().size() == 1)) {
					order = result.getOrders().get(0);
				} else {
					logger.warn("exact export order null or more then one " + orderNo);
				}
				logger.warn("exact export ok");
			}
		} catch (IOException e) {
			logger.error("exact export post request error", e);
			e.printStackTrace();
		}
		return order;
	}

	public void allExport() {
		System.out.println("import qunar order all type:");
		try {
			// 全量导出
			byte[] exportContentBytes = Request
					.Post(AccountConfig.QUNAR_ORDER_EXPORT_URL)
					.connectTimeout(10 * 60 * 1000)
					.socketTimeout(10 * 60 * 1000)
					.bodyForm(
							Form.form().add("user", AccountConfig.QUNAR_ORDER_EXPORT_USER_VALUE)
									.add("pass", AccountConfig.QUNAR_ORDER_EXPORT_PASS_VALUE).add("type", "all")
									.build()).execute().returnContent().asBytes();
			logger.warn("all export resp:\n" + new String(exportContentBytes, "UTF-8"));
			// 导出结果xml转为对象
			Result result = JaxbMapper.fromXml(new String(exportContentBytes, "UTF-8"), Result.class);
			if (!result.getStatus().equals("ok")) {
				logger.error("all export content error:", result.getMsg().getContent());
			} else {
				orderService.saveOrders(result.getOrders());
				Long lastId = result.getOrders().get(result.getOrders().size() - 1).getOrderId();
				commonCountService
						.updateCountByTypeName(CommonCountService.COMM_COUNT_QN_ORDER_INCR_EXP_LAS_ID, lastId);
				logger.warn("all export ok");
			}
		} catch (IOException e) {
			logger.error("all export post request error", e);
			e.printStackTrace();
		}
	}
}
