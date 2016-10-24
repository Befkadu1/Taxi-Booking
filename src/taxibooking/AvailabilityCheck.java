/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxibooking;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import static taxibooking.DriverTaxiCheckController.driverSelection;

/**
 *
 * @author Befkadu Degefa
 */
public class AvailabilityCheck
{
    int hourFrom;
    int minFrom;
    int amountFrom;
    int currentHourFromTo;
    int currentMinFromTo;
 

    public AvailabilityCheck()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //actual current time
        System.out.println(sdf.format(cal.getTime()));
        String[] currentFromtimesplited = sdf.format(cal.getTime()).split(":"); //to split the current time in to hh and mm
        currentHourFromTo = Integer.parseInt(currentFromtimesplited[0].trim());
        currentMinFromTo = Integer.parseInt(currentFromtimesplited[1].trim());

        //booked time
        String fromTime = FXMLDocumentController.passangerArrayList.get(0).getTime();
        String[] fromtimesplited = fromTime.split(":"); //to split hour and minute
        hourFrom = Integer.parseInt(fromtimesplited[0].trim());
        minFrom = Integer.parseInt(fromtimesplited[1].trim());
        
        //Booking time starts here
        if(currentHourFromTo >= hourFrom)
        {
            begin();  
        }
        
    }
    
    public void begin()
    {
        //the booked time starts when amountFrom(the difference of the booked and current actual time) becomes 0
        amountFrom = minFrom - currentMinFromTo;
       if (amountFrom == 0)

        {
            for (Driver list : DriverTaxiCheckController.copyArray)
            {
                //to check if the selected driver name is found in the Database class
                if (driverSelection.equals(list.getDriverName() + " " + list.getExperience() + " years exp") && DriverTaxiCheckController.carSelection.equals(list.getTaxiCar().getRegistrationNum()))
                {
                    //To check if the status of the car busy or free, if it is free it is going to be set busy
                    if (list.getTaxiCar().isActualStatus() == false)
                    {

                        list.getTaxiCar().setActualStatus(true);
                    }
                }
                
                //to show the status of the driver on the console window, for the demo purpose
                    System.out.println("Test " + list.getDriverName() + " availability " + list.getTaxiCar().isActualStatus());

            }
        }
    }
    
    //This method is to end the travel
    public void end()
    {

            for (Driver list : DriverTaxiCheckController.copyArray)
            {
                //Selecting the taxi driver
                if (driverSelection.equals(list.getDriverName() + " " + list.getExperience() + " years exp") && DriverTaxiCheckController.carSelection.equals(list.getTaxiCar().getRegistrationNum()))
                {
                    //to make the driver free when the travel ends
                    if (list.getTaxiCar().isActualStatus() == true)
                    {

                        list.getTaxiCar().setActualStatus(false);
                    }
                }
                
                //to show the status of the driver on the console window , for the demo purpose
                System.out.println("Test " + list.getDriverName() + " availability " + list.getTaxiCar().isActualStatus());
            }
    
    }

}
