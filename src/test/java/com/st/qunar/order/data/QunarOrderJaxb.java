package com.st.qunar.order.data;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springside.modules.mapper.JaxbMapper;

import com.google.common.collect.Lists;
import com.st.qunar.order.entity.Flight;
import com.st.qunar.order.entity.Insurance;
import com.st.qunar.order.entity.Log;
import com.st.qunar.order.entity.Msg;
import com.st.qunar.order.entity.Order;
import com.st.qunar.order.entity.Passenger;
import com.st.qunar.order.entity.PurchaseOrderInfo;
import com.st.qunar.order.entity.Refund;
import com.st.qunar.order.entity.Result;

public class QunarOrderJaxb {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		QunarOrderJaxb qo = new QunarOrderJaxb();
		// String xml = qo.objectToXml();
		qo.xmlToObject();
	}

	// @Test
	public void objectToXml() {
		Result result = new Result();
		result.setStatus("ok");

		Order order = new Order();
		order.setOrderId(100L);
		order.setOrderNo("TTS130812191324904ct01243000");
		order.setAllPrice("1.00");

		Refund refund = new Refund();
		refund.setRefundPrice("1.00");
		refund.setRefundNo("TTS130812191324904ct01243000");
		refund.setRefundTime("2014-11-22");
		order.setRefund(refund);

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();
		purchaseOrderInfo.setPurchansePrice("0.00");
		purchaseOrderInfo.setIssueTicketType("手工");
		order.setPurchaseOrderInfo(purchaseOrderInfo);

		Flight flight = new Flight();
		flight.setCode("MU6837");
		flight.setArr("PEK");
		flight.setDep("CKG");
		flight.setCabin("Y");
		List<Flight> flights = Lists.newArrayList();
		flights.add(flight);
		order.setFlights(flights);

		Passenger passenger = new Passenger();
		passenger.setName("蒋大钧");
		passenger.setPrice("2200");
		passenger.setEticketNum("EK093493");
		List<Passenger> passengers = Lists.newArrayList();
		passengers.add(passenger);
		order.setPassengers(passengers);

		Insurance insurance = new Insurance();
		insurance.setInsuranceNo("1100001");
		List<Insurance> insurances = Lists.newArrayList();
		insurances.add(insurance);
		order.setInsurances(insurances);

		Log log = new Log();
		log.setOperator("用户");
		List<Log> logs = Lists.newArrayList();
		logs.add(log);
		order.setLogs(logs);

		List<Order> orders = Lists.newArrayList();
		orders.add(order);
		result.setOrders(orders);

		String xml = JaxbMapper.toXml(result);
		System.out.println("Jaxb Object to Xml result:\n" + xml);
		// return xml;

	}

	@Test
	public void xmlToObject() throws IOException {
		String xml = FileUtils.readFileToString(new File("qnOrder.xml"));
		System.out.println(xml);
		Result result = JaxbMapper.fromXml(xml, Result.class);

		assertThat(result.getStatus()).isEqualTo("ok");
		Order order = result.getOrders().get(0);
		assertThat(order.getOrderId()).isEqualTo("100");
		assertThat(order.getRefund().getRefundNo()).isEqualTo("TTS130812191324904ct01243000");
		assertThat(order.getRefund().getRefundType()).isEqualTo("线上退款");
		assertThat(order.getPurchaseOrderInfo().getPurchansePrice()).isEqualTo("0.00");
		assertThat(order.getFlights().get(0).getCode()).isEqualTo("MU6837");
		assertThat(order.getPassengers().get(0).getName()).isEqualTo("蒋大钧");
		assertThat(order.getInsurances().get(0).getInsuranceNo()).isEqualTo("1100001");
		assertThat(order.getLogs().get(0).getOperator()).isEqualTo("用户");

	}

	@Test
	public void xmlToObjectErr() throws IOException {
		String xml = FileUtils.readFileToString(new File("qnOrderErr.xml"));
		System.out.println(xml);
		Result result = JaxbMapper.fromXml(xml, Result.class);
		assertThat(result.getStatus()).isEqualTo("error");
		Msg msg = result.getMsg();
		assertThat(msg.getContent()).isEqualTo("导出出错了");
	}

	/**
	 * 使用Dom4j验证Jaxb所生成XML的正确性.
	 * 
	 * @throws IOException
	 *             //
	 */
	// @Test
	public void assertXmlByDom4j() throws IOException {
		String xml = FileUtils.readFileToString(new File("qnOrder.xml"));
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			fail(e.getMessage());
		}
		Element user = doc.getRootElement();
		System.out.println(user.attribute("status").getValue());
		assertThat(user.attribute("status").getValue()).isEqualTo("ok");
	}
}
