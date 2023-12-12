package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinnerCongratulationController {

	private PigController pigController;
	@FXML
	private Text congratulationsText;
	@FXML
	private Button newGameButton;
	@FXML
	private Button viewStatisticsButton;
	@FXML
	private Button quitButton;
	
	public void initialize(PigController pigController) {
		this.pigController = pigController;
	}
	
	public void setWinnerText(Player winner) {
		congratulationsText.setText(winner.getPlayerName() + " wins!");
	}
	
	public void startNewGame() throws IOException {
		pigController.newGameMenuItem();
		Stage stage  = (Stage)newGameButton.getScene().getWindow();
	    stage.close();
	}
	
	public void viewStatistics() throws IOException {
		pigController.openDisplayGameHistory();
		Stage stage  = (Stage)viewStatisticsButton.getScene().getWindow();
	    stage.close();
	}
	
	public void quitGame() {
		pigController.quitGameMenuItem();
		Stage stage  = (Stage)viewStatisticsButton.getScene().getWindow();
	    stage.close();
	}



}
