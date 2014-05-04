package com.st.qunar.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.st.qunar.order.entity.Order;
import com.st.qunar.order.repository.OrderDao;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public Order getOrder(Long id) {
		return orderDao.findOne(id);
	}

	public void saveOrder(Order entity) {
		orderDao.save(entity);
	}

	public void saveOrders(List<Order> orders) {
		for (Order order : orders) {
			orderDao.save(order);
		}
	}

	public void deleteOrder(Long id) {
		orderDao.delete(id);
	}

	public List<Order> getAllOrder() {
		return (List<Order>) orderDao.findAll();
	}

	public Page<Order> getOrderByStatus(String status, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Order> spec = buildSpecification(status, searchParams);

		return orderDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Order> buildSpecification(String status, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Specification<Order> spec = DynamicSpecifications.bySearchFilter(filters.values(), Order.class);
		return spec;
	}
}
