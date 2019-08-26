package vora.priya.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_CRUD {
	
	public void JDBC_CRUD() { 
		
	}
	private static void printRecords(Connection con) throws SQLException {
		String sql
			= "Select * From mytable ORDER BY myname DESC"; // result sets have to be closed
		try(ResultSet results = con.createStatement().executeQuery(sql)) {
			while(results.next()) { 
				System.out.println(
						"First Name=" + results.getInt("FirstName") + "', " + 
						"Last Name='" + results.getString("LastName") + "', " + 
						"Primary Email Address ='" + results.getString("PrimaryEmail") + "', " + 
						"Secondary Email Address ='" + results.getString("SecondaryEmail") + "', " + 
						"Primary Phone ='" + results.getString("PrimaryPhone") + "', " + 
						"Secondary Phone ='" + results.getString("SecondaryPhone") + "', ");
			}
			
		}
			
	}

	private static void updateRecord(Connection con, int id, String newName, String newEmail) throws SQLException {
		String sql = 
					"UPDATE mytable SET myname=?, myemail=? " + 
				"WHERE myid=?";
		PreparedStatement s = con.prepareStatement(sql);
		s.setString(1,  newName);
		s.setString(2, newEmail);
		s.setInt(3, id);
		s.execute();
	}

	private static void insertRecord(Connection con, String TableName, String firstName, String lastName, String primaryEmailAddress, String secondaryEmailAddress, String primaryPhone, String secondaryPhone) throws SQLException {
		String sql = "INSERT INTO"+  TableName + "(FirstName, LastName, PrimaryEmail, SecondaryEmail, PrimaryPhone, SecondaryPhone)" + "VALUES (?, ?)";
		
		PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, firstName);
			s.setString(2, lastName);
			s.setString(3, primaryEmailAddress);
			s.setString(4, secondaryEmailAddress);
			s.setString(5, primaryPhone);
			s.setString(6, secondaryPhone);
			
			s.execute();
		
		
	}

	public static void createTable(Connection con, String IdOfUser) throws SQLException {
		
		//CREATE DATABASE IF NOT EXISTS DBName;
		String sql = "CREATE TABLE IF NOT EXISTS " + IdOfUser+ "myTable(FirstName varchar(255), LastName varchar(255), PrimaryEmail varchar(255), SecondaryEmail varchar(255), PrimaryPhone varchar(255), SecondaryPhone varchar(255))";
		
		con.createStatement().execute(sql);
	}

}
