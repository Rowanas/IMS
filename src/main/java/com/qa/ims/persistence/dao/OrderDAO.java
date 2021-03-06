package com.qa.ims.persistence.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private ItemDAO itemDAO;

	public OrderDAO(CustomerDAO customerDAO, ItemDAO itemDAO) {
		super();
		this.customerDAO = customerDAO;
		this.itemDAO = itemDAO;
	}

	public OrderDAO() {
		super();
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("order_id");
		Long customerID = resultSet.getLong("customer_id");
		List<Item> items = fetchItems(orderID);
		return new Order(orderID, customerID, items);
	}

// created to make an element of my fetchItems work, but eventually phased out as unnecessary with new solution.
//	public Item itemFromResultSet(ResultSet resultSet) throws SQLException {
//		String itemName = resultSet.getString("item_name");
//		Double itemPrice = resultSet.getDouble("item_price");
//		return new Item(itemName, itemPrice);
//	}
// this implementation of fetchItems appears to be causing my testing to fail, but not sure how I fix it.
//	Actual method runs fine and does what it's supposed to do, but doesn't play well with the tests I'm running.
	//It's something about the return, buyt I can't for the life of me fix it.
	public List<Item> fetchItems(Long orderID) throws SQLException {
		List<Long> itemsID = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE order_id = ?");) {
			statement.setLong(1, orderID);
			ResultSet resultSet = statement.executeQuery();
			{
				while (resultSet.next()) {
					itemsID.add(resultSet.getLong("item_id"));
				}
			}
			List<Item> orderItemList = new ArrayList<>();
			for (Long i : itemsID) {
				orderItemList.add(itemDAO.read(i));
			}
			return orderItemList;
		}
	}

//moved all read items down here, for readability and to keep like-methods grouped.

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> order = new ArrayList<>();
			while (resultSet.next()) {
				order.add(modelFromResultSet(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	// reads last created order, mainly for readout after create
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders( customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomerID());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		readLatest();
		return null;
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE order_items SET item_id = ?");) {
			statement.setObject(1, order.getOrderID());
			statement.executeUpdate();
			return read(order.getOrderID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order updateAdd(Long orderID, Long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items (order_id, item_id) VALUES(?,?)");) {
			statement.setObject(1, orderID);
			statement.setObject(2, itemID);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return readLatest();
	}

	// 10/05 deletes all of an item_id from a given order. Not ideal, but I was so
	// stuck and I had dogs barking and all sorts. Will return, maybe.
	// Discovered limit1 which prevents deletion of all items
	public Order updateRemove(Long orderID, Long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_items WHERE order_id = ? and item_id = ? LIMIT 1");) {
			statement.setObject(1, orderID);
			statement.setObject(2, itemID);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return readLatest();
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
