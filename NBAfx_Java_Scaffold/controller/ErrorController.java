package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class ErrorController extends Controller<Validator> {
    @FXML
    private Text messages; 

    @FXML 
    private Button okayButton;

    @FXML private void initialize(){
        String message = "";
        for (int i = 0; i < model.errors().size(); i++){
            message += model.errors().get(i);
        }
        messages.setText(message.toString());
    }
    
    @FXML private void handleOK(){
        stage.close();
    }
}
