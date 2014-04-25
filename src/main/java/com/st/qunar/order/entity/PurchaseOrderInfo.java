package com.st.qunar.order.entity;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author kxhu
 * 
 */
@Embeddable
public class PurchaseOrderInfo {
	// 采购金额
	private String purchansePrice;
	// 出票方式。取手工出票、订单代付、自动出票、B2C 出票、认证出票。
	private String issueTicketType;
	// 出票平台
	private String issueTicketPlatformCode;
	// 支付平台流水号
	private String sourceTransactionId;
	// 采购源订单号
	private String sourceOrderNo;
	// 支付方式。中文显示
	private String tppType;
	// 采购状态。取支付成功、待支付、支付成功有退款
	private String purchanseStatus;
	// 支付商户订单号
	private String payCommercialNo;
	// 线下支付方式
	private String offlinePayType;
	// 线下支付卡号
	private String offlinePayCardno;
	// 采购会员id
	private String platformMemId;
	// 出票员
	private String operator;
	// 采购退款金额
	private String pruchanseRefundPrice;

	@XmlAttribute(name = "price")
	public String getPurchansePrice() {
		return purchansePrice;
	}

	public void setPurchansePrice(String purchansePrice) {
		this.purchansePrice = purchansePrice;
	}

	@XmlAttribute(name = "issue_ticket_type")
	public String getIssueTicketType() {
		return issueTicketType;
	}

	public void setIssueTicketType(String issueTicketType) {
		this.issueTicketType = issueTicketType;
	}

	@XmlAttribute(name = "issue_ticket_platform_code")
	public String getIssueTicketPlatformCode() {
		return issueTicketPlatformCode;
	}

	public void setIssueTicketPlatformCode(String issueTicketPlatformCode) {
		this.issueTicketPlatformCode = issueTicketPlatformCode;
	}

	@XmlAttribute(name = "source_transaction_id")
	public String getSourceTransactionId() {
		return sourceTransactionId;
	}

	public void setSourceTransactionId(String sourceTransactionId) {
		this.sourceTransactionId = sourceTransactionId;
	}

	@XmlAttribute(name = "source_order_no")
	public String getSourceOrderNo() {
		return sourceOrderNo;
	}

	public void setSourceOrderNo(String sourceOrderNo) {
		this.sourceOrderNo = sourceOrderNo;
	}

	@XmlAttribute(name = "tpp_type")
	public String getTppType() {
		return tppType;
	}

	public void setTppType(String tppType) {
		this.tppType = tppType;
	}

	@XmlAttribute(name = "status")
	public String getPurchanseStatus() {
		return purchanseStatus;
	}

	public void setPurchanseStatus(String purchanseStatus) {
		this.purchanseStatus = purchanseStatus;
	}

	@XmlAttribute(name = "pay_commercial_no")
	public String getPayCommercialNo() {
		return payCommercialNo;
	}

	public void setPayCommercialNo(String payCommercialNo) {
		this.payCommercialNo = payCommercialNo;
	}

	@XmlAttribute(name = "offline_pay_type")
	public String getOfflinePayType() {
		return offlinePayType;
	}

	public void setOfflinePayType(String offlinePayType) {
		this.offlinePayType = offlinePayType;
	}

	@XmlAttribute(name = "offline_pay_cardno")
	public String getOfflinePayCardno() {
		return offlinePayCardno;
	}

	public void setOfflinePayCardno(String offlinePayCardno) {
		this.offlinePayCardno = offlinePayCardno;
	}

	@XmlAttribute(name = "platform_mem_id")
	public String getPlatformMemId() {
		return platformMemId;
	}

	public void setPlatformMemId(String platformMemId) {
		this.platformMemId = platformMemId;
	}

	@XmlAttribute
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@XmlAttribute(name = "refund_price")
	public String getPruchanseRefundPrice() {
		return pruchanseRefundPrice;
	}

	public void setPruchanseRefundPrice(String pruchanseRefundPrice) {
		this.pruchanseRefundPrice = pruchanseRefundPrice;
	}

}
