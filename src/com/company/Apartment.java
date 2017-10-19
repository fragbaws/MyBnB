package com.company;

/* Apartment subclass of the Property superclass */

public class Apartment extends Property {

	private int storeyNum; //number of storeys
	private int numBeds; // number of beds
	
	/*Apartment constructor creates Apartment object*/
	
	public Apartment(int RegisterNumber, String name, String address, int rent, int days, int TotalRentDays, int NumberOfStoreys, int NumberOfBeds) {
		super(RegisterNumber, name, address, rent, days, TotalRentDays);
		
		this.storeyNum = NumberOfStoreys;
		this.numBeds = NumberOfBeds;
	}
	
	/*Apartment accessors which return the number of beds, storeys and the income generated for that apartment*/
	public int getStoreyNumber()
	{
		return storeyNum;
	}
	
	public int getNumberBeds()
	{
		return numBeds;
	}
	
	public int getIncome()
	{
		return TotalRentDays*rentCost;
	}
	
}
