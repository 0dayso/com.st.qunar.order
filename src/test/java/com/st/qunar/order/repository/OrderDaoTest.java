/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.st.qunar.order.repository;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.mapper.JaxbMapper;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.google.common.collect.Lists;
import com.st.qunar.order.entity.Flight;
import com.st.qunar.order.entity.Insurance;
import com.st.qunar.order.entity.Log;
import com.st.qunar.order.entity.Order;
import com.st.qunar.order.entity.Passenger;
import com.st.qunar.order.entity.PurchaseOrderInfo;
import com.st.qunar.order.entity.Refund;
import com.st.qunar.order.entity.Result;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class OrderDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private FlightDao flightDao;

	// @Rollback(false)
	// @Test
	public void addFlight() {
		Flight flight = new Flight();
		flight.setCode("MU6837");
		flight.setCabin("Y");
		flight.setDep("CKG");
		flight.setArr("PVG");
		flight.setDepDay("2014-02-23");
		flight.setDepTime("17:20");
		flight.setArrTime("19:20");
		flight.setRealCode("");
		flight.setOrder(orderDao.findOne(1L));
		flightDao.save(flight);
	}

	@Test
	public void getFlight() {
		Flight flight = flightDao.findOne(8L);
		assertThat(flight.getArr()).isEqualTo("PVG");
		assertThat(flight.getOrder().getId()).isEqualTo(28);
	}

	@Rollback(false)
	@Test
	public void delFlight() {
		flightDao.delete(8L);
	}

	@Rollback(false)
	@Test
	public void delOrder() {
		orderDao.delete(29L);
	}

	@Rollback(false)
	@Test
	public void addOrder() {

		Order order = new Order();
		order.setOrderId(100L);
		order.setOrderNo("TTS130812191324904ct01243000");
		order.setAllPrice("2829");
		order.setNoPay("0.0");
		order.setChildPrintPrice("810");
		order.setChildFaceValue("810");
		order.setSource("self");
		order.setPolicySource("pl001");
		order.setTicketMode("手工出票");
		order.setPolicyFriendly("APP130000");
		order.setPayChannel("CFT");
		order.setPayStatus("null");
		order.setInsuranceUnitPrice("30");
		order.setBacknote("退改签");
		order.setCabinnote("-");
		order.setInsuranceCuttingPrice("788");
		order.setViewPrice("810");
		order.setPrice("783.0");
		order.setPnr("JMLBDD");
		order.setCpnr("");
		order.setConstructionFee("50");
		order.setFuelTax("120");
		order.setChildFuelTax("50");
		order.setPolicyType("普通产品");
		order.setStatus("PAY_OK");
		order.setContact("联系人");
		order.setAddress("***");
		order.setCompany("顺丰");
		order.setExpType("普通");
		order.setOrdernumber("001");
		order.setXcdPrice("20");
		order.setXcd("行程单");
		order.setSjr("联系人1");
		order.setSendtime("2014-04-22");

		Refund refund = new Refund();
		refund.setRefundPrice("1.00");
		refund.setRefundNo("TTS130812191324904ct01243000");
		refund.setRefundTime("2014-04-22 19:23:03");
		refund.setRefundType("线上退款");
		order.setRefund(refund);

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();
		purchaseOrderInfo.setPurchansePrice("0.00");
		purchaseOrderInfo.setIssueTicketType("手工");
		purchaseOrderInfo.setIssueTicketPlatformCode("");
		purchaseOrderInfo.setSourceTransactionId("");
		purchaseOrderInfo.setSourceOrderNo("");
		purchaseOrderInfo.setTppType("");
		purchaseOrderInfo.setPurchanseStatus("支付成功");
		purchaseOrderInfo.setPayCommercialNo("");
		purchaseOrderInfo.setOfflinePayCardno("");
		purchaseOrderInfo.setOfflinePayType("");
		purchaseOrderInfo.setPlatformMemId("");
		purchaseOrderInfo.setOperator("相菲");
		purchaseOrderInfo.setPruchanseRefundPrice("");
		order.setPurchaseOrderInfo(purchaseOrderInfo);

		Flight flight = new Flight();
		flight.setCode("MU6837");
		flight.setCabin("Y");
		flight.setDep("CKG");
		flight.setArr("PVG");
		flight.setDepDay("2014-02-23");
		flight.setDepTime("17:20");
		flight.setArrTime("19:20");
		flight.setRealCode("");
		flight.setOrder(order);
		order.getFlights().add(flight);

		Passenger passenger = new Passenger();
		passenger.setName("蒋大钧");
		passenger.setAgeType("0");
		passenger.setCardType("NI");
		passenger.setPrice("2200");
		passenger.setPriceType("成人");
		passenger.setCardNum("310109198308273550");
		passenger.setEticketNum("EK093493");
		passenger.setInsuranceCount("1");
		passenger.setBxSource("百年 9 / 19 人寿保险股份有限公司");
		passenger.setBxName("百年交通工具意外伤害保险");
		passenger.setOrder(order);
		order.getPassengers().add(passenger);

		Insurance insurance = new Insurance();
		insurance.setInsuranceNo("1100001");
		insurance.setBxFlight("第一程");
		insurance.setBxStatus("已出保险");
		insurance.setOrder(order);
		order.getInsurances().add(insurance);

		Log log = new Log();
		log.setOperator("用户");
		log.setTime("2014-04-23 20:47:04.0");
		log.setAction("将订单由【】修改为【订座成功等待支付】原因为生成订单");
		log.setOrder(order);
		List<Log> logs = Lists.newArrayList();
		logs.add(log);
		order.setLogs(logs);
		orderDao.save(order);
	}

	@Rollback(false)
	@Test
	public void xmlToObjectSave() throws IOException {
		String xml = FileUtils.readFileToString(new File("qn前50个订单.xml"));
		System.out.println(xml);
		Result result = JaxbMapper.fromXml(xml, Result.class);

		assertThat(result.getStatus()).isEqualTo("ok");
		List<Order> orders = result.getOrders();
		for (Order order : orders) {
			System.out.println(order.getOrderId() + ":" + order.getOrderNo());
		}
		// assertThat(orders.get(0).getOrderId()).isEqualTo("300");
		orderDao.save(orders);
	}

	@Test
	public void findUserByUserId() throws Exception {
		Order order = orderDao.findOne(28l);
		assertThat(order.getOrderNo()).isEqualTo("1");
		assertThat(order.getFlights().get(0).getArr()).isEqualTo("2");
		assertThat(order.getInsurances().get(0).getBxFlight()).isEqualTo("3");
		assertThat(order.getLogs().get(0).getAction()).isEqualTo("4");
		assertThat(order.getPassengers().get(0).getAgeType()).isEqualTo("5");
	}
}
