package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

		private final ItemDAO DAO = new ItemDAO();

		@Before
		public void setup() {
			DBUtils.connect();
			DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		}

		@Test
		public void testCreate() {
			final Item created = new Item(1, "screwdriver", "7.20");
			assertEquals(created, DAO.create(created));
		}

		@Test
		public void testReadAll() {
			List<Customer> expected = new ArrayList<>();
			expected.add(new Customer(1L, "jordan", "harrison"));
			assertEquals(expected, DAO.readAll());
		}

		@Test
		public void testReadLatest() {
			assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
		}

		@Test
		public void testRead() {
			final long ID = 1L;
			assertEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
		}

		@Test
		public void testUpdate() {
			final Customer updated = new Customer(1L, "chris", "perrins");
			assertEquals(updated, DAO.update(updated));

		}

		@Test
		public void testDelete() {
			assertEquals(1, DAO.delete(1));
		}
	}
