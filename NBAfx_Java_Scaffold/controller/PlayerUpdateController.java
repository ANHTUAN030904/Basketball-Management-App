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
import model.Player;

public class PlayerUpdateController extends Controller<Player>  {
    @FXML 
    private TextField nameTextField;

    @FXML 
    private TextField creditTextField;

    @FXML 
    private TextField ageTextField;

    @FXML 
    private TextField noTextField;
    
    @FXML 
    private Button addButton;

    @FXML 
    private Button updateButton;
    
    @FXML 
    private Button closeButton;
    
    public final Player getPlayer(){
        return model;
    }
    
    @FXML
    private void initialize(){
        nameTextField.setText(getPlayer().getName());
        creditTextField.setText(getPlayer().getCredit().toString());
        ageTextField.setText(getPlayer().getAge().toString());
        noTextField.setText(getPlayer().getNo().toString());
        addButton.setDisable(getPlayer().getName() == null);
        updateButton.setDisable(getPlayer().getName() == null);
    }
    
    @FXML
    private void addPlayer(){
        String name = nameTextField.getText();
        String credit = creditTextField.getText();
        String age = ageTextField.getText();
        String no = noTextField.getText();
        Validator validator = new Validator();
        
        if (!validator.isValid(name, credit, age, no)){
            validator.generateErrors(name, credit, age, no);
            openErrorWindow(validator);
        } else {
            Player player = getPlayer();
            player.setName(name);
            player.setAge(Integer.parseInt(age));
            player.setCredit(Double.parseDouble(credit));
            player.setNo(Integer.parseInt(no));
            player.getTeam().getPlayers().updateStats();
            player.getTeam().getPlayers().addPlayer(player);
            close();
        }
    }
    
    private void openErrorWindow(Validator validator){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/error.png"));
            
            ViewLoader.showStage(validator, "/view/error.fxml", "Input Errors", stage);
        } catch (IOException ex) {
            Logger.getLogger(PlayerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void updatePlayer(){
        String name = nameTextField.getText();
        String credit = creditTextField.getText();
        String age = ageTextField.getText();
        String no = noTextField.getText();
        Validator validator = new Validator();
        
        if (!validator.isValid(name, credit, age, no)){
            validator.generateErrors(name, credit, age, no);
            openErrorWindow(validator);
        } else{
            Player player = getPlayer();
            player.setName(name);
            player.setAge(Integer.parseInt(age));
            player.setCredit(Double.parseDouble(credit));
            player.setNo(Integer.parseInt(no));
            // This to update all the windows
            getPlayer().getTeam().getPlayers().updateStats();
            close();
        }
    }
    
    
    @FXML
    private void close(){
        stage.close();
    }
    
}
