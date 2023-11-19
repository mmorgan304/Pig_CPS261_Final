package application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PigController {
	@FXML
	private ImageView playersDieFace;
	@FXML
	private ImageView computersDieFace;
	
	@FXML
	private Text playerNameDieText;
	@FXML
	private Text computerDieText;
	@FXML
	private Text playerScoreNameText;
	@FXML
	private Text computerScoreNameText;

	@FXML
	private Text currentRollText;
	@FXML
	private Text turnTotalText;
	@FXML
	private Text turnIndicatorText;

	@FXML
	private Image die1 = new Image("orange1.png");

	public void initialize() {
		String userName = "Hello";
		String computerName = "Computer";
		playersDieFace.setImage(die1);
		computersDieFace.setImage(die1);
		playerNameDieText.setText(userName);
		computerDieText.setText(computerName);
		playerScoreNameText.setText(userName);
		computerScoreNameText.setText(computerName);
		
		turnIndicatorText.setText(computerName + "'s Turn");
		currentRollText.setText("4");
		turnTotalText.setText("8");

	}

}
