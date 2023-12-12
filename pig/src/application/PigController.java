package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PigController implements ControlsListener {

	ArrayList<CompletedGame> games = DataManager.getInstance().getGameResults();
	private Game currentGame;
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private ImageView player1DieFace;
	@FXML
	private ImageView player2DieFace;

	@FXML
	private Text player1DieText;
	@FXML
	private Text player2DieText;
	@FXML
	private Text player1ScoreNameText;
	@FXML
	private Text player2ScoreNameText;

	@FXML
	private Text currentRollText;
	@FXML
	private Text turnTotalText;
	@FXML
	private Text turnIndicatorText;

	@FXML
	private Button rollAgainButton;
	@FXML
	private Button holdButton;

	@FXML
	private Image die1 = new Image("orange1.png");
	
	/**************************************
	 * Methods for button controls
	 * ************************************/
	public void newGameMenuItem() throws IOException {
		this.currentGame = null;
		displayPlayerSelect();
	}

	public void displayPlayerSelect() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerSelectDialogue.fxml"));
		AnchorPane playerSelectDialogue = loader.load();
		PlayerSelectDialogueController playerSelectController = loader.getController();
		playerSelectController.setPigController(this);
		Stage playerSelect = new Stage();
		playerSelect.setScene(new Scene(playerSelectDialogue, 296, 297));
		playerSelect.setTitle("Welcome to PIG");
		playerSelect.initModality(Modality.APPLICATION_MODAL);
		try {
			Stage mainStage = (Stage) player1DieFace.getScene().getWindow();
			playerSelect.initOwner(mainStage);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Didn't work");
		}
		playerSelect.show();
	}
	
	public void openDisplayGameHistory() throws IOException {
		AnchorPane gameHistory = (AnchorPane) FXMLLoader
				.load(getClass().getResource("DisplayGameHistory.fxml"));
		Stage displayGameHistory = new Stage();
		displayGameHistory.setScene(new Scene(gameHistory, 400, 500));
		displayGameHistory.initModality(Modality.APPLICATION_MODAL);
		try {
			Stage playerSelect = (Stage) menuBar.getScene().getWindow();
			displayGameHistory.initOwner(playerSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		displayGameHistory.show();
	}
		
	public void quitGameMenuItem() {
	    Stage stage  = (Stage) menuBar.getScene().getWindow();
	    stage.getOnCloseRequest().handle(null);
	    stage.close();
	}
	
	public void displayWinnerCongratulations() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WinnerCongratulation.fxml"));
		Parent root = loader.load();
		WinnerCongratulationController winnerCongrats = loader.getController();
		winnerCongrats.initialize(this);
		winnerCongrats.setWinnerText(currentGame.getWinner());
		Stage displayWinnerCongrats = new Stage();
		displayWinnerCongrats.setScene(new Scene(root, 250, 65));
		displayWinnerCongrats.setTitle("Congratulations!");
		displayWinnerCongrats.show();
	}

	/***********************************************
	 * Initialization and game setting controls
	 * *********************************************/
	
	public void initialize() throws IOException {
		player1DieFace.setImage(die1);
		player2DieFace.setImage(die1);
		player1DieText.setText("");
		player2DieText.setText("");
		player1ScoreNameText.setText("");
		player2ScoreNameText.setText("");
		turnIndicatorText.setText("");
		currentRollText.setText("");
		turnTotalText.setText("");
	}

	public void setGame(Game game, GameReadyCallback callback) {
		this.currentGame = game;
		currentGame.setControlsListener(this);
		player1DieText.setText(game.player1.getPlayerName());
		player2DieText.setText(game.player2.getPlayerName());
		player1ScoreNameText.setText(game.player1.getPlayerName());
		player2ScoreNameText.setText(game.player2.getPlayerName());
		updateUIAfterTurn();
		callback.onGameReady();
	}
	
	public Game getCurrentGame() {
		return currentGame;
	}

	/****************************
	 * Gameplay methods
	 * **************************/
	
	public void rollAgain() {
		this.onDieRoll();
	}

	public void endTurn() {
		this.onTurnEnd();
	}

	public void playGame() {
		currentGame.playGame();
	}

	public void endGame() throws IOException {
		displayWinnerCongratulations();
		Date date = new Date();
		games.add(new CompletedGame(date, currentGame.getWinner(), currentGame.getWinner().getGameTotal(), true));
		games.add(new CompletedGame(date, currentGame.getLoser(), currentGame.getLoser().getGameTotal(), false));
	}

	@Override
	public void onDieRoll() {
		System.out.println(currentGame.getPlayer1().getPlayerName() + ": " + currentGame.player1.getGameTotal());
		System.out.println(currentGame.getPlayer2().getPlayerName() + ": " + currentGame.player2.getGameTotal());
		if (currentGame.getActivePlayer() instanceof ComputerPlayer) {
		} else {
			System.out.println("from onDieRoll: it's still your turn");
			updateUIAfterTurn();
			currentGame.humanTurn();
			updateUIAfterTurn();
		}
	}

	@Override
	public void onTurnEnd() {
		currentGame.playerEndTurn();
		updateUIAfterTurn();
		triggerComputerTurn();
		currentGame.checkWinner();
    	System.out.println("from onTurnEnd: it's your turn");
    	updateUIAfterTurn();
		if (currentGame.getWinner() != null) {
			try {
				endGame();
			} catch (IOException e) {
				System.out.println("problem with endGame in onTurnEnd");
				e.printStackTrace();
			}
		}
	}
	
	public void triggerComputerTurn() {
	    if (currentGame.getActivePlayer() instanceof ComputerPlayer) {
	    	System.out.println("from triggerComputerTurn: it's the computer's turn");
	    	updateUIAfterTurn();
	        currentGame.computerTurn();
	    }
	}
	
	@Override
	public void updateUIAfterTurn() {
		turnIndicatorText.setText(currentGame.getActivePlayer().getPlayerName() + "'s Turn"); // must be here
		currentRollText.setText(currentGame.getActivePlayer().getCurrentRoll().toString()); // must be here
		turnTotalText.setText(currentGame.getActivePlayer().getTurnTotal().toString()); // must be here
	}

}
