package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PigController implements ControlsListener {

	ArrayList<CompletedGame> games = DataManager.getInstance().getGameResults();
	private Game currentGame;
	ArrayList<Integer> player1GameTotals = new ArrayList<Integer>();
	ArrayList<Integer> player2GameTotals = new ArrayList<Integer>();
	@FXML
	Image[] diceFaces = new Image[] { new Image("orange1.png"), new Image("orange2.png"), new Image("orange3.png"),
			new Image("orange4.png"), new Image("orange5.png"), new Image("orange6.png") };

	@FXML
	private MenuBar menuBar;

	@FXML
	private Text turnIndicatorText;
	@FXML
	private ImageView player1DieFace;
	@FXML
	private GridPane gameScoreRunningTotals;
	@FXML
	private ColumnConstraints player1GameScoreColumn;
	@FXML
	private ColumnConstraints player2GameScoreColumn;
	@FXML
	private Text player1ScoreNameText;
	@FXML
	private Text player2ScoreNameText;

	@FXML
	private Text currentRollText;
	@FXML
	private Text turnTotalText;

	@FXML
	private Button rollAgainButton;
	@FXML
	private Button holdButton;

	/**************************************
	 * Methods for button controls
	 ************************************/
	public void newGameMenuItem() throws IOException {
		player1GameTotals.clear();
		player2GameTotals.clear();
		displayPlayerSelect();
		updateRunningScores();
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
		AnchorPane gameHistory = (AnchorPane) FXMLLoader.load(getClass().getResource("DisplayGameHistory.fxml"));
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

	public void openRules() throws IOException {
		StackPane rules = (StackPane) FXMLLoader.load(getClass().getResource("Rules.fxml"));
		Stage displayRules = new Stage();
		displayRules.setScene(new Scene(rules, 400, 250));
		displayRules.initModality(Modality.APPLICATION_MODAL);
		try {
			Stage playerSelect = (Stage) menuBar.getScene().getWindow();
			displayRules.initOwner(playerSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		displayRules.show();
	}

	public void openAbout() throws IOException {
		StackPane about = (StackPane) FXMLLoader.load(getClass().getResource("About.fxml"));
		Stage displayAbout = new Stage();
		displayAbout.setScene(new Scene(about, 400, 250));
		displayAbout.initModality(Modality.APPLICATION_MODAL);
		try {
			Stage playerSelect = (Stage) menuBar.getScene().getWindow();
			displayAbout.initOwner(playerSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		displayAbout.show();
	}

	public void quitGameMenuItem() {
		Stage stage = (Stage) menuBar.getScene().getWindow();
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
	 *********************************************/

	public void initialize() throws IOException {
		player1DieFace.setImage(diceFaces[0]);
		player1ScoreNameText.setText("");
		player2ScoreNameText.setText("");
		turnIndicatorText.setText("");
		currentRollText.setText("");
		turnTotalText.setText("");
	}

	public void setGame(Game game, GameReadyCallback callback) {
		this.currentGame = game;
		currentGame.setControlsListener(this);
		player1ScoreNameText.setText(game.player1.getPlayerName());
		player2ScoreNameText.setText(game.player2.getPlayerName());
		callback.onGameReady();
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	/****************************
	 * Gameplay methods
	 **************************/

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
		currentGame.player1.setActive(false);
		currentGame.player2.setActive(false);
	}

	public void onDieRoll() {
		setDieFace(1);
		currentGame.humanTurn();
	}

	@Override
	public void onTurnEnd() {
		currentGame.playerEndTurn();
		currentGame.checkWinner();
		if (currentGame.getWinner() != null) {
			try {
				endGame();
			} catch (IOException e) {
				System.out.println("problem with endGame in onTurnEnd");
				e.printStackTrace();
			}
		} else {
			try {
				triggerComputerTurn();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void triggerComputerTurn() throws InterruptedException {
		if (currentGame != null && currentGame.getActivePlayer() instanceof ComputerPlayer) {
			currentGame.computerTurn();
		}
	}

	/****************
	 * UI methods
	 **************/

	public void setDieFace(Integer result) {
		RotateTransition rotate = new RotateTransition(Duration.millis(1000), player1DieFace);
		rotate.setByAngle(720);
		rotate.setOnFinished(e -> {
			player1DieFace.setImage(diceFaces[result - 1]);
			updateRestofUIAfterRoll();
		});
		rotate.play();
	}

	public void addToRunningTotals() {
		if (currentGame.player1.isActive()) {
			player1GameTotals.add(currentGame.getActivePlayer().getGameTotal());
		} else if (currentGame.player2.isActive()) {
			player2GameTotals.add(currentGame.getActivePlayer().getGameTotal());
		}
	}

	@Override
	public void updateRunningScores() {
		gameScoreRunningTotals.getChildren().clear();
		addToRunningTotals();
		updateColumn(player1GameTotals, "player1GameScoreColumn", 0);
		updateColumn(player2GameTotals, "player2GameScoreColumn", 1);
	}

	private void updateColumn(ArrayList<Integer> scores, String columnFxId, int columnIndex) {
		int rowIndex = 0;
		for (Integer score : scores) {
			Label scoreLabel = new Label(score.toString());
			gameScoreRunningTotals.add(scoreLabel, columnIndex, rowIndex);
			GridPane.setHalignment(scoreLabel, HPos.CENTER);
			rowIndex++;
		}
	}

	@Override
	public void updateUIAfterRoll(Integer result) {
		setDieFace(result);
	}

	public void updateRestofUIAfterRoll() {
		currentRollText.setText(currentGame.getActivePlayer().getCurrentRoll().toString());
		turnTotalText.setText(currentGame.getActivePlayer().getTurnTotal().toString());
	}

	@Override
	public void updateUIAfterTurn() {
		turnIndicatorText.setText(currentGame.getActivePlayer().getPlayerName() + "'s Turn");
		currentRollText.setText(currentGame.getActivePlayer().getCurrentRoll().toString());
		turnTotalText.setText(currentGame.getActivePlayer().getTurnTotal().toString());
	}

}
