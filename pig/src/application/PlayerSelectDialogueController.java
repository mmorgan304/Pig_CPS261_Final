package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerSelectDialogueController implements GameReadyCallback{

	ArrayList<Player> players = DataManager.getInstance().getPlayers();
	private PigController pigController;

	@FXML
	public Button createNewPlayerButton;
	@FXML
	public Button newGameButton;
	@FXML
	public Button viewStatisticsButton;
	@FXML
	public ComboBox<String> player1ComboBox;
	@FXML
	public ComboBox<String> player2ComboBox;
	@FXML
	public RadioButton onePlayerGameToggle;
	@FXML
	public RadioButton twoPlayerGameToggle;
	@FXML
	public ToggleGroup gameTypeSelection;

	/**********************
	 * Button controls
	 * ********************/
	public void openPlayerCreateDialogue() throws IOException {
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
	
	public void openDisplayGameHistory() throws IOException {
		AnchorPane createPlayerDialogue = (AnchorPane) FXMLLoader
				.load(getClass().getResource("DisplayGameHistory.fxml"));
		Stage displayGameHistory = new Stage();
		displayGameHistory.setScene(new Scene(createPlayerDialogue, 400, 500));
		displayGameHistory.initModality(Modality.APPLICATION_MODAL);
		try {
			Stage playerSelect = (Stage) viewStatisticsButton.getScene().getWindow();
			displayGameHistory.initOwner(playerSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		displayGameHistory.show();
	}
	
	public void handlePlayer1List() throws IOException, ClassNotFoundException {
		ArrayList<String> playerNameStrings = new ArrayList<String>();
		players.forEach(playerName -> playerNameStrings.add(playerName.getPlayerName()));
		ObservableList<String> playerNameList = FXCollections.observableArrayList(playerNameStrings);
		player1ComboBox.setItems(playerNameList);
	}

	public void handlePlayer2List() throws IOException, ClassNotFoundException {
		ArrayList<String> playerNameStrings = new ArrayList<String>();
		players.forEach(playerName -> playerNameStrings.add(playerName.getPlayerName()));
		ObservableList<String> playerNameList = FXCollections.observableArrayList(playerNameStrings);
		player2ComboBox.setItems(playerNameList);
	}

	/***************************
	 * Initialization controls
	 * *************************/
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
		gameTypeSelection.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == onePlayerGameToggle) {
				player2ComboBox.setDisable(true);
			} else if (newValue == twoPlayerGameToggle) {
				player2ComboBox.setDisable(false);
			}
		});
		gameTypeSelection.selectToggle(onePlayerGameToggle);
	}
	
	public void setPigController(PigController pigController) {
		this.pigController = pigController;
	}

	/***************************
	 * New game start methods
	 * *************************/
	public void startNewGame() {
		try {
			if (onePlayerGameToggle.isSelected()) {
				String selectedPlayerName = player1ComboBox.getValue();
				if (selectedPlayerName != null && !selectedPlayerName.isEmpty()) {
					Date currentDate = new Date();
					Player selectedPlayer = Player.getPlayerByName(selectedPlayerName, players);
					SinglePlayerGame newGame = new SinglePlayerGame(currentDate, selectedPlayer);
					pigController.setGame(newGame, this);
				} else {
					System.out.println("Please select a player for the game.");
				}
			} else if (twoPlayerGameToggle.isSelected()) {
				String selectedPlayer1Name = player1ComboBox.getValue();
				String selectedPlayer2Name = player2ComboBox.getValue();
				if (selectedPlayer1Name != null && !selectedPlayer1Name.isEmpty() && selectedPlayer2Name != null
						&& !selectedPlayer2Name.isEmpty()) {
					Date currentDate = new Date();
					Player selectedPlayer1 = Player.getPlayerByName(selectedPlayer1Name, players);
					Player selectedPlayer2 = Player.getPlayerByName(selectedPlayer2Name, players);
					TwoPlayerGame newGame = new TwoPlayerGame(currentDate, selectedPlayer1, selectedPlayer2);
					pigController.setGame(newGame, this);

				} else {
					System.out.println("Please select a player for the game.");
				}
			}
		} catch (Exception e) {
			System.out.println("invalid game selection");
			e.printStackTrace();
		}

	}

	@Override
	public void onGameReady() {
		Stage stage = (Stage) newGameButton.getScene().getWindow();
		stage.close();
		pigController.playGame();
	}
	
	

}
