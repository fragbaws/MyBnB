package com.company;

/*Truck Subclass inherits Vehicle SuperClass*/
public class Truck extends Vehicle {

    /*Instance variable*/
    private int CargoWeight;

    /*Truck Constructor*/
    public Truck(int regNum, String ownerName, int RentalCostPerDay, int weight, int TotalRentalDays)
    {
        super(regNum, ownerName, RentalCostPerDay, TotalRentalDays);
        this.CargoWeight = weight;
    }

    /*Truck accessors*/
    public int getCargoWeight(){return this.CargoWeight;}

    /*Method which calculates income generated from Truck object (provides 1 free day)*/
    public int getIncome() {return ((RentalCostPerDay*(TotalRentalDays-1) + CargoWeight));}

}
