package com.company;

/* House subclass of the Property superclass */


public class House extends Property {

	private int storeyNum; //number of storeys
	private int clearingFees; //amount for clearing fees
	
	/* House constructor creates House object */
	public House(int RegisterNumber, String name, String address, int rent, int days, int TotalRentDays, int NumberOfStoreys, int amount) {
		super(RegisterNumber, name, address, rent, days, TotalRentDays);
		
		this.storeyNum = NumberOfStoreys;
		this.clearingFees = amount;
	}
	
	
	/*House accessors which return the number of storeys, clearing fees and the income generated for that house*/

	public int getStoreyNumber()
	{
		return storeyNum;
	}
	
	public int getClearingFees()
	{
		return clearingFees;
	}
	
	public int getIncome()
	{
		return TotalRentDays*rentCost + clearingFees;
	}

	
}
