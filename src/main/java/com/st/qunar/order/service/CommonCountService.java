package com.st.qunar.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.st.qunar.order.entity.CommonCount;
import com.st.qunar.order.repository.CommonCountDao;

@Component
@Transactional
public class CommonCountService {

	// 通用计数常量，去哪儿订单增量导出最后一个订单ip
	public static final String COMM_COUNT_QN_ORDER_INCR_EXP_LAS_ID = "qunarOrderIncrExportLastId";

	@Autowired
	private CommonCountDao commonCountDao;

	public CommonCount getCommonCountByTypeName(String typeName) {
		return commonCountDao.findByTypeName(typeName);
	}

	public void updateCountByTypeName(String typeName, Long count) {
		commonCountDao.updateCountByTypeName(typeName, count);
	}
}
