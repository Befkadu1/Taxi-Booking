/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxibooking;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Befkadu Degefa
 */
public class DriverTaxiCheckController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label labelDriverStatus; //label to show if the driver is busy or not

    @FXML
    private Label labelCarStatus;  //label to show if the car is busy or not

    @FXML
    private ListView driversListView; //ListView for the driver

    @FXML
    private ListView carsListView; //ListView for the car

    public static String driverSelection; //A string to show when the user select a driver 
    public static String carSelection;//A string to show when the user select a car 

    @FXML
    private Button availilabilityButton; //A button to check if thespecific driver is availabe or not

    @FXML
    private Button carAvaililabilityButton;//A button to check if the specific car is availabe or not

    static Database database = new Database(); //object of the Database class
    static Driver driver = new Driver(); // object of a Driver class

    //calls an array from Database class and assigned it to copyArray of the data type Driver
    public static Driver[] copyArray = database.searchDrivers(); 

    //Back button to go back to the first scene
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException
    {
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Lund Taxi Booking");
    }

    //A button to book a taxi
    @FXML
    private void bookButtonAction(ActionEvent event) throws IOException
    {
        //Synchronization between Model, View and Controller windows, active when selection of drivers and cars
        driverSelection = (String) driversListView.getSelectionModel().getSelectedItem();
        carSelection = (String) carsListView.getSelectionModel().getSelectedItem();
        System.out.println("Driver name " + driverSelection); //For test

        //To show the message if the boxes in the FXMLDocument.fxml not filled
        if (FXMLDocumentController.passangerArrayList.isEmpty())
        {
            labelDriverStatus.setText("You must fill all the boxes");
        } 
        
        else if (driverSelection == null || carSelection == null)
        {
            labelDriverStatus.setText("You must select the driver and the taxi");
        }
        else if (driverSelection != null && carSelection != null)
        {
            //Calling the database class, it returns an array of type Driver and it is assigned to the copyArray of type Driver

            for (Driver list : copyArray)
            {
                //System.out.println("copyArray name" + list.getDriverName());//For testing test

                /*
                to check if the selected taxi driver is found in the database and checking his current status
                If he is not busy, it is possible to book. If so the next step is to forward to the Comfirmation.fxml scene
                 */
                System.out.println("Before booking " + list.getDriverName() + " " + list.getTaxiCar().isActualStatus() + " (False means currently free)");

                if (driverSelection.equals(list.getDriverName() + " " + list.getExperience() + " years exp") && carSelection.equals(list.getTaxiCar().getRegistrationNum()))
                {
                    if (list.getTaxiCar().isActualStatus() == false)
                    {
                        AvailabilityCheck av = new AvailabilityCheck();
                        av.begin();
                        Parent root3 = FXMLLoader.load(getClass().getResource("Comfirmation.fxml"));
                        Scene scene = new Scene(root3);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Lund Taxi Booking");

                    } else   //To show the message if the driver is busy or not
                    {
                        labelDriverStatus.setText("Busy");
                    }
                } /*
                One taxi driver is assigned to one taxi car, if the user selects the car which is not belogs to the
                selected driver, the following message will be displayed
                 */ else if (driverSelection.equals(list.getDriverName() + " " + list.getExperience() + " years exp"))
                {
                    labelDriverStatus.setText(driverSelection + " drives " + list.getTaxiCar().getRegistrationNum());
                }
            }

        }

    }

    /*
    This method is to check the availability of the taxi driver
     */
    @FXML
    private void checkDriverAvailabilityAction(ActionEvent event) throws IOException
    {

        //to check availability of the taxi driver 
        if (event.getSource() == availilabilityButton)
        {
            driverSelection = (String) driversListView.getSelectionModel().getSelectedItem();

            //Calling the database class, it returns an array of type Driver and the returned array is assigned to the copyArray of type Driver
            Driver[] copyArray = database.searchDrivers();

            for (Driver list : copyArray)
            {
                //System.out.println("copyArray name" + list.getDriverName());//For testing test

                //A message will be displayed if the user doesn't select the driver
                if (driverSelection == null)
                {
                    labelDriverStatus.setText("Select the Driver");
                }
                
                /*
                 After the user selects the taxi driver,we need to check if the selected taxi driver is available in the database 
                 */ else if (driverSelection.equals(list.getDriverName() + " " + list.getExperience() + " years exp"))
                
                 {
                    //System.out.println("If equal: " + list.getDriverName()); //For test

                    //to check if the car is busy or free
                    if (list.getTaxiCar().isActualStatus() == true)
                    {
                        labelDriverStatus.setText("Busy");
                    } else
                    {
                        labelDriverStatus.setText("Free");
                    }
                }
            }

        }
    }

    /*
    This method is to check the availability of the taxi car
     */
    @FXML
    private void checkCarAvailabilityAction(ActionEvent event) throws IOException
    {
        carSelection = (String) carsListView.getSelectionModel().getSelectedItem();
        if (event.getSource() == carAvaililabilityButton)
        {
            //Calling the database class, it returns an array of type Driver and the returned array is assigned to the copyArray 
            //of type Driver
            Driver[] copyArray = database.searchDrivers();

            for (Driver list : copyArray)
            {
                //to show a message if the user doesn't select the a car from the list
                if (carSelection == null)
                {
                    labelCarStatus.setText("Select the car");
                }
                
                 //After the user selects the car, here we search the car in the database class to check if the car
                //is busy currently or free
                else if (carSelection.equals(list.getTaxiCar().getRegistrationNum()))
                {
                    if (list.getTaxiCar().isActualStatus() == true)
                    {
                        labelCarStatus.setText("Busy");
                    } else
                    {
                        labelCarStatus.setText("Free");
                    }
                }

            }
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //A list that allows listeners to track changes when they occur
        driversListView.setItems(FXMLDocumentController.driversObservableList);
        carsListView.setItems(FXMLDocumentController.carsObservableList);
    }

}
