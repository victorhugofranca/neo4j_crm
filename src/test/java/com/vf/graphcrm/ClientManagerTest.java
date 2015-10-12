package com.vf.graphcrm;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import com.vf.graphcrm.relationship.RelTypes;

public class ClientManagerTest {

	@Test
	public void createAndRemoveNodeShouldNotRaiseException() {
		DBConnection dbConnection = DBConnection.getInstance();

		Node firstNode;
		Node secondNode;
		Relationship relationship;

		try (Transaction tx = dbConnection.getGraphDatabaseService().beginTx()) {
			
			firstNode = dbConnection.getGraphDatabaseService().createNode();
			firstNode.setProperty("name", "John");
			assertThat(firstNode.getId(), is(greaterThan(-1L)));
			assertThat((String) firstNode.getProperty("name"), is("John"));
			assertThat((String) firstNode.getProperty("name"), not("Paul"));

			secondNode = dbConnection.getGraphDatabaseService().createNode();
			secondNode.setProperty("code", "001");
			assertThat(secondNode.getId(), is(greaterThan(-1L)));
			assertThat((String) secondNode.getProperty("code"), is("001"));
			assertThat((String) secondNode.getProperty("code"), not("002"));

			relationship = firstNode.createRelationshipTo(secondNode,
					RelTypes.BOUGHT);
			relationship.setProperty("date", "today");
			
			assertThat((String)relationship.getProperty("date"), is("today"));
			assertThat((String)relationship.getProperty("date"), not("tomorrow"));

			firstNode
					.getSingleRelationship(RelTypes.BOUGHT, Direction.OUTGOING)
					.delete();
			firstNode.delete();
			secondNode.delete();
			
			tx.success();
		}

	}
}
