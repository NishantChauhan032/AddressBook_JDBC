package com.capg.jdbc.addressbook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressBookServiceTest 
{
	static AddressBookServiceDB addressBookService;
	static List<Contacts> contactList;

	@BeforeClass
	public static void setUp()  {
		addressBookService = new AddressBookServiceDB();
		contactList = new ArrayList<>();	
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
	
}
