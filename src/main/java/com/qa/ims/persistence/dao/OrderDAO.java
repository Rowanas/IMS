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
		return new Order(orderID, customerID);
	}

	public Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		String itemName = resultSet.getString("item_name");
		Double itemPrice = resultSet.getDouble("item_price");
		return new Item(itemName, itemPrice);
	}

	public List<Item> fetchItems(Long orderID) throws SQLException {
		List<Long> itemsID = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE order_id = ?");) {
			statement.setLong(1,  orderID);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					itemsID.add(resultSet.getLong("item_id"));
				}
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			List<Item> orderItemList = new ArrayList<>();
			for (Long i : itemsID) {
				orderItemList.add(itemDAO.read(i));
			}
			return orderItemList;
		}
	}

//moved all read items down here, for readability and to keep like-methods grouped.
	public List<Order> readItem() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items");) {
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
	
//reads one specific order

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
		readItem();
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
			statement.setObject(1, order.getorderItemsID());
			statement.executeUpdate();
			return read(order.getorderItemsID());
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
	public Order updateRemove(Long orderID, Long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_items WHERE order_id = ? and item_id = ?");) {
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

}
