package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePlayerController {

	ArrayList<Player> players = DataManager.getInstance().getPlayers();

	@FXML
	public Button addPlayerButton;
	@FXML
	public TextField newPlayerNameTextField;

	public void addPlayer() {
		Player newPlayer = new Player(newPlayerNameTextField.getText());
		players.add(newPlayer);
		Stage stage = (Stage) addPlayerButton.getScene().getWindow();
		stage.close();
	}

}
