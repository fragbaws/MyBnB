package com.company;

/*Vehicle Superclass*/
public class Vehicle implements RentalItem {

    /*Instance variables*/
    private int regNum;
    private String ownerName;
    protected int RentalCostPerDay;
    protected int TotalRentalDays = 0;

    /*Vehicle constructor*/
    public Vehicle(int regNum, String ownerName, int RentalCostPerDay, int TotalRentalDays)
    {
        this.regNum = regNum;
        this.ownerName = ownerName;
        this.RentalCostPerDay = RentalCostPerDay;
        this.TotalRentalDays = TotalRentalDays;
    }

    /*Vehicle accessors*/
    public int getRegNum() { return this.regNum;}
    public String getOwnerName(){ return this.ownerName;}
    public int getTotalRentalDays(){ return this.TotalRentalDays;}
    public int getRentalCostPerDay(){ return this.RentalCostPerDay;}



    /*RentalItem method implemented from the interface used to calculate total rented days for each object*/
    @Override
    public void RentalItem(int RentalDays) {
        TotalRentalDays+=RentalDays;
    }

}
