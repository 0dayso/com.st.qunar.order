package com.st.qunar.order.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class CommonCountServiceTest extends SpringTransactionalTestCase {

	@Autowired
	private CommonCountService commonCountService;

	@Test
	public void getTypeCount() {
		assertThat(commonCountService.getCommonCountByTypeName("qunarOrderIncrExportLastId").getCount()).isEqualTo(100);
	}

	@Rollback(false)
	@Test
	public void updateCountByTypeName() {
		commonCountService.updateCountByTypeName("qunarOrderIncrExportLastId", 100L);
	}
}
