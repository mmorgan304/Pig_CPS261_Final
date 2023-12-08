package application;

import java.util.Date;

public class TwoPlayerGame extends Game {

	public TwoPlayerGame(Date gameDate, Player player1, Player player2) {
		super(gameDate);
		this.gameDate = gameDate;
		this.player1 = player1;
		this.player2 = player2;
		player1.setCurrentRoll(0);
		player1.setTurnTotal(0);
		player1.setGameTotal(0);
		player2.setCurrentRoll(0);
		player2.setTurnTotal(0);
		player2.setGameTotal(0);
		player1.setActive(true);
	    player2.setActive(false);
	}
	
	// uses superclass methods humanTurn, dieRoll
	
	public void playGame() {
		while (player1.getGameTotal() < 100 & player2.getGameTotal() < 100) {
			 player1.setActive(true);
			 player2.setActive(false);
			 while (player1.isActive()) {
				 humanTurn(player1);
			 }
			 while (player2.isActive()) {
				 humanTurn(player2);
			 }
		}

	}
	
	
}
