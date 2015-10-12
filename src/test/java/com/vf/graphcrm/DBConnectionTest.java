package com.vf.graphcrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DBConnectionTest {

	@Test
	public void getInstanceShouldNotRaiseException() {
		DBConnection.getInstance();
	}

	@Test
	public void getInstanceShouldNotReturnNull() {
		assertNotNull(DBConnection.getInstance());
	}

	@Test
	public void getInstanceShouldAlwaysReturnSameObject() {
		DBConnection dbConnectionOne = DBConnection.getInstance();

		DBConnection dbConnectionTwo = DBConnection.getInstance();

		assertEquals(dbConnectionOne, dbConnectionTwo);
	}

}
