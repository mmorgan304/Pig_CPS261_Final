package application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AboutController {
	@FXML
	Text about;
	
	@FXML
	private void initialize() {
        String aboutText = "Melissa Morgan\n"
        		+ "CPS282-01\n"
        		+ "Fall 2023";
        about.setText(aboutText);
    }
}
