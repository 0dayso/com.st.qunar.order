/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.st.qunar.order.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.st.qunar.order.entity.CommonCount;

public interface CommonCountDao extends PagingAndSortingRepository<CommonCount, Long> {

	CommonCount findByTypeName(String typeName);

	// @Param("numbers")String numbers where t.numbers =:numbers
	@Modifying
	@Query("update CommonCount cc set cc.count=?2 where cc.typeName=?1")
	void updateCountByTypeName(String typeName, Long count);
}
