package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PigController {

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
		player1DieText.setText("dfgg");
		player2DieText.setText("fgaf");
		player1ScoreNameText.setText("fagfa");
		player2ScoreNameText.setText("fgagadf");
		turnIndicatorText.setText("'s Turn");
		currentRollText.setText("ragg");
		turnTotalText.setText("fvda");
	}

	public void setGame(Game game) {
		Player player1 = game.getPlayer1();
		Player player2 = game.getPlayer2();
		player1DieText.setText(player1.getPlayerName());
		player2DieText.setText(player2.getPlayerName());
		player1ScoreNameText.setText(player1.getPlayerName());
		player2ScoreNameText.setText(player2.getPlayerName());
		turnIndicatorText.setText(game.getActivePlayer().getPlayerName() + "'s Turn");
		currentRollText.setText(game.getActivePlayer().getCurrentRoll().toString());
		turnTotalText.setText(game.getActivePlayer().getTurnTotal().toString());
	}
}
