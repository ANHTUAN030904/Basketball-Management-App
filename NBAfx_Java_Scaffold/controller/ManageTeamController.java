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
import model.Team;

public class ManageTeamController extends Controller<Team> {
    @FXML 
    private TextField teamNameTextField;

    @FXML
    private TableView<Player> playerTableView;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, String> creditColumn;

    @FXML
    private TableColumn<Player, String> ageColumn;

    @FXML
    private TableColumn<Player, String> noColumn;

    @FXML 
    private Button addButton;

    @FXML 
    private Button updateButton;

    @FXML 
    private Button deleteButton;

    @FXML 
    private Button saveAndCloseButton;
    
    public final Team getTeam(){
        return model;
    }

    @FXML
    private Player getPlayer(){
        return playerTableView.getSelectionModel().getSelectedItem();
    }

    @FXML 
    private void initialize(){
        teamNameTextField.setText(getTeam().getName());
        playerTableView.setItems(getTeam().getPlayers().getPlayersList());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        creditColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString("%.1f"));
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
        noColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());
        playerTableView.getSelectionModel().selectedItemProperty().addListener((o, oldAcct, newAcct) -> {
            addButton.setDisable(getPlayer() != null);
            updateButton.setDisable(getPlayer() == null);
            deleteButton.setDisable(getPlayer() == null);
        });
    }

    @FXML 
    private void addPlayer(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(createNewPlayer(), "/view/PlayerUpdateView.fxml", "Adding new player", stage);
        } catch (IOException ex) {
            Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Player createNewPlayer(){
        Player player = new Player("", 0.0, 0, 0);
        player.setTeam(getTeam());
        return player;
    }

    @FXML 
    private void updatePlayer(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getPlayer(), "/view/PlayerUpdateView.fxml", "Updating Player: " + getPlayer().getName(), stage);
            stage.setOnHiding(event -> {
                playerTableView.setItems(null);
                playerTableView.layout();
                playerTableView.setItems(getTeam().getCurrentPlayers());
            });
            addButton.setDisable(false);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deletePlayer(){
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            getTeam().getPlayers().removePlayer(selectedPlayer);
            playerTableView.getSelectionModel().clearSelection();
            addButton.setDisable(false);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    @FXML 
    private void saveAndClose(){
        String name = teamNameTextField.getText();
        getTeam().setName(name);
        Stage stage = (Stage) saveAndCloseButton.getScene().getWindow();
        stage.close();
    }
}
