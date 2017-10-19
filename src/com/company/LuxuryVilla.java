package com.company;

/*Luxury Villa subclass of the Property super class*/

public class LuxuryVilla extends Property {

	private int numRooms; // number of rooms
	private int serviceCost; // service cost per day
	private int luxuryTax; // luxury tax per day
	
	
	/*Luxury Villa constructor which creates a Luxury Villa object*/
	public LuxuryVilla(int RegisterNumber, String name, String address, int rent, int days, int TotalRentDays, int NumberOfRooms, int cost, int tax) {
		super(RegisterNumber, name, address, rent, days, TotalRentDays);
		
		this.numRooms = NumberOfRooms;
		this.serviceCost = cost;
		this.luxuryTax = tax;
	}
	
	/*LuxuryVilla accessors which return the value for number of rooms, service cost per day, luxury tax per day, and the income generated*/
	public int getNumberRooms()
	{
		return numRooms;
	}
	
	public int getRoomServiceCost()
	{
		return serviceCost;
	}
	
	public int getLuxuryTax()
	{
		return luxuryTax;
	}
	
	public int getIncome()
	{
		return (rentCost + serviceCost + luxuryTax)*TotalRentDays;
	}


}
