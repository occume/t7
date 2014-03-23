package com.hy.wo.util;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return UserContextHolder.getUserType();
	}
	
}
