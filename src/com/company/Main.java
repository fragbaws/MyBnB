package com.company;

import java.io.*;
import java.util.*;
import javax.swing.*;

/*Main Class*/
public class Main {


	/*Creates 5 ArrayLists of objects - Apartment, LuxuryVilla, House, Car and Truck*/
	ArrayList<Apartment> Apartment = new ArrayList<Apartment>();
	ArrayList<LuxuryVilla> LuxuryVilla = new ArrayList<LuxuryVilla>();
	ArrayList<House> House = new ArrayList<House>();
	ArrayList<Car> Car = new ArrayList<Car>();
	ArrayList<Truck> Truck = new ArrayList<Truck>();


    /*Initialises the income for each apartment,villa and house to 0*/
	int apartmentIncome = 0;
	int villaIncome = 0;
	int houseIncome = 0;
	int carIncome = 0;
	int truckIncome = 0;

	/*String array which holds user input when it comes to making decisions in methods*/
	String[] decision = {"Y", ""}; // 1st index holds answer to whether user wants to rent again
                                   // 2nd index holds answer to whether user wants to rent property or vehicle
	
	/*Main method*/
	public static void main(String[] args) throws IOException {

		Main call = new Main();                 // creating instance of class to call non static methods in main.

        call.ReadInputPropertyFile();	        // method to fill in attributes in properties
       	call.ReadInputVehicleFile();            // method to fill in attributes in vehicles

       	call.userInterface();                   // method which calls user interface to rent

		call.CalculatePropertyTotalIncome();    // method to calculate total income generated for each property
        call.CalculateVehicleTotalIncome();     // method to calculate total income generated for each vehicle
		call.PrintAllProperties();              // method to print all information about property
        call.PrintAllVehicles();                // method to print all information about vehicles

		System.exit(0); /*exit*/
	}

	/*Method which contains the UI*/
	public void userInterface(){

	    /*User Welcome Message*/
        JOptionPane.showOptionDialog(null,
                "Welcome to 'MyBnB'!" + "\n" + "Happy renting!",
                "MyBnB",
                JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Continue"},
                "");

        /*Used for drop down menu*/
        String[] choices = {"Vehicles", "Property"};

        /*While user inputs "Y" into decision, the while loop continues to rent*/
        while((decision[0].equalsIgnoreCase("Y"))){

            /*Asks user what he would like to rent using a drop down menu*/
            decision[1] = (String) JOptionPane.showInputDialog(null, "What would you like to rent?",
                    "Rent Option", JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,
                    choices[0]);

            if (decision[1].equalsIgnoreCase("vehicles")) {
                GiveRentalVehicle(); // Rent Vehicles if user choose vehicles in drop down menu
            } else if(decision[1].equalsIgnoreCase("property")) {
                GiveRentalProperty(); // Rent Property if user chooses property in drop down menu
            }

            Decision(); // calls Decision method which asks if user would like to rent again
        }
    }

    /*Methods to fill in attributes in property and vehicle objects through file input*/
    public void ReadInputPropertyFile() {

        int count = 0; // used to count the number of lines in the text file
        String line;   // used to store a line from input text file
        boolean stop = false; // used to terminate loop when reaches end of input text file

        try{

            Scanner check = new Scanner(new File("InputProperty.txt")); // Scanner object reads InputProperty.txt

            /*While loop which counts the number of lines in the file*/
            while(check.hasNextLine() && !stop)
            {
                count++;
                String checkLine = check.nextLine();

                if(checkLine.equals("")) // If reaches the end, terminate loop.
                {
                    stop = true;
                }
            }


            Scanner input = new Scanner(new File("InputProperty.txt")); // Scanner object reads InputProperty.txt
            for(int i = 0; i<count; i++)
            {
                line = input.nextLine();
                String[] element = line.split(" "); // Splits the current string in line into the element array

                if(element[0].equals("1")) // If the element array holds information on apartment
                {
                    Apartment.add(new Apartment(Integer.parseInt(element[1]), element[2], element[3], Integer.parseInt(element[4]),
                            Integer.parseInt(element[5]),0, Integer.parseInt(element[6]), Integer.parseInt(element[7])));

                }
                else if(element[0].equals("2")) // If the element array holds information on house
                {
                    House.add(new House(Integer.parseInt(element[1]), element[2], element[3], Integer.parseInt(element[4]),
                            Integer.parseInt(element[5]),0, Integer.parseInt(element[6]), Integer.parseInt(element[7])));
                }
                else if(element[0].equals("3")) // If the element array holds information on luxury villa
                {
                    LuxuryVilla.add(new LuxuryVilla(Integer.parseInt(element[1]), element[2], element[3], Integer.parseInt(element[4]), Integer.parseInt(element[5]), 0,
                            Integer.parseInt(element[6]), Integer.parseInt(element[7]), Integer.parseInt(element[8])));
                }
                else if(element[0].equals("")) { // If the element array is empty
                    System.out.println("Error, empty line found in text file.");
                }
                else    // If no required property type exists.
                    System.out.println("Property type found in InputProperty.txt does not exist in the system...");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }


    }

	public void ReadInputVehicleFile(){

	    int count = 0;
	    String line;
	    boolean stop = false;

	    try{

	        Scanner check = new Scanner(new File("InputVehicle.txt"));
	        while(check.hasNextLine() && !stop)
            {
                count++;
                String checkLine = check.nextLine();

                if(checkLine.equals(""))
                    stop = true;
            }

            Scanner input = new Scanner(new File("InputVehicle.txt"));
            for(int i = 0 ; i< count ; i++)
            {
                line = input.nextLine();
                String[] element = line.split(" ");

                if(element[0].equals("4"))
                {
                    Car.add(new Car(Integer.parseInt(element[1]), element[2], Integer.parseInt(element[3]), Integer.parseInt(element[4]), 0));
                }
                else if(element[0].equals("5"))
                {
                    Truck.add(new Truck(Integer.parseInt(element[1]), element[2], Integer.parseInt(element[3]), Integer.parseInt(element[4]), 0));
                }
                else
                    System.out.println("Vehicle type found in InputVehicle.txt does not exist in the system...");
            }


        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found ...");
        }


	}

    /*Methods to print out all of user input and generated income for all properties and vehicles*/
    public void PrintAllProperties() throws IOException {

        /*Creates a file called OutputProperty.txt and prepares to write to it*/
        File output = new File("OutputProperty.txt");
        FileOutputStream fos = new FileOutputStream(output);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));


		/*Prints all attributes of Apartment ArrayList and writes to OutputProperty.txt*/
        for(int i = 0 ; i < Apartment.size() ; i++)
        {
            System.out.println("Apartment " + (i+1));
            System.out.println("------------");
            System.out.println("REG#: " + Apartment.get(i).getRegisterNumber());
            System.out.println("OWNER NAME: " +Apartment.get(i).getOwnerName());
            System.out.println("POSTAL ADDRESS: " +Apartment.get(i).getPostalAddress());
            System.out.println("SEASON RENT DAYS: "+Apartment.get(i).getSeasonDays());
            System.out.println("TOTAL DAYS RENTED: "+Apartment.get(i).TotalRentDays);
            System.out.println("RENT COST: " +Apartment.get(i).getRentCost() + "\n");

            bw.write("Apartment " + (i+1)+"\r\n");
            bw.write("------------"+"\r\n");
            bw.write("REG#: " + Apartment.get(i).getRegisterNumber()+"\r\n");
            bw.write("OWNER NAME: " +Apartment.get(i).getOwnerName()+"\r\n");
            bw.write("POSTAL ADDRESS: " +Apartment.get(i).getPostalAddress()+"\r\n");
            bw.write("SEASON RENT DAYS: "+Apartment.get(i).getSeasonDays()+"\r\n");
            bw.write("TOTAL DAYS RENTED: "+Apartment.get(i).TotalRentDays+ "\r\n");
            bw.write("RENT COST: " +Apartment.get(i).getRentCost() + "\r\n\r\n");

        }



		/*Prints all attributes of House ArrayList and writes to OutputProperty.txt*/
        for(int i = 0 ; i < House.size() ; i++)
        {
            System.out.println("House " + (i+1));
            System.out.println("------------");
            System.out.println("REG#: " + House.get(i).getRegisterNumber());
            System.out.println("OWNER NAME: " +House.get(i).getOwnerName());
            System.out.println("POSTAL ADDRESS: " +House.get(i).getPostalAddress());
            System.out.println("SEASON RENT DAYS: "+House.get(i).getSeasonDays());
            System.out.println("TOTAL DAYS RENTED: "+House.get(i).TotalRentDays);
            System.out.println("RENT COST: " +House.get(i).getRentCost() + "\n");

            bw.write("House " + (i+1)+ "\r\n");
            bw.write("------------"+ "\r\n");
            bw.write("REG#: " + House.get(i).getRegisterNumber()+ "\r\n");
            bw.write("OWNER NAME: " +House.get(i).getOwnerName()+ "\r\n");
            bw.write("POSTAL ADDRESS: " +House.get(i).getPostalAddress()+ "\r\n");
            bw.write("SEASON RENT DAYS: "+House.get(i).getSeasonDays()+ "\r\n");
            bw.write("TOTAL DAYS RENTED: "+House.get(i).TotalRentDays +"\r\n");
            bw.write("RENT COST: " +House.get(i).getRentCost() + "\r\n\r\n");
        }

		/*Prints all attributes of Luxury Villa ArrayList and writes to OutputProperty.txt*/
        for(int i = 0 ; i < LuxuryVilla.size() ; i++)
        {
            System.out.println("Luxury Villa " + (i+1));
            System.out.println("------------");
            System.out.println("REG#: " + LuxuryVilla.get(i).getRegisterNumber());
            System.out.println("OWNER NAME: " +LuxuryVilla.get(i).getOwnerName());
            System.out.println("POSTAL ADDRESS: " +LuxuryVilla.get(i).getPostalAddress());
            System.out.println("SEASON RENT DAYS: "+LuxuryVilla.get(i).getSeasonDays());
            System.out.println("TOTAL DAYS RENTED: "+LuxuryVilla.get(i).TotalRentDays);
            System.out.println("RENT COST: " +LuxuryVilla.get(i).getRentCost() + "\n");

            bw.write("Luxury Villa " + (i+1)+ "\r\n");
            bw.write("------------"+ "\r\n");
            bw.write("REG#: " + LuxuryVilla.get(i).getRegisterNumber()+ "\r\n");
            bw.write("OWNER NAME: " +LuxuryVilla.get(i).getOwnerName()+ "\r\n");
            bw.write("POSTAL ADDRESS: " +LuxuryVilla.get(i).getPostalAddress()+ "\r\n");
            bw.write("SEASON RENT DAYS: "+LuxuryVilla.get(i).getSeasonDays()+ "\r\n");
            bw.write("TOTAL DAYS RENTED: "+LuxuryVilla.get(i).TotalRentDays + "\r\n");
            bw.write("RENT COST: " +LuxuryVilla.get(i).getRentCost() + "\r\n\r\n");
        }


        /*Prints all income generated from Property and writes to OutputProperty.txt*/
        System.out.println("TOTAL INCOME GENERATED FROM APARTMENTS: " + apartmentIncome);
        System.out.println("TOTAL INCOME GENERATED FROM HOUSES: " + houseIncome);
		System.out.println("TOTAL INCOME GENERATED FROM LUXURY VILLAS: " + villaIncome + "\n");

        bw.write("TOTAL INCOME GENERATED FROM APARTMENTS: " + apartmentIncome + "\r\n");
        bw.write("TOTAL INCOME GENERATED FROM HOUSES: " + houseIncome + "\r\n");
        bw.write("TOTAL INCOME GENERATED FROM LUXURY VILLAS: " + villaIncome +"\r\n");


        bw.close(); // Closes the file

    }

	public void PrintAllVehicles() throws IOException {

        /*Creates a file called OutputVehicle.txt and prepares to write to it*/
		File output = new File("OutputVehicle.txt");
		FileOutputStream fos = new FileOutputStream(output);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		/*Prints all attributes of Car ArrayList and writes to OutputVehicle.txt*/
	   	for(int i=0; i<Car.size(); i++)
        {
            System.out.println("Car " + (i+1) );
            System.out.println("--------------");
            System.out.println("REG#: " + Car.get(i).getRegNum());
            System.out.println("OWNER: " + Car.get(i).getOwnerName());
            System.out.println("TOTAL RENTED DAYS: " + Car.get(i).getTotalRentalDays());
            System.out.println("COST PER DAY: " + Car.get(i).getRentalCostPerDay());
            System.out.println("NUMBER OF PASSENGERS: " + Car.get(i).getPassengersNumber() + "\n");

			bw.write("Car " + (i + 1)+ "\r\n");
			bw.write("--------------"+ "\r\n");
			bw.write("REG#: " + Car.get(i).getRegNum() + "\r\n");
			bw.write("OWNER: " + Car.get(i).getOwnerName()+ "\r\n");
			bw.write("TOTAL RENTED DAYS: " + Car.get(i).getTotalRentalDays()+ "\r\n");
			bw.write("COST PER DAY: " + Car.get(i).getRentalCostPerDay()+ "\r\n");
			bw.write("NUMBER OF PASSENGERS: " + Car.get(i).getPassengersNumber()+ "\r\n\r\n");

        }

        /*Prints all attributes of Truck ArrayList and writes to OutputVehicle.txt*/
        for(int i=0; i<Truck.size(); i++)
        {
            System.out.println("Truck " + (i+1) );
            System.out.println("--------------");
            System.out.println("REG#: " + Truck.get(i).getRegNum());
            System.out.println("OWNER: " + Truck.get(i).getOwnerName());
            System.out.println("TOTAL RENTED DAYS: " + Truck.get(i).getTotalRentalDays());
            System.out.println("COST PER DAY: " + Truck.get(i).getRentalCostPerDay());
            System.out.println("CARGO WEIGHT: " + Truck.get(i).getCargoWeight() + "kg" + "\n");

			bw.write("Truck " + (i+1)+ "\r\n");
			bw.write("--------------"+ "\r\n");
			bw.write("REG#: " + Truck.get(i).getRegNum());
			bw.write("OWNER: " + Truck.get(i).getOwnerName()+ "\r\n");
			bw.write("TOTAL RENTED DAYS: " + Truck.get(i).getTotalRentalDays()+ "\r\n");
			bw.write("COST PER DAY: " + Truck.get(i).getRentalCostPerDay()+ "\r\n");
			bw.write("CARGO WEIGHT: " + Truck.get(i).getCargoWeight() + "kg"+ "\r\n\r\n");

        }

        /*Prints all income generated from Vehicle and writes to OutputVehicle.txt*/
        System.out.println("TOTAL INCOME GENERATED FROM CARS: " + carIncome);
        System.out.println("TOTAL INCOME GENERATED FROM TRUCKS: " + truckIncome);

        bw.write("TOTAL INCOME GENERATED FROM CARS: " + carIncome +"\r\n");
        bw.write("TOTAL INCOME GENERATED FROM TRUCKS: " + truckIncome + "\r\n");


		bw.close(); // Closes file
	}

    /*Methods to calculate total income generated for each property and vehicle*/
    public void CalculatePropertyTotalIncome() {


        for(int i=0;i<Apartment.size();i++) {
            if(Apartment.get(i).TotalRentDays > 0) // if apartment was rented
            apartmentIncome += Apartment.get(i).getIncome();
        }

        for(int i=0;i<House.size();i++){
            if(House.get(i).TotalRentDays > 0) // if house was rented
            houseIncome += House.get(i).getIncome();
        }

        for(int i=0;i<LuxuryVilla.size();i++) {
            if(LuxuryVilla.get(i).TotalRentDays > 0) // if luxuryvilla was rented
            villaIncome += LuxuryVilla.get(i).getIncome();
        }

    }

	public void CalculateVehicleTotalIncome(){

        for(int i=0;i<Car.size();i++) {
            if(Car.get(i).TotalRentalDays > 0) // if car was rented
                carIncome += Car.get(i).getIncome();
        }
        for(int i=0;i<Truck.size();i++) {
            if(Truck.get(i).TotalRentalDays > 0) // if truck was rented
                truckIncome += Truck.get(i).getIncome();
        }

	}

    /*Methods used to calculate the total number of days each property and vehicles is rented*/
    public void GiveRentalProperty() {

        boolean found = false; // boolean keeps track whether register number of property is found
        boolean house = false;  // boolean keeps track whether the register number belongs to a house
        boolean apartment = false; // boolean keeps track whether the register number belongs to an apartment
        boolean villa = false; // boolean keeps track whether the register number belongs to a villa

        final int MAXIMUM = Math.max(Math.max(House.size(), Apartment.size()), LuxuryVilla.size()); // gets the biggest ArrayList size.
        int index = 0; //keeps track at which index the register number of a property was found

        /*UI Layout for asking user to enter PropertyID and RentalDays*/
        JTextField PropertyID = new JTextField();
        JTextField Days = new JTextField();

        Object[] message = {"PropertyID (1000-3999):", PropertyID,
                "Rent Days:", Days};

        int option = JOptionPane.showConfirmDialog(null, message, "Property Rent Details", JOptionPane.OK_CANCEL_OPTION);

        /*While loop which tries to find entered PropertyID*/
        int i = 0;
        while(i<MAXIMUM) {


            if (i<House.size() && House.get(i).getRegisterNumber() == Integer.parseInt(PropertyID.getText())) { // Checks if i is less than size of house ArrayList and if PropertyID belongs to register
                house = true;                                                                                   // number of current house object
                found = true;
                index = i;
            } else if (i<Apartment.size() && Apartment.get(i).getRegisterNumber() == Integer.parseInt(PropertyID.getText())) {// Checks if i is less than size of house ArrayList and if PropertyID belongs to register
                apartment = true;                                                                                             // number of current apartment object
                found = true;
                index = i;
            } else if (i<LuxuryVilla.size() && LuxuryVilla.get(i).getRegisterNumber() == Integer.parseInt(PropertyID.getText())) {  // Checks if i is less than size of house ArrayList and if PropertyID belongs to register
                villa = true;                                                                                                       // number of current villa object
                found = true;
                index = i;
            }

            i++;
        }

        if(!found) // If the PropertyID does not exist
            JOptionPane.showMessageDialog(null,"Property ID " +Integer.parseInt(PropertyID.getText())+" does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        else
            found = false; // Reset found boolean if exists


        if (option == JOptionPane.OK_OPTION) { //If user clicked OK
            if (house) {
                House.get(index).RentalItem(Integer.parseInt(Days.getText())); //Add the rented days to TotalRentalDays in current House object
            } else if (apartment) {
                Apartment.get(index).RentalItem(Integer.parseInt(Days.getText())); //Add the rented days to TotalRentalDays in current Apartment object
            } else if (villa) {
                LuxuryVilla.get(index).RentalItem(Integer.parseInt(Days.getText())); //Add the rented days to TotalRentalDays in current LuxuryVilla object
            }
        } else {
            return;
        }

        /*Resets the JTextFields*/
        PropertyID.setText("");
        Days.setText("");

        /*Resets all booleans, preparing for next time the method is called*/
        if(house || apartment || villa) {
            house = false;
            apartment = false;
            villa = false;
        }
    }

    public void GiveRentalVehicle(){
        boolean found = false; // boolean keeps track whether register number of a vehicle is found
        boolean car = false; // boolean keeps track whether register number of car is found
        boolean truck = false; // boolean keeps track whether register number of truck is found

        int MAXIMUM = Math.max(Car.size(), Truck.size()); // gets the biggest ArrayList size
        int index = 0; //keeps track at which index the register number of a vehicle was found

        /*UI Layout for asking user to enter VehicleID and RentalDays*/
        JTextField VehicleID = new JTextField();
        JTextField Days = new JTextField();

        Object[] message = {"VehicleID (4000-5999):", VehicleID,
                "Rent Days:", Days};

        int option = JOptionPane.showConfirmDialog(null, message, "Vehicle Rent Details", JOptionPane.OK_CANCEL_OPTION);

        /*While loop which tries to find entered VehicleID*/
        int i = 0;
        while(i<MAXIMUM) {
            if (i < Car.size() && Car.get(i).getRegNum() == Integer.parseInt(VehicleID.getText())) {// Checks if i is less than size of Car ArrayList and if VehicleID belongs to register
                car = true;                                                                         // number of current Car object
                found = true;
                index = i;
            } else if (i< Truck.size() && Truck.get(i).getRegNum() == Integer.parseInt(VehicleID.getText())) {// Checks if i is less than size of Truck ArrayList and if VehicleID belongs to register
                truck = true;                                                                                 // number of current Truck object
                found = true;
                index = i;
            }

            i++;
        }

        if(!found) // If the VehicleID was not found
            JOptionPane.showMessageDialog(null,"VehicleID " +Integer.parseInt(VehicleID.getText())+" does not exist.","Error", JOptionPane.ERROR_MESSAGE);
        else
            found = false; // Reset found boolean if exists

        if (option == JOptionPane.OK_OPTION) {
            if (car) {
                Car.get(index).RentalItem(Integer.parseInt(Days.getText())); //Add the rented days to TotalRentalDays in current Car object
            } else if (truck) {
                Truck.get(index).RentalItem(Integer.parseInt(Days.getText())); //Add the rented days to TotalRentalDays in current Truck object
            }
        } else {
            return;
        }

        /*Resets the JTextFields*/
        VehicleID.setText("");
        Days.setText("");

        /*Resets all booleans, preparing for next time the method is called*/
        if(car || truck) {
            car = false;
            truck = false;
        }
    }

    /*Method which asks the user whether he/she would like to rent again*/
    public void Decision() {

        decision[1] = ""; // resets the object to be rented

        /*Do While loop which asks the user whether he wants to rent an object again*/
        do{
            this.decision[0] = JOptionPane.showInputDialog(null, "Would you like to rent again? Y\\N");
        }while(!this.decision[0].equalsIgnoreCase("Y") && !this.decision[0].equalsIgnoreCase("N")); // ensures correct input

    }
}
