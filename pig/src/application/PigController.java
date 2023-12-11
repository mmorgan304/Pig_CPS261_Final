package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	public void displayPlayerSelect() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerSelectDialogue.fxml"));
		AnchorPane playerSelectDialogue = loader.load();
		PlayerSelectDialogueController playerSelectController = loader.getController();
		playerSelectController.setPigController(this);

		Stage playerSelect = new Stage();
		playerSelect.setScene(new Scene(playerSelectDialogue, 277, 300));
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
		turnIndicatorText.setText(game.getActivePlayer().getPlayerName() + "'s Turn");
		currentRollText.setText(game.getActivePlayer().getCurrentRoll().toString());
		turnTotalText.setText(game.getActivePlayer().getTurnTotal().toString());
		callback.onGameReady();
	}

	public void rollAgain() {
		// update UI
		this.onDieRoll();
	}

	public void endTurn() {
		this.onTurnEnd();
	}

	public void playGame() {
		currentGame.playGame();
	}

	public void endGame() {
		Date date = new Date();
		CompletedGame completedGame = new CompletedGame(date, currentGame.getWinner(), currentGame.getLoser(),
				currentGame.getWinner().getGameTotal(), currentGame.getLoser().getGameTotal());
		games.add(completedGame);
	}

	@Override
	public void onDieRoll() {
		System.out.println(currentGame.getPlayer1().getPlayerName() + ": " + currentGame.player1.getGameTotal());
		System.out.println(currentGame.getPlayer2().getPlayerName() + ": " + currentGame.player2.getGameTotal());
		Integer result = currentGame.dieRoll();
		currentGame.getActivePlayer().setCurrentRoll(result);
		if (result == 1) {
			currentGame.getActivePlayer().setTurnTotal(0);
			endTurn();
		} else if (result > 1) {
			currentGame.getActivePlayer().setTurnTotal(currentGame.getActivePlayer().getTurnTotal() + result);
		}
		turnIndicatorText.setText(currentGame.getActivePlayer().getPlayerName());
		currentRollText.setText(currentGame.getActivePlayer().getCurrentRoll().toString());
		turnTotalText.setText(currentGame.getActivePlayer().getTurnTotal().toString());
	}

	@Override
	public void onTurnEnd() {
		currentGame.getActivePlayer().setGameTotal(currentGame.getActivePlayer().getGameTotal()+currentGame.getActivePlayer().getTurnTotal());
		currentGame.setPlayerGameScore(currentGame.getActivePlayer().getGameTotal());
		currentGame.switchActivePlayer();
		currentGame.getActivePlayer().setTurnTotal(0);
		currentRollText.setText(currentGame.getActivePlayer().getCurrentRoll().toString());
		turnTotalText.setText(currentGame.getActivePlayer().getTurnTotal().toString());
		turnIndicatorText.setText(currentGame.getActivePlayer().getPlayerName());
		currentGame.checkWinner();
		if (currentGame.getWinner() != null) {
			endGame();
		}
	}

}
