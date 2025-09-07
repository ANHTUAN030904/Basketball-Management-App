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
import model.Team;
import model.Teams;

public class TeamsController extends Controller<Teams>{
    @FXML 
    private TableView<Team> teamsTableView;

    @FXML
    private TableColumn<Team,String> nameColumn;

    @FXML
    private TableColumn<Team, String> numberOfPlayersColumn;

    @FXML
    private TableColumn<Team, String> avgPlayerCreditColumn; 

    @FXML
    private TableColumn<Team, String> avgAgeColumn;

    @FXML
    private Button addButton;

    @FXML 
    private Button manageButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button closeButton;

    public final Teams getTeams(){
        return model;
    }

    public final Team getTeam(){
        return teamsTableView.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void initialize(){
        teamsTableView.setItems(getTeams().currentTeams());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        numberOfPlayersColumn.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asString());
        avgPlayerCreditColumn.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asString("%.2f"));
        avgAgeColumn.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asString("%.2f"));
        teamsTableView.getSelectionModel().selectedItemProperty().addListener((o, oldAcct, newAcct) -> {
            addButton.setDisable(getTeam() != null);
            manageButton.setDisable(getTeam() == null);
            deleteButton.setDisable(getTeam() == null);
        });
    }

    @FXML 
    private void addTeam(){
        try{
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(model, "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex){
            Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void manageTeam(){
        try{
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeam(), "/view/ManageTeamView.fxml", "Managing Team: " + getTeam() , stage);
        } catch (IOException ex){
            Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteTeam(){
        getTeams().remove(getTeam());
    }

    @FXML
    private void close(){
        stage.close();
    }
}

