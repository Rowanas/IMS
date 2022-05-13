package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//10/05 tore the arraylist out of my orders to simplify, hopefully my code will still work
//10/05 End of day addendum - still works, but not perfectly due to missing method
public class Order {

	private Long orderID;
	private Long customerID;
	List<Item> items = new ArrayList<>();

	public Order(Long orderID, Long customerID, List<Item> items) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.items = items;
	}

	public Order(Long orderID, Long customerID) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
	}

	public Order(Long customerID) {
		super();
		this.customerID = customerID;
	}
	
	public Order() {
		super();
	}

	@Override
	public String toString() {
		double totalPrice = 0;
				for (Item i : items)
					totalPrice += i.getItemPrice();
		return "Order [orderID=" + orderID + ", customerID=" + customerID + ", items=" + items + "]\n OrderTotal = £" + totalPrice;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerID, items, orderID);
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
		return Objects.equals(customerID, other.customerID) && Objects.equals(items, other.items)
				&& Objects.equals(orderID, other.orderID);
	}

}
