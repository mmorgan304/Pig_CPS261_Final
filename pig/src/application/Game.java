package application;

import java.util.Date;
import java.util.Random;

public abstract class Game {
	static Random die = new Random();
	protected Player player1;
	protected Player player2;
	private boolean isPlayer1Active;
	private boolean isPlayer2Active;
	protected Date gameDate;
	protected Player winner;
	private Integer player1Score;
	private Integer player2Score;
	
	public Game(Date gameDate) {
		super();
		this.gameDate = gameDate;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean isPlayer1Active() {
		return isPlayer1Active;
	}

	public void setPlayer1Active(boolean isPlayer1Active) {
		this.isPlayer1Active = isPlayer1Active;
	}

	public boolean isPlayer2Active() {
		return isPlayer2Active;
	}

	public void setPlayer2Active(boolean isPlayer2Active) {
		this.isPlayer2Active = isPlayer2Active;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Integer getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	public Integer getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

	public static int dieRoll() {
		return die.nextInt(6)+1;
	}
	
	public void humanTurn(Player activePlayer) {
		while (activePlayer.isActive()) {
			// wait for button press?
			int currentRoll = dieRoll();
			if (currentRoll == 1) {
				activePlayer.setTurnTotal(0);
				activePlayer.setGameTotal(activePlayer.getGameTotal()+activePlayer.getTurnTotal());
				activePlayer.setActive(false);
			} else if (currentRoll > 1) {
				activePlayer.setTurnTotal(activePlayer.getTurnTotal()+currentRoll);
			}
		}
	}
	
	public Player getActivePlayer() {
		if (player1.isActive()) {
			return player1;
		} else return player2;
	}
	
	public abstract void playGame();
}
