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
public class Passanger
{
    private String firstName;
    private String lastName;
    private String date;
    private String time;  
    private String source;
    private String destination;



    public Passanger(String firstName, String lastName, String date, String time, String source, String destination )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.time = time;
        this.source = source;
        this.destination = destination;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public String getSource()
    {
        return source;
    }

    public String getDestination()
    {
        return destination;
    }
    
}
