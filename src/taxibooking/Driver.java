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
public class Driver
{
    private String driverName;
    private String experience;
    private TaxiCar taxiCar;

    public Driver ()
    {
        
    }

    public Driver(String driverName,  String experience, TaxiCar taxiCar)
    {
        this.driverName = driverName;
        this.experience = experience;
        this.taxiCar = taxiCar;
    }

    public TaxiCar getTaxiCar()
    {
        return taxiCar;
    }

    public void setTaxiCar(TaxiCar taxiCar)
    {
        this.taxiCar = taxiCar;
    }

    public String getDriverName()
    {
        return driverName;
    }

    public String getExperience()
    {
        return experience;
    }

    
}
