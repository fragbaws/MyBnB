package com.company;


/*Property Super Class*/

public class Property implements RentalItem{

	/*Attributes which are used in each property*/
	private int regNum;
	private String ownerName;
	private String postalAddress;
	int rentCost;
	private int seasonRentDays;
	int TotalRentDays = 0;
	
	/*Property Constructor*/
	public Property(int RegisterNumber, String name, String address, int rent, int days, int TotalRentDays)
	{
		this.regNum = RegisterNumber;
		this.ownerName = name;
		this.postalAddress = address;
		this.rentCost = rent;
		this.seasonRentDays = days;
		this.TotalRentDays = TotalRentDays;
	}

	/*Property accessors used to return specified value*/
	public int getRegisterNumber() {
		return regNum;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public int getRentCost() {
		return rentCost;
	}
	
	public int getSeasonDays()
	{
		return seasonRentDays;
	}

	@Override
	public void RentalItem(int RentalDays) {

		TotalRentDays+=RentalDays;
	}
}