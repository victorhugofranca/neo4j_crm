package com.vf.graphcrm;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class DBConnection {

	private static DBConnection instance;

	private GraphDatabaseService graphDatabaseService;

	private DBConnection() {
		this.graphDatabaseService = new GraphDatabaseFactory()
				.newEmbeddedDatabase("target/graphDB");
		
		registerShutdownHook(graphDatabaseService);
	}

	public static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();

		return instance;
	}

	private void registerShutdownHook(final GraphDatabaseService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
	
	public GraphDatabaseService getGraphDatabaseService() {
		return graphDatabaseService;
	}
	
}
