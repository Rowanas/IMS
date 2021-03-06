package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

// Creates a single item order by taking user input. Currently then separate methods
// for adding/removing further items, but would like to call those methods from this
// create if time later. Jira task added.

// After talking with Pawel, he suggested add/remove method would probably be the better
// choice, and on re-examination, might not be any harder.
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing item by taking in user input
	 */

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

//rewrote with toLowerCase and more elegant execution than multiple cases
//to my knowledge, I am the only person to use switch cases, and testing it is
// becoming difficult.
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter Add to add an item, or Remove to remove an item");
		String addRemove = utils.getString().toLowerCase();
		switch (addRemove) {
		case "add":
			LOGGER.info("Please enter an item ID");
			Long itemID = utils.getLong();
			orderDAO.updateAdd(orderID, itemID);
			LOGGER.info("Order Updated");
			break;
// For some reason, my Add code threw no errors, but my remove code did. Odd behaviour.
// Got it fixed eventually, of course.
		case "remove":
			LOGGER.info("Please enter an item ID");
			itemID = utils.getLong();
			orderDAO.updateRemove(orderID, itemID);
			LOGGER.info("Order Updated");
			break;

		default:
			LOGGER.info("Please enter Add or Remove, lower or uppercase");

		}
// I hunted and hunted for what would make this return a value for my tests,
// what am I missing?
		return orderDAO.readLatest();
	}
}
