package com.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*Syntax                                    stores procedure
 {call procedure_name() }              Accept no parameters and return no value
 {call procedure_name(?,?) }           Accept two parameters and return no value
 {?=call procedure_name() }            Accept no parameters and return  value
 {?=call procedure_name(?) }               Accept one parameters and return value */
public class SpTesting {

	Connection con=null;
	Statement stmt;
	ResultSet rs;
	CallableStatement rstmt;
	ResultSet rs1;
	ResultSet rs2;
	
	@BeforeMethod
	void setup() throws SQLException {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","root");
	}
	
	
	@AfterMethod
	void tearDown() throws SQLException {
		con.close();
	}
	
	@Test(priority =1)
	void test_storedProcedureExists() throws SQLException {
		stmt=con.createStatement();
		rs=stmt.executeQuery("show procedure status where name='selectAllCustomers'");
		rs.next();
		
		Assert.assertEquals(rs.getString("Name"),"selectAllCustomers");
	}
	
	@Test(priority = 2)
	void test_selectAllCustomers() throws SQLException {
		rstmt=con.prepareCall("{call selectAllCustomers()}");
		rs1=rstmt.executeQuery();
		
		Statement stm=con.createStatement();
		rs2=stm.executeQuery("select * from customers");
		
		Assert.assertEquals(compareResultSets(rs1,rs2), true);
	}
	
	@Test(priority = 3)
	void test_selectAllCustomerByCity() throws SQLException {
		rstmt=con.prepareCall("{call selectAllCustomersByCity(?)}");
		rstmt.setString(1, "Singapore");
		rs1=rstmt.executeQuery();
		
		Statement stm=con.createStatement();
		rs2=stm.executeQuery("select * from customers where city='Singapore'");
		
		Assert.assertEquals(compareResultSets(rs1,rs2), true);
	}
	
	@Test(priority = 4)
	void test_selectAllCustomersByCityAndPin() throws SQLException {
		rstmt=con.prepareCall("{call selectAllCustomersByCityAndPin(?,?)}");
		rstmt.setString(1, "Singapore");
		rstmt.setString(2, "079903");

		rs1=rstmt.executeQuery();
		
		Statement stm=con.createStatement();
		rs2=stm.executeQuery("select * from customers where city='Singapore' and postalCode='079903'");
		
		Assert.assertEquals(compareResultSets(rs1,rs2), true);
	}
	
	@Test(priority=5)
	void test_get_order_by_cust() throws SQLException {
		con.prepareCall("{call get_order_by_cust(?,?,?,?,?)}");
		rstmt.setInt(1, 141);
		
		rstmt.registerOutParameter(2,Types.INTEGER);
		rstmt.registerOutParameter(3,Types.INTEGER);
		rstmt.registerOutParameter(4,Types.INTEGER);
		rstmt.registerOutParameter(5,Types.INTEGER);
		
		rstmt.executeQuery();
		
		int shipped=rstmt.getInt(2);
		int canceled=rstmt.getInt(3);
		int resolved=rstmt.getInt(4);
		int disputed=rstmt.getInt(5);

		System.out.println(shipped+" "+canceled+" "+resolved+" "+disputed);
		
		Statement stmt=con.createStatement();
		rs=stmt.executeQuery("select (select count(*) as shipped from orders where customerNumber= 141 and status= Shipped) as Shipped,(select count(*) as canceled from orders where customerNumber= 141 and status= Canceled) as Canceled,(select count(*) as Resolved from orders where customerNumber= 141 and status= Resolved) as Resolved,(select count(*) as Disputed from orders where customerNumber= 141 and status= Disputed) as Disputed");
		rs.next();
		
		int shipped1=rs.getInt("shipped");
		int canceled1=rs.getInt("canceled");
		int resolved1=rs.getInt("resolved");
		int disputed1=rs.getInt("disputed");
		
		if(shipped==shipped1 && canceled==canceled1 && resolved==resolved1 && disputed==disputed1) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);

		}

		
		
	}
	
	@Test(priority=5)
	void GetCustomerShipping() throws SQLException {
		con.prepareCall("{call classicmodels.GetCustomerShipping(?,?)}");
		rstmt.setInt(1, 112);
		
		rstmt.registerOutParameter(2,Types.VARCHAR);
		
		
		rstmt.executeQuery();
		
		String shippingTime=rstmt.getString(2);
		
	//	System.out.print(shipped);
		
		Statement stmt=con.createStatement();
		rs=stmt.executeQuery("select country,case when country='USA' Then '2-days Shipping' when country='Canada' Then '3-days Shipping'else '5-days Shipping' end as shippingTime from customers where customerNumber=112");
		rs.next();
		
		String exp_ShippingTime=rs.getString("ShippingTime");
		
		Assert.assertEquals(shippingTime, exp_ShippingTime);
	}
	
	public boolean compareResultSets(ResultSet resultSet1,ResultSet resultSet2) throws SQLException {
		while (resultSet1.next()) {
			resultSet2.next();
			int count=resultSet1.getMetaData().getColumnCount();
			for(int i=1;i<count;i++) {
				if(!StringUtils.equals(resultSet1.getString(i), resultSet2.getString(i))) {
					return false;
				}
			}
		}
		return true;
		
	}
}
