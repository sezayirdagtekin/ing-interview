package com.sezayir.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.sezayir.model.Customer;
import com.sezayir.repository.DerbDbRepsository;


public class CustomerDao {
	final static Logger log= Logger.getLogger(CustomerDao.class);
	public static int createCustomers() throws ClassNotFoundException, SQLException {
		int result=0;
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("create  table Customer (name varchar(50) , surname varchar(50), username varchar(20) )");
		log.info("Customer table isCreated");
		result=st.executeUpdate("INSERT INTO Customer " + "VALUES ('Sezayir',  'Dagtekin', 'user1')");
		log.info("user1 is  inserted");
		result=st.executeUpdate("INSERT INTO Customer " + "VALUES ('Adam',  'Smith', 'user2')");
		log.info("user2 is inserted");
		connection.close();
		return result;
	}	
	
	public static List<Customer> getCustomers() throws ClassNotFoundException, SQLException {
		Statement st = DerbDbRepsository.getConnection().createStatement();
		List<Customer> customerList = new ArrayList<>();
		ResultSet rec = st.executeQuery("select name,surname,username from Customer");
		while (rec.next()) {
			Customer customer = new Customer();
			customer.setName(rec.getString(1));
			customer.setSurname(rec.getString(2));
			customer.setUsername(rec.getString(3));
			customerList.add(customer);
		}
		st.close();

		return customerList;
	}
	public static int dropCustomers() throws SQLException, ClassNotFoundException {
		int result=0;
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		result=st.executeUpdate("DROP TABLE Customer");
		log.info("Customer Table deleted");
		return result;
	}
}
