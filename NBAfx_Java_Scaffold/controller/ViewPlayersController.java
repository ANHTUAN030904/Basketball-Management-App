package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Player;
import model.Players;
import model.Team;
import model.Teams;

public class ViewPlayersController extends Controller<Teams>{
    @FXML 
    private TableView<Player> playerTableView;

    @FXML 
    private TableColumn<Player, String> teamColumn;

    @FXML 
    private TableColumn<Player, String> nameColumn;

    @FXML 
    private TableColumn<Player, String> creditColumn;

    @FXML 
    private TableColumn<Player, String> ageColumn;

    @FXML 
    private TableColumn<Player, String> noColumn;

    @FXML 
    private TableColumn<Player, String> levelColumn;
    
    @FXML 
    private TextField levelTextField;

    @FXML 
    private TextField nameTextField;

    @FXML 
    private TextField ageFromTextField;

    @FXML 
    private TextField ageToTextField;

    ObservableList<Players> playersList;

    ObservableList<Player> playerList;

    private final Teams getTeams(){
        return model; 
    }

    @FXML
    private void initialize(){
        playersList = getPlayers();
        playerList = getALLPlayers(playersList);
        playerTableView.setItems(playerList);
        teamColumn.setCellValueFactory(cellData -> cellData.getValue().getTeam().nameProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        creditColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString("$%.2f"));
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
        noColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
        levelTextField.textProperty().addListener((obj, oldText, newText) -> displayFilteredPlayer());
        nameTextField.textProperty().addListener((obj, oldText, newText) -> displayFilteredPlayer());
        ageFromTextField.textProperty().addListener((obj, oldText, newText) -> displayFilteredPlayer());
        ageToTextField.textProperty().addListener((obj, oldText, newText) -> displayFilteredPlayer());
    }

    private void displayFilteredPlayer(){
        playerList = getAllPlayersByFilter(playersList);
        if (playerList.isEmpty()) {
            playerTableView.setPlaceholder(new Label("Players list is not loaded."));
        } else {
            playerTableView.setPlaceholder(null);
        }
        playerTableView.setItems(playerList);

    }

    private ObservableList<Players> getPlayers()  {
        ObservableList<Players> playersList = FXCollections.<Players>observableArrayList();
        ObservableList<Team> teamList = getTeams().currentTeams();
        for (int i = 0; i < teamList.size(); i++){
            playersList.add(teamList.get(i).getPlayers());
        }
        return playersList;
    }

    private ObservableList<Player> getALLPlayers(ObservableList<Players> playerList){
        ObservableList<Player> players = FXCollections.<Player>observableArrayList();
        for (int i = 0; i < playersList.size(); i++){
            ObservableList<Player> getPlayers = playersList.get(i).getPlayersList();
            for (int j = 0; j < getPlayers.size(); j++)
            {
                players.add(getPlayers.get(j));
            }
        }
        return players;
    }

    private ObservableList<Player> getAllPlayersByFilter(ObservableList<Players> playersList){
        ObservableList<Player> players = FXCollections.<Player>observableArrayList();
        String name = hasSameText(nameTextField.getText()) ? nameTextField.getText() : "";
        String level = hasSameText(levelTextField.getText()) ? levelTextField.getText() : "" ;
        int ageFromField = hasSameText(ageFromTextField.getText()) ? Integer.parseInt(ageFromTextField.getText()) : 0;
        int ageToField = hasSameText(ageToTextField.getText()) ? Integer.parseInt(ageToTextField.getText()) : 100;
        for (int i = 0; i < playersList.size(); i++){
            playersList.get(i).filterList(name, level, ageFromField, ageToField);
            ObservableList<Player> getFilteredPlayers = playersList.get(i).getFilteredPlayersList();
            for (int j = 0; j < getFilteredPlayers.size(); j++){
                players.add(getFilteredPlayers.get(j));
            }
        }
        return players;
    }

    @FXML
    private void close(){
        stage.close();
    }
    private boolean hasSameText(String a){
        return !a.isEmpty();
    }
}

