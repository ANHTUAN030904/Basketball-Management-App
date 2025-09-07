package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Season;
import model.Team;
import model.Game;

public class TeamsRoundController extends Controller<Season>  {
    @FXML 
    private Label roundLabel;

    @FXML 
    private ListView<Team> teamsListView;

    @FXML 
    private Button nextButton;

    @FXML 
    private TableView<Game> gamesTableView;

    @FXML 
    private TableColumn<Game, String> gameTableColumn;

    @FXML 
    private TableColumn<Game, String> firstTeamTableColumn;

    @FXML 
    private TableColumn<Game, String> secondTeamTableColumn;

    @FXML 
    private Button arrangeButton;
    
    private Season getSeason(){
        return model;
    }
    
    @FXML 
    private void initialize(){
        int round = getSeason().round();
        roundLabel.setText("Round: " + (round+1));
        teamsListView.setItems(getSeason().getCurrentTeams());
        teamsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teamsListView.getSelectionModel().selectedItemProperty().addListener((o, oldItem, newItem) -> {
            nextButton.setDisable(teamsListView.getSelectionModel().getSelectedItems().size() != 2);
        });

        gamesTableView.setItems(getSeason().getCurrentSchedule());
        gameTableColumn.setCellValueFactory(cellData -> cellData.getValue().termProperty().asString());
        firstTeamTableColumn.setCellValueFactory(cellData -> cellData.getValue().team1());
        secondTeamTableColumn.setCellValueFactory(cellData -> cellData.getValue().team2());
    }
    
    @FXML 
    private void addTeams(){
        getSeason().addTeams(teamsListView.getSelectionModel().getSelectedItems());
        arrangeButton.setDisable(getSeason().getCurrentTeams().size() != 0);
    }
    
    @FXML
    private void arrangeSeason(){
        stage.close();
    }
}