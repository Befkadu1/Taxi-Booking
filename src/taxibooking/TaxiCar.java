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
public class TaxiCar
{
    private String registrationNum;
    private static boolean actualStatus;

    public TaxiCar(String registrationNum, boolean actualStatus)
    {
        this.registrationNum = registrationNum;
        this.actualStatus = actualStatus;
    }

    public String getRegistrationNum()
    {
        return registrationNum;
    }

    public boolean isActualStatus()
    {
        return actualStatus;
    }

    public void setActualStatus(boolean actualStatus)
    {
        this.actualStatus = actualStatus;
    }
    
}
