package com.company;

/*Car SubClass inherits Vehicle SuperClass*/
public class Car extends Vehicle {

    /*Instance variable*/
    private int PassengersNumber;

    /*Car Constructor*/
    public Car(int regNum, String ownerName, int RentalCostPerDay, int number,int TotalRentalDays)
    {
        super(regNum, ownerName, RentalCostPerDay,TotalRentalDays);
        this.PassengersNumber = number;
    }

    /*Car Accessor*/
    public int getPassengersNumber(){return this.PassengersNumber;}

    /*Method which calculates income generated from Car object (provides 1 free day)*/
    public int getIncome() {return RentalCostPerDay*(TotalRentalDays-1);}

}
