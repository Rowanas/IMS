package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

//	Looked this failure code up, not sure what's causing it. Seems to suggest
//	incorrect java version? Documentation suggested in stacktrace has nothing on this error
//  Pawel worked out the solution - there was an out of date dependency packaged with the test
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
}
