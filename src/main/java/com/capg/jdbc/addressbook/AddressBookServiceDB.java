package com.capg.jdbc.addressbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookServiceDB {
	
	Contacts contacts = null;
	
	public List<Contacts> viewAddressBook() throws DBServiceException
	{
		List<Contacts> contactsList = new ArrayList<>();
		String query = "select * from address_book";
		try(Connection connection = AddressBookService.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				int id = resultSet.getInt(1);
				String fisrtName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String addressBookName = resultSet.getString(4);
				String contactType = resultSet.getString(5);
				String address = resultSet.getString(6);
				String city = resultSet.getString(7);
				String state = resultSet.getString(8);
				String zip = resultSet.getString(9);
				String phoneNumber = resultSet.getString(10);
				String email = resultSet.getString(11);
				contacts = new Contacts(id,fisrtName,lastName,addressBookName,contactType,address,city,state,zip,phoneNumber,email);
				contactsList.add(contacts);
			}
		}
		catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		System.out.println(contactsList);
		return contactsList;
	}


}
