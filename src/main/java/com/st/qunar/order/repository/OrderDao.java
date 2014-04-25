package com.st.qunar.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.st.qunar.order.entity.Order;
import com.st.qunar.order.entity.Task;

public interface OrderDao extends PagingAndSortingRepository<Order, Long>, JpaSpecificationExecutor<Order> {
	// @Modifying
	// @Query("select * from Order order where order.orderNo=?1")
	// Order findByOrderNo(String orderNo);

	Page<Task> findByStatus(String status, Pageable pageRequest);
}