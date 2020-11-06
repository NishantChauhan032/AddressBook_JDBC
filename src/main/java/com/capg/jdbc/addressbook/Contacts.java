package com.capg.jdbc.addressbook;

public class Contacts {
	private int id;
	private String firstName;
	private String lastName,emailId,cityName;
	private String address,zipCode,stateName;
	private String phoneNumber;
	private String contactType,addressBookName;
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getAddressBookName() {
		return addressBookName;
	}
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	public Contacts(int id,String firstName,String lastName,String address_name,String addressType,
					String address,String cityName,String stateName,String zipCode,String phoneNumber, String emailId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.cityName = cityName;
		this.address = address;
		this.zipCode = zipCode;
		this.stateName = stateName;
		this.phoneNumber = phoneNumber;
		this.contactType = addressType;
		this.addressBookName = address_name;
	}
	public Contacts(String firstName, String lastName, String addressBookName, String contactType, String address,
			String city, String state, String zip, String phoneNo, String email, String date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = email;
		this.cityName = city;
		this.address = address;
		this.zipCode = zip;
		this.stateName = state;
		this.phoneNumber = phoneNo;
		this.contactType = contactType;
		this.addressBookName = addressBookName;
	}
	
	
	@Override
	public String toString() {
		return "Contacts [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", cityName=" + cityName + ", address=" + address + ", zipCode=" + zipCode + ", stateName="
				+ stateName + ", phoneNumber=" + phoneNumber + ", addressType=" + contactType + ", address_name="
				+ addressBookName + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacts other = (Contacts) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contactType == null) {
			if (other.contactType != null)
				return false;
		} else if (!contactType.equals(other.contactType))
			return false;
		if (addressBookName == null) {
			if (other.addressBookName != null)
				return false;
		} else if (!addressBookName.equals(other.addressBookName))
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
}

