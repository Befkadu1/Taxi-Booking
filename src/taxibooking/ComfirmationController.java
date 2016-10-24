/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxibooking;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Befkadu Degefa
 */
public class ComfirmationController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField firstName;  //TextField for the first name on the scene 3, confirmation.fxml

    @FXML
    private TextField lastName; //TextField for the last name on the scene 3, confirmation.fxml

    @FXML
    private TextField from; //TextField for the user's source city on the scene 3, confirmation.fxml

    @FXML
    private TextField to; //TextField for the user's destination on the scene 3, confirmation.fxml

    @FXML
    private TextField date; //TextField for the date on the scene 3, confirmation.fxml

    @FXML
    private TextField fromTime; //TextField for the booked time on the scene 3, confirmation.fxml 
    
    @FXML
    private TextField toTime;

    @FXML
    private TextField driverName; //TextField for the taxi driver name on the scene 3, confirmation.fxml

    @FXML
    private TextField registrationNumber; //TextField for the taxi's registration number on the scene 3, confirmation.fxml
    
    @FXML
    private Button closeButton; //Button for closing the application  
 
    AvailabilityCheck av = new AvailabilityCheck();

    //closing the app
    @FXML
    private void closeButtonAction()
    {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    //This method is to go back to DriverTaxiCheck.fxml
    @FXML
    private void backConfirmationButtonAction(ActionEvent event) throws IOException
    {
        Parent root2 = FXMLLoader.load(getClass().getResource("DriverTaxiCheck.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Lund Taxi Booking");
    }

    //This method is a demo to show whether a drivers are currently busy or free, for testing purpose
    @FXML
    private void checkDemoButtonAction()
    {

            av = new AvailabilityCheck();
       
    } 
    
    //This method is to end the travel time, the busy driver becomes free when this method is enabled
    @FXML
    private void stopButtonAction()
    {
            Calendar cal = Calendar.getInstance();
            av.end();
            toTime.setText(cal.getTime().toString());//to show the end time
       
    } 

    //to show the confirmation
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        firstName.setText(FXMLDocumentController.passangerArrayList.get(0).getFirstName()); //to show first name
        lastName.setText(FXMLDocumentController.passangerArrayList.get(0).getLastName());//to show last name
        from.setText(FXMLDocumentController.passangerArrayList.get(0).getSource()); //to show the source
        to.setText(FXMLDocumentController.passangerArrayList.get(0).getDestination()); //to show the destination
        date.setText(FXMLDocumentController.passangerArrayList.get(0).getDate());//to show the date
        fromTime.setText(FXMLDocumentController.passangerArrayList.get(0).getTime());//to show the booking time
        
        driverName.setText(DriverTaxiCheckController.driverSelection); //to show the driver name
        registrationNumber.setText(DriverTaxiCheckController.carSelection);//to show the taxi's registration number

        
    }

}
