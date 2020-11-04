package com.capg.jdbc.addressbook;

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
		Assert.assertEquals(6, contactList.size());
	}
	
}
