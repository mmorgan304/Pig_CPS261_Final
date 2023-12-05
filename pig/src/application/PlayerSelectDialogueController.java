package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerSelectDialogueController {

	ArrayList<Player> players = DataManager.getInstance().getPlayers();

	@FXML
	public Button createNewPlayerButton;
	@FXML
	public ComboBox<String> player1ComboBox;
	@FXML
	public ComboBox<String> player2ComboBox;

	@FXML
	public void initialize() throws IOException {
		try {
			handlePlayer1List();
			handlePlayer2List();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayPlayerCreate() throws IOException {
		AnchorPane createPlayerDialogue = (AnchorPane) FXMLLoader
				.load(getClass().getResource("CreatePlayerDialogue.fxml"));
		Stage createPlayer = new Stage();
		createPlayer.setScene(new Scene(createPlayerDialogue, 400, 50));
		createPlayer.initModality(Modality.APPLICATION_MODAL);
		try {
			Stage playerSelect = (Stage) createNewPlayerButton.getScene().getWindow();
			createPlayer.initOwner(playerSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		createPlayer.show();
	}

	public void openPlayerCreateDialogue() {
		try {
			displayPlayerCreate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handlePlayer1List() throws IOException, ClassNotFoundException {
		ArrayList<String> playerNameStrings = new ArrayList<String>();
		players.forEach(playerName ->
				playerNameStrings.add(playerName.getPlayerName()));
		ObservableList<String> playerNameList = FXCollections.observableArrayList(playerNameStrings);
		player1ComboBox.setItems(playerNameList);
	}

	public void handlePlayer2List() throws IOException, ClassNotFoundException {
		ArrayList<String> playerNameStrings = new ArrayList<String>();
		players.forEach(playerName ->
				playerNameStrings.add(playerName.getPlayerName()));
		ObservableList<String> playerNameList = FXCollections.observableArrayList(playerNameStrings);
		player2ComboBox.setItems(playerNameList);
	}

}
