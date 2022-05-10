package com.qa.ims.persistence.domain;

import java.util.Objects;

//tore the arraylist out of my orders to simplify, hopefully my code will still work
public class Order {

	private Long orderID;
	private Long orderItemsID;
	private Double totalPrice;
	private Long customerID;

	public Order(Long orderID, Long orderItemsID, Double totalPrice, Long customerID) {
		super();
		this.orderID = orderID;
		this.orderItemsID = orderItemsID;
		this.totalPrice = totalPrice;
		this.customerID = customerID;
	}

	public Order(Long orderItemsID, Double totalPrice, Long customerID) {
		super();
		this.orderItemsID = orderItemsID;
		this.totalPrice = totalPrice;
		this.customerID = customerID;

	}

	public Order(Long orderID, Long customerID, Long orderItemsID) {
		super();
		this.orderID = orderID;
		this.orderItemsID = orderItemsID;
		this.customerID = customerID;
	}

	public Order(Long customerID) {
		super();
		this.customerID = customerID;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getorderItemsID() {
		return orderItemsID;
	}

	public void setorderItemsID(Long orderItemsID) {
		this.orderItemsID = orderItemsID;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public Order() {
		super();

	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderItemsID=" + orderItemsID + ", totalPrice=" + totalPrice
				+ ", customerID=" + customerID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerID, orderID, orderItemsID, totalPrice);
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
		return Objects.equals(customerID, other.customerID) && Objects.equals(orderID, other.orderID)
				&& Objects.equals(orderItemsID, other.orderItemsID) && Objects.equals(totalPrice, other.totalPrice);
	}
}
