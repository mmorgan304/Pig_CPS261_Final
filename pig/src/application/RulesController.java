package application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RulesController {
	
	@FXML
	Text rules;
	
	@FXML
	private void initialize() {
        String gameRules = "THE RULES OF PIG:\n\n"
        		+ "The game of Pig is a simple two-player dice game in which the first player to reach 100 or more"
        		+ "points wins.\n\n"
        		+ "How to play:\n"
        		+ "Players take turns rolling one six-sided dice and following these rules:\n"
        		+ "1. If the player rolls 2 through 6, then he/she can either"
        		+ "a. “Roll Again” or\n"
        		+ "b. “Hold” At this point, the sum of all rolls is added to the player’s score, and it"
        		+ "becomes the other player’s turn.\n"
        		+ "2. If the player rolls 1 before he/she decides to stop rolling, the player scores 0 for that"
        		+ "round and it's the other player's turn.";
        rules.setText(gameRules);
    }
	
}
