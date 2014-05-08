package com.st.qunar.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;

/**
 * @author kxhu
 * 
 */
@Entity
@Table(name = "qn_order")
public class Order extends IdEntity {
	// 订单ID
	private Long orderId;
	// 订单号
	private String orderNo;
	// 订单金额
	private String allPrice;
	// 未支付金额
	private String noPay;
	// 儿童票面价
	private String childPrintPrice;
	// 儿童销售价
	private String childFaceValue;
	// 订单来源，self、wap 等
	private String source;
	// 政策代码，代理商上传政策是自定义
	private String policySource;
	// 出票方式
	private String ticketMode;
	// 订单匹配政策号，若当前存在对应政策，会返回该项，否则不展示。
	private String policyFriendly;
	// 支付渠道。具体见附录支付渠道列表
	private String payChannel;
	// 支付状态（数字），具体见附录支付方式列表
	private String payStatus;
	// 保单单价，一般是20 或30
	private String insuranceUnitPrice;
	// 退改签说明，升级后单程政策展示标准退改签，该字段不展示，但不为空
	private String backnote;
	// 舱位说明（若当前存在对应政策，会返回舱位说明）
	private String cabinnote;
	// 成人保险让利价，销售价-保险让利
	private String insuranceCuttingPrice;
	// 成人票面价
	private String viewPrice;
	// 销售价
	private String price;
	// 成人PNR
	private String pnr;
	// 儿童PNR
	private String cpnr;
	// 基建费
	private String constructionFee;
	// 成人燃油费，订单有多个航段，为多航段之和
	private String fuelTax;
	// 儿童燃油费，订单有多个航段，为多航段之和
	private String childFuelTax;
	// 政策类型，如普通、申请、包机切位等
	private String policyType;
	// 订单状态，取值见附录订单状态列表
	private String status;
	// 订单联系人
	private String contact;
	// 订单联系人电话
	private String contactMob;
	// 订单联系人邮件，支付成功后的状态展示完整邮件地址
	private String contactEmail;
	// 订单创建时间
	private String createTime;
	// 订单中是否含有行程单或保单
	private String needPS;
	// 快递联系人电话
	private String contactTel;
	// 收件人地址
	private String address;
	// 快递公司
	private String company;
	// 快递费支付方式，普通/到付
	private String expType;
	// 快递号
	private String ordernumber;
	// 固定值，为“行程单”
	private String xcdPrice;
	// 值为0 或快递价格。（若快递为空，返回行程单价格，否则返回以下邮寄细节内容）
	private String xcd;
	// 收件人
	private String sjr;
	// 投递日期
	private String sendtime;

	// 几个乘机人会有几条passenger
	private List<Passenger> passengers = Lists.newArrayList();
	// 退款信息节点
	private Refund refund;
	// 采购单信息节点
	private PurchaseOrderInfo purchaseOrderInfo;
	// 几个航段会有几条flight
	private List<Flight> flights = Lists.newArrayList();

	private List<Log> logs = Lists.newArrayList();

	@XmlAttribute(name = "id")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@XmlAttribute
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@XmlAttribute
	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	@XmlAttribute
	public String getNoPay() {
		return noPay;
	}

	public void setNoPay(String noPay) {
		this.noPay = noPay;
	}

	@XmlAttribute
	public String getChildPrintPrice() {
		return childPrintPrice;
	}

	public void setChildPrintPrice(String childPrintPrice) {
		this.childPrintPrice = childPrintPrice;
	}

	@XmlAttribute
	public String getChildFaceValue() {
		return childFaceValue;
	}

	public void setChildFaceValue(String childFaceValue) {
		this.childFaceValue = childFaceValue;
	}

	@XmlAttribute
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@XmlAttribute
	public String getPolicySource() {
		return policySource;
	}

	public void setPolicySource(String policySource) {
		this.policySource = policySource;
	}

	@XmlAttribute
	public String getTicketMode() {
		return ticketMode;
	}

	public void setTicketMode(String ticketMode) {
		this.ticketMode = ticketMode;
	}

	@XmlAttribute
	public String getPolicyFriendly() {
		return policyFriendly;
	}

	public void setPolicyFriendly(String policyFriendly) {
		this.policyFriendly = policyFriendly;
	}

	@XmlAttribute
	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	@XmlAttribute
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@XmlAttribute
	public String getInsuranceUnitPrice() {
		return insuranceUnitPrice;
	}

	public void setInsuranceUnitPrice(String insuranceUnitPrice) {
		this.insuranceUnitPrice = insuranceUnitPrice;
	}

	@XmlAttribute
	public String getBacknote() {
		return backnote;
	}

	public void setBacknote(String backnote) {
		this.backnote = backnote;
	}

	@XmlAttribute
	public String getCabinnote() {
		return cabinnote;
	}

	public void setCabinnote(String cabinnote) {
		this.cabinnote = cabinnote;
	}

	@XmlAttribute
	public String getInsuranceCuttingPrice() {
		return insuranceCuttingPrice;
	}

	public void setInsuranceCuttingPrice(String insuranceCuttingPrice) {
		this.insuranceCuttingPrice = insuranceCuttingPrice;
	}

	@XmlAttribute
	public String getViewPrice() {
		return viewPrice;
	}

	public void setViewPrice(String viewPrice) {
		this.viewPrice = viewPrice;
	}

	@XmlAttribute
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@XmlAttribute
	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	@XmlAttribute
	public String getCpnr() {
		return cpnr;
	}

	public void setCpnr(String cpnr) {
		this.cpnr = cpnr;
	}

	@XmlAttribute
	public String getConstructionFee() {
		return constructionFee;
	}

	public void setConstructionFee(String constructionFee) {
		this.constructionFee = constructionFee;
	}

	@XmlAttribute
	public String getFuelTax() {
		return fuelTax;
	}

	public void setFuelTax(String fuelTax) {
		this.fuelTax = fuelTax;
	}

	@XmlAttribute
	public String getChildFuelTax() {
		return childFuelTax;
	}

	public void setChildFuelTax(String childFuelTax) {
		this.childFuelTax = childFuelTax;
	}

	@XmlAttribute
	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	@XmlAttribute
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlAttribute
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@XmlAttribute
	public String getContactMob() {
		return contactMob;
	}

	public void setContactMob(String contactMob) {
		this.contactMob = contactMob;
	}

	@XmlAttribute
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@XmlAttribute
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@XmlAttribute
	public String getNeedPS() {
		return needPS;
	}

	public void setNeedPS(String needPS) {
		this.needPS = needPS;
	}

	@XmlAttribute
	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	@XmlAttribute
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlAttribute
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@XmlAttribute
	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@XmlAttribute
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	@XmlAttribute
	public String getXcdPrice() {
		return xcdPrice;
	}

	public void setXcdPrice(String xcdPrice) {
		this.xcdPrice = xcdPrice;
	}

	@XmlAttribute
	public String getXcd() {
		return xcd;
	}

	public void setXcd(String xcd) {
		this.xcd = xcd;
	}

	@XmlAttribute
	public String getSjr() {
		return sjr;
	}

	public void setSjr(String sjr) {
		this.sjr = sjr;
	}

	@XmlAttribute
	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	@XmlElement
	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	@XmlElement
	public PurchaseOrderInfo getPurchaseOrderInfo() {
		return purchaseOrderInfo;
	}

	public void setPurchaseOrderInfo(PurchaseOrderInfo purchaseOrderInfo) {
		this.purchaseOrderInfo = purchaseOrderInfo;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	@XmlElement(name = "flight")
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		for (Flight flight : flights) {
			flight.setOrder(this);
		}
		this.flights = flights;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	@XmlElement(name = "passenger")
	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		for (Passenger passenger : passengers) {
			passenger.setOrder(this);
		}
		this.passengers = passengers;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	@XmlElement(name = "log")
	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		for (Log log : logs) {
			log.setOrder(this);
		}
		this.logs = logs;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
