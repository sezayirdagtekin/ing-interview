package com.sezayir.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sezayir.dao.CustomerDao;
import com.sezayir.model.Account;
import com.sezayir.model.Customer;
import com.sezayir.repository.DerbDbRepsository;

public class CustomerServiceImpl implements CustomerService {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		return DerbDbRepsository.getConnection();
	}

	@Override
	public int createCustomers() throws ClassNotFoundException, SQLException {
		return CustomerDao.createCustomers();
	}


	@Override
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException {
		Statement st = getConnection().createStatement();
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

	public List<Account> getAccounts() throws ClassNotFoundException, SQLException {
		Statement st = getConnection().createStatement();
		List<Account> accounts = new ArrayList<>();
		ResultSet rec = st.executeQuery("select accountid,username,balance from Account");
		while (rec.next()) {
			Account account = new Account();
			account.setAccountId(rec.getLong(1));
			account.setUserName(rec.getString(2));
			account.setBalance(rec.getBigDecimal(3));
			accounts.add(account);
		}
		st.close();
		return accounts;

	}

	@Override
	public int  dropCustomerTable() throws ClassNotFoundException, SQLException {
		return CustomerDao.dropCustomers();
	}
	

}
