package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long id;
	private String itemName;
	private Double itemPrice;

	public Item(Long id, String itemName, Double itemPrice) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public Item(String itemName, Double itemPrice) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", item name=" + itemName + ", item price= £" + itemPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemName, itemPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemPrice, other.itemPrice);
	}

	
}
