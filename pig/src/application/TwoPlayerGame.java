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
	}

}
