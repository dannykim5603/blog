package com.sbs.java.blog.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	
	public static Map<String, Object> selectRow(Connection connection,String sql) {
		List<Map<String, Object>> rows = selectRows(connection,sql);
		
		if (rows.size() == 0) {
			return new HashMap<String, Object>();
		}
		
		return rows.get(0);
	}
	
	public static List<Map<String, Object>> selectRows(Connection connection,String sql) {
		List<Map<String, Object>> rows = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnSize = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();

				for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
					String columnName = metaData.getColumnName(columnIndex + 1);
					Object value = rs.getObject(columnName);

					if (value instanceof Long) {
						int numValue = (int) (long) value;
						row.put(columnName, numValue);
					} else if (value instanceof Timestamp) {
						String dateValue = value.toString();
						dateValue = dateValue.substring(0, dateValue.length() - 2);
						row.put(columnName, dateValue);
					} else {
						row.put(columnName, value);
					}
				}

				rows.add(row);
			}
		} catch (SQLException e) {
			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
			e.printStackTrace();
		}finally {
			if( stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
					e.printStackTrace();
				}
			}
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
					e.printStackTrace();
				}
			}
		}

		return rows;
	}

	public static int selectRowIntValue(Connection dbConn, String sql) {
		Map<String,Object> row = selectRow(dbConn,sql);
		for (String key : row.keySet()) {
			return (int) row.get(key);
		}
		return -1;
	}
	
	public static String selectRowStringValue(Connection dbConn, String sql) {
		Map<String,Object> row = selectRow(dbConn,sql);
		for (String key : row.keySet()) {
			return (String) row.get(key);
		}
		return "";
	}
	
	public static Boolean selectRowIntBooleanValue(Connection dbConn, String sql) {
		Map<String,Object> row = selectRow(dbConn,sql);
		for (String key : row.keySet()) {
			return (int) row.get(key) == 1;
		}
		return false;
	}

	public static int insert(Connection dbConn, String sql) {
		int id = -1;

		try {
			Statement stmt = dbConn.createStatement();
			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
		}
		return id;
	}
	
	public int delete(Connection dbConn, String sql) {
		int affectedRows = 0;

		Statement stmt;
		try {
			stmt = dbConn.createStatement();
			affectedRows = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
		}

		return affectedRows;
	}
}
