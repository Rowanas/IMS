package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
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

	@Override
	public Order update() {
		Double itemID = utils.getDouble();
		Long order = utils.getLong();
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter Add to add an item, or Remove to remove an item");
		String addRemove = utils.getString();
		switch (addRemove) {
		case "add":
			LOGGER.info("Please enter an item ID");
			itemID = utils.getDouble();
			order = orderDAO.updateAdd(order);
			LOGGER.info("Order Updated");
			break;
// For some reason, my Add code threw no errors, but my remove code did. Odd behaviour.			
		case "remove":
			LOGGER.info("Please enter an item ID");
			itemID = utils.getDouble();
			order = orderDAO.updateRemove(itemID);
			LOGGER.info("Order Updated");
			break;

		default:
			LOGGER.info("Please enter an item ID");
			itemID = utils.getDouble();
			LOGGER.info("Order Updated");
			break;
		}
		return null;
	}
}
