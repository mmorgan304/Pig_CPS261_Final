package application;

import java.util.Date;

public class SinglePlayerGame extends Game {

	public SinglePlayerGame(Date gameDate, Player player1) {
		super(gameDate);
		this.gameDate = gameDate;
	    this.player1 = player1;
	    player1.setCurrentRoll(0);
	    player1.setTurnTotal(0);
	    player1.setGameTotal(0);
	    Player computer = new Player("Computer");
	    computer.setCurrentRoll(0);
	    computer.setTurnTotal(0);
	    computer.setGameTotal(0);
	    this.player2 = computer;	
	}
	
	public void playGame() {
		
	}
	
	// superclass methods humanTurn, dieRoll
	public void computerTurn() {
		
	}

}
