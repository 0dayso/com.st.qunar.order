/**
 * 
 */
package com.st.qunar.order.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kxhu
 * 
 */
public class OrderStatus {
	public static final String ORDER_STATUS_NONE = "NONE";
	public static final int ORDER_STATUS_VALUE_NONE = 100;
	/*
	 * 未出票申请退款
	 */
	public static final String ORDER_STATUS_APPLY_4_RETURN_PAY = "APPLY_4_RETURN_PAY";
	public static final int ORDER_STATUS_VALUE_APPLY_4_RETURN_PAY = 50;
	/*
	 * 订座成功等待价格确认
	 */
	public static final String ORDER_STATUS_ORDER_SUCCESS_WAIT_4_PRICE_CONFIRME = "ORDER_SUCCESS_WAIT_4_PRICE_CONFIRME";
	public static final int ORDER_STATUS_VALUE_ORDER_SUCCESS_WAIT_4_PRICE_CONFIRME = 51;
	/*
	 * 等待座位确认
	 */
	public static final String ORDER_STATUS_WAIT_CONFIRM = "WAIT_CONFIRM";
	public static final int ORDER_STATUS_VALUE_WAIT_CONFIRM = 20;
	/*
	 * 订座成功等待支付
	 */
	public static final String ORDER_STATUS_BOOK_OK = "BOOK_OK";
	public static final int ORDER_STATUS_VALUE_BOOK_OK = 0;
	/*
	 * 订单取消
	 */
	public static final String ORDER_STATUS_CANCEL_OK = "CANCEL_OK";
	public static final int ORDER_STATUS_VALUE_CANCEL_OK = 12;
	/*
	 * 支付成功等待出票
	 */
	public static final String ORDER_STATUS_PAY_OK = "PAY_OK";
	public static final int ORDER_STATUS_VALUE_PAY_OK = 1;
	/*
	 * 出票中
	 */
	public static final String ORDER_STATUS_TICKET_LOCK = "TICKET_LOCK";
	public static final int ORDER_STATUS_VALUE_TICKET_LOCK = 5;
	/*
	 * 出票完成
	 */
	public static final String ORDER_STATUS_TICKET_OK = "ICKET_OK";
	public static final int ORDER_STATUS_VALUE_TICKET_OK = 2;
	/*
	 * 改签申请中
	 */
	public static final String ORDER_STATUS_APPLY_CHANGE = "APPLY_CHANGE";
	public static final int ORDER_STATUS_VALUE_APPLY_CHANGE = 40;
	/*
	 * 改签完成
	 */
	public static final String ORDER_STATUS_CHANGE_OKE = "CHANGE_OKE";
	public static final int ORDER_STATUS_VALUE_CHANGE_OKE = 42;
	/*
	 * 退票申请中
	 */
	public static final String ORDER_STATUS_APPLY_REFUNDMENT = "APPLY_REFUNDMENT";
	public static final int ORDER_STATUS_VALUE_APPLY_REFUNDMENT = 30;
	/*
	 * 退票完成等待退款
	 */
	public static final String ORDER_STATUS_CHANGE_WAIT_REFUNDMENT = "WAIT_REFUNDMENT";
	public static final int ORDER_STATUS_VALUE_CHANGE_WAIT_REFUNDMENT = 31;
	/*
	 * 退款完成
	 */
	public static final String ORDER_STATUS_APPLY_REFUND_OK = "APPLY_REFUND_OK";
	public static final int ORDER_STATUS_VALUE_APPLY_REFUND_OK = 39;

	@SuppressWarnings({ "serial" })
	public static final Map<String, StatusB2A> statusChange = new HashMap() {
		{
			// 订座成功等待支付->支付成功等待出票;用户支付：订单状态从“订座成功等待支付”变成“支付成功等待出票”
			put("0101", new StatusB2A(ORDER_STATUS_BOOK_OK, ORDER_STATUS_PAY_OK));
			// 未出票申请退款->支付成功等待出票;未出票申请退款请求被驳回：订单状态从“未出票申请退款”变为“支付成功等待出票”
			put("0102", new StatusB2A(ORDER_STATUS_APPLY_4_RETURN_PAY, ORDER_STATUS_PAY_OK));
			// 订单取消->支付成功等待出票;用户支付：订单状态从“订单取消”变为“支付成功等待出票”
			put("0103", new StatusB2A(ORDER_STATUS_CANCEL_OK, ORDER_STATUS_PAY_OK));
			// 等待座位确认->支付成功等待出票;用户支付：订单状态从“等待座位确认”变为“支付成功等待出票”
			put("0104", new StatusB2A(ORDER_STATUS_WAIT_CONFIRM, ORDER_STATUS_PAY_OK));
			// 支付成功等待出票->未出票申请退款;未出票申请退款：订单状态从“支付成功等待出票”变为“未出票申请退款”
			put("0201", new StatusB2A(ORDER_STATUS_PAY_OK, ORDER_STATUS_APPLY_4_RETURN_PAY));
			// 出票中->未出票申请退款;未出票申请退款：订单状态从“出票中”变为“未出票申请退款”
			put("0202", new StatusB2A(ORDER_STATUS_TICKET_LOCK, ORDER_STATUS_APPLY_4_RETURN_PAY));
			// 出票完成->退票申请中;申请退票：订单状态从“出票完成”变为“退票申请中”
			put("0301", new StatusB2A(ORDER_STATUS_TICKET_OK, ORDER_STATUS_APPLY_REFUNDMENT));
			// 出票完成->改期申请中;申请改期：订单状态从“出票完成”变为“改期申请中”
			put("0401", new StatusB2A(ORDER_STATUS_TICKET_OK, ORDER_STATUS_APPLY_CHANGE));
			// 在订单中索取行程单或保险发票（包含快递、自取），并支付，即用户支付快递费
			put("0501", new StatusB2A("", ""));
			// 用户未在订单中索取行程单、保险发票，在成单回调页索取并支付，即行程单、保险发票费用不在订单内
			put("0502", new StatusB2A("", ""));
			// n->等待座位确认;生成申请订单：订单状态“等待座位确认”
			put("0601", new StatusB2A("", ORDER_STATUS_WAIT_CONFIRM));
			// 订座成功等待支付->等待座位确认;PNR校验失败，请检查PNR状态：订单状态“等待座位确认”
			put("0602", new StatusB2A("ORDER_STATUS_BOOK_OK", ORDER_STATUS_WAIT_CONFIRM));
			// 订座成功等待支付->订座成功等待价格确认;Pata异常，需人工确认价格：订单状态“订座成功等待价格确认”
			put("0701", new StatusB2A("ORDER_STATUS_BOOK_OK", ORDER_STATUS_ORDER_SUCCESS_WAIT_4_PRICE_CONFIRME));
			// 支付成功等待出票->支付成功等待出票;%B2B平台%政策查询失败，退出自动出票
			put("0801", new StatusB2A("ORDER_STATUS_PAY_OK", ORDER_STATUS_PAY_OK));
			// 支付成功等待出票->支付成功等待出票;%B2B平台%生单失败，退出自动出票
			put("0802", new StatusB2A("ORDER_STATUS_PAY_OK", ORDER_STATUS_PAY_OK));
			// 支付成功等待出票->支付成功等待出票;%B2B平台%支付失败，退出自动出票
			put("0803", new StatusB2A("ORDER_STATUS_PAY_OK", ORDER_STATUS_PAY_OK));
			// 支付成功等待出票->出票中;%B2B平台%支付异常，无法判断是否支付成功，退出自动出票
			put("0804", new StatusB2A("ORDER_STATUS_PAY_OK", ORDER_STATUS_TICKET_LOCK));
			// 超时未出票
			put("0901", new StatusB2A("", ""));
		}
	};

	public static class StatusB2A {
		private String beforeStatus;

		private String afterStatus;

		public String getBeforeStatus() {
			return beforeStatus;
		}

		public void setBeforeStatus(String beforeStatus) {
			this.beforeStatus = beforeStatus;
		}

		public String getAfterStatus() {
			return afterStatus;
		}

		public void setAfterStatus(String afterStatus) {
			this.afterStatus = afterStatus;
		}

		public StatusB2A(String beforeStatus, String afterStatus) {
			this.beforeStatus = beforeStatus;
			this.afterStatus = afterStatus;
		}
	}
}
