package com.st.qunar.order.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.st.qunar.order.entity.StatusChangeLog;

public interface StatusChangeLogDao extends PagingAndSortingRepository<StatusChangeLog, Long>,
		JpaSpecificationExecutor<StatusChangeLog> {

}