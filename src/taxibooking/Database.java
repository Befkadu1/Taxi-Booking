/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxibooking;

/**
 *
 * @author Befkadu Degefa
 */
public class Database
{
    //Creation  of a Driver data type array of a size of 7
    public  Driver driverArray[] = new Driver[7];
    
public  Driver [] searchDrivers ()
{

    driverArray[0] = new Driver("Anders","2", new TaxiCar("ABC123", true));  //Anders drives Taxi ABC123
    driverArray[1] = new Driver("Ben", "2", new TaxiCar("DEF456", false));  //Ben drives Taxi DEF456
    driverArray[2] = new Driver("David","3", new TaxiCar("FGH789", false));//David drives Taxi FGH789
    driverArray[3] = new Driver("Elias","4", new TaxiCar("AAA123", false)); //Elias drives Taxi AAA123
    driverArray[4] = new Driver("Tom","5", new TaxiCar("BBB123", false));  //Tom drives Taxi BBB123
    driverArray[5] = new Driver("Kate","2", new TaxiCar("AAA123", false)); //Kate drives Taxi AAA123
    driverArray[6] = new Driver("Ana","4", new TaxiCar("BBB123", false)); //Ana drives Taxi BBB123
    
    return driverSearching(driverArray);
    

    }

public Driver [] driverSearching(Driver [] arrayDr)
{
    for(Driver driverList: arrayDr)
    {
        //System.out.println("Driver's name " + driverList.getDriverName()); //For test
    }
    
    return arrayDr;
}
    
}
