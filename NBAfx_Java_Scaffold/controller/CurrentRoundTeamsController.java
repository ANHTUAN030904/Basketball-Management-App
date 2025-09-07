package controller;

import javafx.beans.property.SimpleStringProperty;
import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Game;
import model.Season;

public class CurrentRoundTeamsController extends Controller<Season>  {
    @FXML
    private Label roundLabel;
  
    @FXML
    private TableView<Game> gamesTableView;

    @FXML
    private TableColumn<Game, String> firstTeamColumn;

    @FXML
    private TableColumn<Game, String> vsColumn;

    @FXML
    private TableColumn<Game, String> secondTeamColumn;

    @FXML
    private Button closeButton;

    public final Season getSeason(){
        return model;
    }

    @FXML
    private void initialize(){
        int round = getSeason().round() + 1;
        roundLabel.setText("Round: " + round);
        gamesTableView.setItems(getSeason().getCurrentSchedule());
        firstTeamColumn.setCellValueFactory(cellData -> cellData.getValue().team1());
        vsColumn.setCellValueFactory(cellData -> new SimpleStringProperty("VS"));
        secondTeamColumn.setCellValueFactory(cellData -> cellData.getValue().team2());
    }

    @FXML
    private void close(){
        stage.close();
    }
}

