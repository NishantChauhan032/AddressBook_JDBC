package com.capg.jdbc.addressbook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookServiceDB {

	Contacts contacts = null;

	public List<Contacts> viewAddressBook() throws DBServiceException {
		List<Contacts> contactsList = new ArrayList<>();
		String query = "select * from address_book";
		try (Connection connection = AddressBookService.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
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
				String emailID = resultSet.getString(11);
				contacts = new Contacts(id, fisrtName, lastName, addressBookName, contactType, address, city, state,
						zip, phoneNumber, emailID);
				contactsList.add(contacts);
			}
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		System.out.println(contactsList);
		return contactsList;
	}

	public void updateContactDetails(String state, String zip, String firstName) throws DBServiceException {
		String query = "update address_book set state = ? , zip = ? where firstName = ?";
		try (Connection con = AddressBookService.getConnection()) {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, state);
			preparedStatement.setString(2, zip);
			preparedStatement.setString(3, firstName);
			int result = preparedStatement.executeUpdate();
			contacts = getContactDetails(firstName);
			if (result > 0 && contacts != null) {
				contacts.setStateName(state);
				contacts.setZipCode(zip);
			}
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
	}

	private Contacts getContactDetails(String firstName) throws DBServiceException {
		return viewAddressBook().stream().filter(e -> e.getFirstName().equals(firstName)).findFirst().orElse(null);
	}

	public boolean checkForDBSync(String firstName) throws DBServiceException {
		try {
			return viewContactsByName(firstName).get(0).equals(getContactDetails(firstName));
		} catch (IndexOutOfBoundsException e) {
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		return false;
	}

	public List<Contacts> viewContactsByName(String firstName) throws DBServiceException {
		List<Contacts> contactListByName = new ArrayList<>();
		String query = "select * from address_book where firstName = ?";
		try (Connection con = AddressBookService.getConnection()) {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
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
				String emailID = resultSet.getString(11);
				contacts = new Contacts(id, fisrtName, lastName, addressBookName, contactType, address, city, state,
						zip, phoneNumber, emailID);
				contactListByName.add(contacts);
			}
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		System.out.println(contactListByName);
		return contactListByName;
	}

	public List<Contacts> viewContactsInADateRange(LocalDate startDate, LocalDate endDate) throws DBServiceException {

		List<Contacts> contactListInADateRange = new ArrayList<>();
		String query = "select * from address_book where date_added between ? and  ?";
		try (Connection con = AddressBookService.getConnection()) {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setDate(1, Date.valueOf(startDate));
			preparedStatement.setDate(2, Date.valueOf(endDate));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
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
				String emailID = resultSet.getString(11);
				contacts = new Contacts(id, fisrtName, lastName, addressBookName, contactType, address, city, state,
						zip, phoneNumber, emailID);
				contactListInADateRange.add(contacts);
			}
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		return contactListInADateRange;
	}

	public Map<String,Integer> countContactsByStateOrCityName(String column) throws DBServiceException
	{
		Map<String,Integer> contactsCount = new HashMap<>();
		String query = String.format("select %s , count(%s) from address_book group by %s;" , column,column,column);
		try(Connection connection = AddressBookService.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				contactsCount.put(resultSet.getString(1), resultSet.getInt(2));
			}
		}catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		return contactsCount;
	}	

}
