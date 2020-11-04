package com.capg.jdbc.addressbook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressBookServiceTest 
{
	static AddressBookServiceDB addressBookService;
	static List<Contacts> contactList;
	static Map<String, Integer> countOfContaccts;

	@BeforeClass
	public static void setUp()  {
		addressBookService = new AddressBookServiceDB();
		contactList = new ArrayList<>();	
		countOfContaccts = new HashMap<>();
	}
	
	@Test
	public void givenAddressBookDB_WhenRetrieved_ShouldMatchCountOfContacts() throws DBServiceException{
		contactList = addressBookService.viewAddressBook();
		Assert.assertEquals(7, contactList.size());
	}
	
	@Test
	public void givenUpdatedContacts_WhenRetrieved_ShouldBeSyncedWithDB() throws DBServiceException{
		addressBookService.updateContactDetails("Pune" , "879123" , "Heera");
		boolean checkIfSynced = addressBookService.checkForDBSync("Heera");
		Assert.assertTrue(checkIfSynced);
	}
	
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchContactsCount() throws DBServiceException{
		contactList = addressBookService.viewContactsInADateRange(LocalDate.of(2018,01,01), LocalDate.now() );
		Assert.assertEquals(3, contactList.size());
	}
	
	
	@Test
	public void givenAddressDB_WhenRetrieved_ShouldReturnCountByState() throws DBServiceException {
		countOfContaccts = addressBookService.countContactsByStateOrCityName("state");
		Assert.assertEquals(2, countOfContaccts.get("Jharkhand") , 0);
		Assert.assertEquals(4, countOfContaccts.get("Bihar"), 0);
		Assert.assertEquals(1, countOfContaccts.get("Uttar Pradesh"), 0);
		Assert.assertEquals(1, countOfContaccts.get("Maharashtra"), 0);
	}
	
	@Test
	public void givenAddressDB_WhenRetrieved_ShouldReturnCountByCity() throws DBServiceException {
		countOfContaccts = addressBookService.countContactsByStateOrCityName("city");
		Assert.assertEquals(2, countOfContaccts.get("Vaishali") , 0);
		Assert.assertEquals(1, countOfContaccts.get("Goraul"), 0);
		Assert.assertEquals(1, countOfContaccts.get("Bhagwanpur"), 0);
		Assert.assertEquals(1, countOfContaccts.get("Pune"), 0);
		Assert.assertEquals(1, countOfContaccts.get("Patna"), 0);
	}
}
