package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {

	private Long orderid;
	private String orderItemid;
	private Double totalPrice;
	private String customerid;
	
	public Order(Long orderid, String orderItemid, Double totalPrice, String customerid) {
		super();
		this.orderid = orderid;
		this.orderItemid = orderItemid;
		this.totalPrice = totalPrice;
		this.customerid = customerid;
	}

	public Order(String orderItemid, Double totalPrice, String customerid) {
		super();
		this.orderItemid = orderItemid;
		this.totalPrice = totalPrice;
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", orderItemid=" + orderItemid + ", totalPrice= £" + totalPrice
				+ ", customerid=" + customerid + "]";
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getOrderItemid() {
		return orderItemid;
	}

	public void setOrderItemid(String orderItemid) {
		this.orderItemid = orderItemid;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerid, orderItemid, orderid, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerid, other.customerid) && Objects.equals(orderItemid, other.orderItemid)
				&& Objects.equals(orderid, other.orderid) && Objects.equals(totalPrice, other.totalPrice);
	}
	
}
