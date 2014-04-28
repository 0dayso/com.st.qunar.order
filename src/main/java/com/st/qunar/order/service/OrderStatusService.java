package com.st.qunar.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.st.qunar.order.entity.Order;
import com.st.qunar.order.pojo.OrderStatus;
import com.st.qunar.order.pojo.OrderStatusUpdateInfo;
import com.st.qunar.order.repository.OrderDao;

@Component
@Transactional
public class OrderStatusService extends OrderService {
	@Autowired
	OrderDao orderDao;

	public void updateStatusByOrderNo(String status, String orderNo) {
		orderDao.updateStatusByOrderNo(status, orderNo);
	}

	/*
	 * 根据订单订单状态变更码做相应逻辑处理
	 */
	public void updateOrderStatus(OrderStatusUpdateInfo orderStatusUpdateInfo) {
		String changeCode = orderStatusUpdateInfo.getData().getChangeCode();
		String orderNo = orderStatusUpdateInfo.getData().getOrderNo();
		Order order = orderDao.findByOrderNo(orderNo);
		switch (changeCode) {
		case "0101":
			updateStatusByOrderNo(OrderStatus.statusChange.get("0101").values().iterator().next(), orderNo);
		case "0102":
			System.out.println(orderStatusUpdateInfo.getData().getOrderDesc());
		default:

		}
	}

}
