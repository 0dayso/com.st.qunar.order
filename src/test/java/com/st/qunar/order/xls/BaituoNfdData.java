/**
 * 
 */
package com.st.qunar.order.xls;

/**
 * @author kxhu
 * 
 */
public class BaituoNfdData {
	private String arilines;
	private String dep;
	private String arr;
	private String cabin;
	private String price;
	private String discountCabin;
	private String aheadEarliest;
	private String aheadLatest;
	private String weekLimit;
	private String unlimitFlight;
	private String limitFlight;
	private String salseStartDate;
	private String salseEndDate;
	private String flightStartDate;
	private String flightEndDate;

	public String getArilines() {
		return arilines;
	}

	public void setArilines(String arilines) {
		this.arilines = arilines;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscountCabin() {
		return discountCabin;
	}

	public void setDiscountCabin(String discountCabin) {
		this.discountCabin = discountCabin;
	}

	public String getAheadEarliest() {
		return aheadEarliest.substring(0, aheadEarliest.indexOf("."));
	}

	public void setAheadEarliest(String aheadEarliest) {
		this.aheadEarliest = aheadEarliest;
	}

	public String getAheadLatest() {
		return aheadLatest.substring(0, aheadLatest.indexOf("."));
	}

	public void setAheadLatest(String aheadLatest) {
		this.aheadLatest = aheadLatest;
	}

	public String getWeekLimit() {
		return weekLimit;
	}

	public void setWeekLimit(String weekLimit) {
		this.weekLimit = weekLimit;
	}

	public String getUnlimitFlight() {
		return unlimitFlight;
	}

	public void setUnlimitFlight(String unlimitFlight) {
		this.unlimitFlight = unlimitFlight;
	}

	public String getLimitFlight() {
		return limitFlight;
	}

	public void setLimitFlight(String limitFlight) {
		this.limitFlight = limitFlight;
	}

	public String getSalseStartDate() {
		return salseStartDate;
	}

	public void setSalseStartDate(String salseStartDate) {
		this.salseStartDate = salseStartDate;
	}

	public String getSalseEndDate() {
		return salseEndDate;
	}

	public void setSalseEndDate(String salseEndDate) {
		this.salseEndDate = salseEndDate;
	}

	public String getFlightStartDate() {
		return flightStartDate;
	}

	public void setFlightStartDate(String flightStartDate) {
		this.flightStartDate = flightStartDate;
	}

	public String getFlightEndDate() {
		return flightEndDate;
	}

	public void setFlightEndDate(String flightEndDate) {
		this.flightEndDate = flightEndDate;
	}

}
