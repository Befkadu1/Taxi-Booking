/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxibooking;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Befkadu Degefa
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    public TextField firstNameTextField;  //TextField for the first name

    @FXML
    public TextField lastNameTextField;// TextField for the last name

    @FXML
    public TextField dateTextField; //TextField for the date


    @FXML
    public TextField fromtimeTextField; //TextField for the time

    @FXML
    public TextField fromTextField;  //TextField for source

    @FXML
    public TextField toTextField; //TextField for the destination

    // ObservableList for the drivers' name on the ListView on scene 2
    public static ObservableList<String> driversObservableList;

    // ObservableList for the cars' name on the ListView on scene 2
    public static ObservableList<String> carsObservableList;

    //ArrayList for passangers, to save the passanger information
    public static ArrayList<Passanger> passangerArrayList;

    // label to show message if the user push register button without filling all the boxes
    @FXML
    private Label labelAlertRegistration;  


    //When the user clicks the next button, the program goes to scene 2,"DriverTaxiCheck.fxml"
    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException
    {
        Parent root2 = FXMLLoader.load(getClass().getResource("DriverTaxiCheck.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Lund Taxi Booking");
    }

    //Action for the register button
    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException
    { 

        //If the first name, last name.... not filled, the message will be displayed 
        if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || dateTextField.getText().isEmpty()
                || fromtimeTextField.getText().isEmpty() || fromTextField.getText().isEmpty() || toTextField.getText().isEmpty() ) 
        {
            labelAlertRegistration.setText("You must fill all the boxes");

        }
        
        else {

        //Date validation
        Calendar dateTime = Calendar.getInstance();
        dateTime.add(Calendar.DATE, 0);
        Date today = dateTime.getTime();  //today        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");    //The date format    
        String formatted = format1.format(dateTime.getTime());
        System.out.println("Today's date " + formatted);
        String bookedDate = dateTextField.getText(); //This is user's booked date
        
   
       
        String [] fromYearSplited = bookedDate.split("-"); //spliting to yyyy, mm, and dd
        int yyyyFrom = Integer.parseInt(fromYearSplited[0].trim());
        dateTime.set(dateTime.YEAR,yyyyFrom);
 
            int mmFrom = Integer.parseInt(fromYearSplited[1].trim());
            dateTime.set(dateTime.MONTH,mmFrom-1);

            int ddFrom = Integer.parseInt(fromYearSplited[2].trim());
            dateTime.set(dateTime.DAY_OF_MONTH,ddFrom);

        Date bookedDateSpec = dateTime.getTime();

        
        /**
         * Time validation, if the entered date is before today or not
         */
        String fromTime = fromtimeTextField.getText();
        String[] fromtimesplited = fromTime.split ( ":" );
        int hourFrom = Integer.parseInt ( fromtimesplited[0].trim());        
        int minFrom = Integer.parseInt ( fromtimesplited[1].trim() );

        /**
         * To validate the hour and date, if the booking date before today, we will get a message to write a valid date again
         */
        if(hourFrom >= 24 || hourFrom < 0  || bookedDateSpec.before(today) )
        {
            labelAlertRegistration.setText("Enter a time between 0-24 or enter a valid date");
        }

             else
        {
            //Creation of objects of Passanger class

            Passanger passanger = new Passanger(firstNameTextField.getText(), lastNameTextField.getText(),dateTextField.getText(),
                    fromtimeTextField.getText(),  fromTextField.getText(), toTextField.getText());
            
            //adding passanger objects to passangerArrayList
            passangerArrayList.add(passanger);


            //forwarding the scene to "DriverTaxiCheck.fxml" if all the boxes are filled
            Parent root2 = FXMLLoader.load(getClass().getResource("DriverTaxiCheck.fxml"));
            Scene scene = new Scene(root2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Lund Taxi Booking");
        }
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Always the passangerArrayList becomes empty when it begin, doesn't save the old elements
        passangerArrayList = new ArrayList();


        //driversObservableList initialized 
        driversObservableList = FXCollections.observableArrayList("Anders 2 years exp", "Ben 2 years exp", "David 3 years exp", "Elias 4 years exp", "Tom 5 years exp","Kate 2 years exp", "Ana 4 years exp");

        //carsObservableList initialized 
        carsObservableList = FXCollections.observableArrayList("ABC123", "DEF456", "FGH789", "AAA123", "BBB123");

    }

}
