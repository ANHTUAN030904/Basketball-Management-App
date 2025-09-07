package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import model.Teams;
import model.Team;

public class AddTeamController extends Controller<Teams> {
    @FXML 
    private TextField nameTextField;

    @FXML
    private Button addButton;
    
    private final String teamErrorMessage = " Team already exists\n";
    
    public final Teams getTeams(){
        return model;
    }
    
    @FXML 
    private void addTeam(){
        String name = nameTextField.getText();
        Validator validator = new Validator();
        
        if (!validator.isValid(name)){
            validator.generateErrors(name);
            openErrorWindow(validator);
        }
        else if (!model.hasTeam(name)){
            Team team = new Team(name);
            model.addTeam(team);
            stage.close();
        }
        else{
            validator.addError(name + teamErrorMessage);
            openErrorWindow(validator);
        }
    }
    
    private void openErrorWindow(Validator validator){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/error.png"));
            
            ViewLoader.showStage(validator, "/view/error.fxml", "Error!", stage);
        } catch (IOException ex) {
            Logger.getLogger(AddTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
