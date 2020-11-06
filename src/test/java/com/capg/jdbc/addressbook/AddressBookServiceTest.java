package com.capg.jdbc.addressbook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Test
	public void givenContactData_WhenAddedToDB_ShouldSyncWithDB() throws DBServiceException {
		addressBookService.addNewContactToDB("Vimal","Kumar","Friend","Friend","002-Db Road","Dhanbad",
				"Jharkhand","676756","9878754567","vimal@gmail.com","2018-03-04");
		boolean checkIfSynced = addressBookService.checkForDBSync("Vimal");
		Assert.assertTrue(checkIfSynced);
	}
	
	@Test
	public void givenMultipleContacts_WhenAdded_ShouldSyncWithTheDB() throws DBServiceException {
		Contacts[] arrayOfContacts= {
								new Contacts("Suraj","Asthana","Friend","Friend","Motihari",
										"Champaran","Bihar","878675","9507028511", "suraj@gmail.com","2018-03-22"),
								new Contacts("Rajeev","Kumar","Fanily","Family","Chainpur","Goraul",
										"Bihar","836367","9567538676","rajeev@gmail.com","2018-08-18"),
								new Contacts("Jeet","Yadav","Business","Business","Abc","Dhanbad",
										"Jharkhand", "998786","7648794749","jeet@gmail.com","2019-03-11"),
		};
		addressBookService.addMultipleContactsToAddressBookDBUsingThreads(Arrays.asList(arrayOfContacts));
		boolean checkIfSynced1 = addressBookService.checkForDBSync("Suraj");
		boolean checkIfSynced2 = addressBookService.checkForDBSync("Rajeev");
		boolean checkIfSynced3 = addressBookService.checkForDBSync("Jeet");
		Assert.assertTrue(checkIfSynced1);
		Assert.assertTrue(checkIfSynced2);
		Assert.assertTrue(checkIfSynced3);
	}
}
