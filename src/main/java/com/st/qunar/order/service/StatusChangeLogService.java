package com.st.qunar.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.st.qunar.order.entity.Order;
import com.st.qunar.order.entity.StatusChangeLog;
import com.st.qunar.order.pojo.OrderStatus;
import com.st.qunar.order.pojo.OrderStatusUpdateInfo;
import com.st.qunar.order.repository.OrderDao;
import com.st.qunar.order.repository.StatusChangeLogDao;

@Component
@Transactional
public class StatusChangeLogService extends OrderService {
	@Autowired
	OrderDao orderDao;

	@Autowired
	StatusChangeLogDao statusChangeLogDao;

	public void updateStatusByOrderNo(String status, String orderNo) {
		orderDao.updateStatusByOrderNo(status, orderNo);
	}

	/*
	 * 根据订单订单状态变更码做相应逻辑处理
	 */
	public void updateOrderStatus(OrderStatusUpdateInfo orderStatusUpdateInfo) {
		StatusChangeLog orderStatusUpdate = new StatusChangeLog();
		orderStatusUpdate.setNotifyType(orderStatusUpdateInfo.getNotifyType());
		orderStatusUpdate.setSign(orderStatusUpdateInfo.getSign());
		orderStatusUpdate.setVersion(orderStatusUpdateInfo.getVersion());
		orderStatusUpdate.setChangeCode(orderStatusUpdateInfo.getData().getChangeCode());
		orderStatusUpdate.setOrderDesc(orderStatusUpdateInfo.getData().getOrderDesc());
		orderStatusUpdate.setOrderNo(orderStatusUpdateInfo.getData().getOrderNo());
		orderStatusUpdate.setTransactionId(orderStatusUpdateInfo.getData().getTransactionId());
		orderStatusUpdate.setTime(orderStatusUpdateInfo.getData().getTime());

		String changeCode = orderStatusUpdateInfo.getData().getChangeCode();
		String orderNo = orderStatusUpdateInfo.getData().getOrderNo();
		Order order = orderDao.findByOrderNo(orderNo);
		if (changeCode.equals("0101") || changeCode.equals("0102") || changeCode.equals("0103")
				|| changeCode.equals("0104") || changeCode.equals("0201") || changeCode.equals("0202")
				|| changeCode.equals("0301") || changeCode.equals("0401") || changeCode.equals("0601")
				|| changeCode.equals("0602") || changeCode.equals("0701") || changeCode.equals("0801")
				|| changeCode.equals("0802") || changeCode.equals("0803") || changeCode.equals("0804")) {
			updateStatusByOrderNo(OrderStatus.statusChange.get(changeCode).getAfterStatus(), orderNo);
		} else if (changeCode.equals("0501") || changeCode.equals("0502") || changeCode.equals("0901")) {
			System.out.println(orderStatusUpdateInfo.getData().getOrderDesc());
		} else {
			System.out.println("not def change code");
		}

		orderStatusUpdate.setBeforeStatus(OrderStatus.statusChange.get(changeCode).getBeforeStatus());
		orderStatusUpdate.setAfterStatus(OrderStatus.statusChange.get(changeCode).getAfterStatus());
		statusChangeLogDao.save(orderStatusUpdate);
	}
}
