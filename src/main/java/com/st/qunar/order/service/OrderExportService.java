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
import org.springframework.stereotype.Component;
import org.springside.modules.mapper.JaxbMapper;

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

	// public OrderExportService(OrderService orderService, CommonCountService commonCountService) {
	// this.orderService = orderService;
	// this.commonCountService = commonCountService;
	// }

	// @Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println("import qunar order running" + " ThreadName:" + threadName);
		while (true) {
			try {
				// 增量导出
				byte[] exportContentBytes = Request
						.Post(AccountConfig.QUNAR_ORDER_EXPORT_URL)
						.bodyForm(
								Form.form()
										.add("use", AccountConfig.QUNAR_ORDER_EXPORT_USER_VALUE)
										.add("pass", AccountConfig.QUNAR_ORDER_EXPORT_PASS_VALUE)
										.add("type", "incr")
										.add("lastId",
												commonCountService.getCommonCountByTypeName(
														CommonCountService.COMM_COUNT_QN_ORDER_INCR_EXP_LAS_ID)
														.getCount()
														+ "").build()).execute().returnContent().asBytes();

				// 导出结果xml转为对象
				Result result = JaxbMapper.fromXml(new String(exportContentBytes, "UTF-8"), Result.class);
				if (!result.getStatus().equals("ok")) {
					String exErrContent = result.getMsg().getContent();
					logger.error("export content error:", exErrContent);
				} else {
					orderService.saveOrders(result.getOrders());
					Long lastId = result.getOrders().get(result.getOrders().size() - 1).getOrderId();
					commonCountService.updateCountByTypeName(CommonCountService.COMM_COUNT_QN_ORDER_INCR_EXP_LAS_ID,
							lastId);
					logger.warn("export ok:\n" + result);
				}
			} catch (IOException e) {
				logger.error("export post request error", e);
				e.printStackTrace();
			}

			try {
				Thread.sleep(5 * 60 * 1000);
			} catch (InterruptedException e) {
				logger.error("import order thread sleep 5mins error", e);
				e.printStackTrace();
			}
		}
	}
}
