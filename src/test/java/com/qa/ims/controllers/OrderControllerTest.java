package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;
        
// tests the create method in my order controller. Should only have a customer ID to begin with
	@Test
	public void testCreate() {
		final Long CustID = 1L;
		final Order created = new Order(CustID);
		Mockito.when(utils.getLong()).thenReturn(CustID);	
		Mockito.when(dao.create(created)).thenReturn(created);
		assertEquals(created, controller.create());
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> items = new ArrayList<>();
		items.add(new Order(1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

//split Long,string and double into separate sections.	

	
	public void testUpdateCore()	{
		Order updated = new Order();
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		assertEquals(updated, this.controller.update());
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testUpdateAdd() {
		Mockito.when(this.utils.getString()).thenReturn("Add");

	}
		
	@Test
	public void testUpdateRemove() {
		testUpdateCore();
		Mockito.when(this.utils.getString()).thenReturn("Remove");

	}
	
	@Test
	public void testUpdateOther() {
		testUpdateCore();
		Mockito.when(this.utils.getString()).thenReturn("Gobbledigook");

	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}

