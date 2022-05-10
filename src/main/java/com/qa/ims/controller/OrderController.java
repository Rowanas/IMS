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

	public Order updateAdd() {
		LOGGER.info("Please enter the id of the order to which you would like to add an item");
		Long id = utils.getLong();
		LOGGER.info("Please enter an order ID");
		Long itemName = utils.getLong();
		LOGGER.info("Please enter an item ID");
		Long itemID = utils.getLong();
		Order order = orderDAO.updateAdd(null);
		LOGGER.info("Order Updated");
		return order;
	}

	public Order updateRemove() {
		LOGGER.info("Please enter the id of the order from which you would like to remove an item");
		Long id = utils.getLong();
		LOGGER.info("Please enter an order ID");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter an item");
		Double itemID = utils.getDouble();
		Order order = orderDAO.updateRemove(null);
		LOGGER.info("Order Updated");
		return order;
	}

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
		// TODO Auto-generated method stub
		return null;
	}
}
