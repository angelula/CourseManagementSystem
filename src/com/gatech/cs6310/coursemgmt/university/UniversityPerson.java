package com.gatech.cs6310.coursemgmt.university;

public class UniversityPerson {
	
	protected String name = "";
	
	protected String homeAddress = "";
	
	protected String phoneNumber = "";
	
	protected int UUID = 0;
	
	public UniversityPerson(
			int UUID,
			String name,
			String homeAddress,
			String phoneNumber
	) {
		this.name=name;
		this.homeAddress=homeAddress;
		this.phoneNumber=phoneNumber;
		this.UUID=UUID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHomeAddress() {
		return this.homeAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getUUID () {
		return this.UUID;
	}
	
	public void setUUID(int UUID) {
		this.UUID = UUID;
	}
}
