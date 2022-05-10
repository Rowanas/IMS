package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO orderDAO;
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
	public List<Item> readAll() {
		List<Item> orders = orderDAO.readAll();
		for (Item order : orders) {
			LOGGER.info(order);
		}
		return order;
	}

// Creates a single item order by taking user input. Currently then separate methods
// for addin/removing further items, but would like to call those methods from this
// create if time later. Jira task added.
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Double customerID = utils.getDouble();
		LOGGER.info("Please enter an item ID");
		Double itemID = utils.getDouble();
		Order order = orderDAO.create(new Order(customerID, itemID));
		LOGGER.info("Item created");
		return order;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Order updateAdd() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter an order ID");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item");
		Double itemPrice = utils.getDouble();
		Item item = itemDAO.update(new Item(id, itemName, itemPrice));
		LOGGER.info("Item Updated");
		return item;
	}

	public Item updateRemove() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter an order ID");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item");
		Double itemPrice = utils.getDouble();
		Item item = itemDAO.update(new Item(id, itemName, itemPrice));
		LOGGER.info("Item Updated");
		return item;
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
}
