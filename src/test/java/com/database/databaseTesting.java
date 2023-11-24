package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseTesting {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {


		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","root");
		
		Statement stm=con.createStatement();
		
		String ss="insert into college.items values(9,'icecream',90.0)";
			
		stm.executeUpdate(ss);
		
		con.close();
		System.out.println("one row created...........");
	}

}
