package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Record;
import javafx.collections.ObservableList;

public class RecordController extends Controller<ObservableList<Record>> {
    @FXML
    private TableView<Record> recordsTableView;

    @FXML
    private TableColumn<Record, String> roundTableColumn;

    @FXML
    private TableColumn<Record, String> gamesTableColumn;

    @FXML
    private TableColumn<Record, String> winningTeamTableColumn;

    @FXML
    private TableColumn<Record, String> losingTeamTableColumn;

    @FXML
    private Button closeButton;
    
    private final ObservableList<Record> getRecord(){
        return model;
    }

    @FXML
    private void initialize(){
        recordsTableView.setItems(getRecord());
        roundTableColumn.setCellValueFactory(cellData -> cellData.getValue().roundProperty().asString());
        gamesTableColumn.setCellValueFactory(cellData -> cellData.getValue().gameNumberProperty().asString());
        winningTeamTableColumn.setCellValueFactory(cellData -> cellData.getValue().winTeamProperty());
        losingTeamTableColumn.setCellValueFactory(cellData -> cellData.getValue().loseTeamProperty());
    }
    
    @FXML
    private void close(){
        stage.close();
    }

    
}







