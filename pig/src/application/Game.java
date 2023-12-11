package application;

import java.util.Date;
import java.util.Random;

public abstract class Game {
	protected ControlsListener controlsListener;
	static Random die = new Random();
	protected Player player1;
	protected Player player2;
	protected Date gameDate;
	protected Player winner;
	protected Player loser;
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

	public Player getLoser() {
		return loser;
	}

	public void setLoser(Player loser) {
		this.loser = loser;
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

	public void setControlsListener(ControlsListener controlsListener) {
		this.controlsListener = controlsListener;
	}

	public Integer dieRoll() {
		return die.nextInt(6) + 1;
	}

	public void endTurn() {
		checkWinner();
		switchActivePlayer();
	}

	public void onEndTurn() {
		endTurn();
	}

	public Player getActivePlayer() {
		if (player1.isActive()) {
			return player1;
		} else
			return player2;
	}

	public void switchActivePlayer() {
		if (player1.isActive()) {
			switchPlayer(player1, player2);
		} else if (player2.isActive()) {
			switchPlayer(player2, player1);
		}
	}

	private void switchPlayer(Player currentPlayer, Player nextPlayer) {
		currentPlayer.setActive(false);
		nextPlayer.setActive(true);
	}

	public void setPlayerGameScore(Integer gameTotal) {
		if (player1.isActive()) {
			player1.setGameTotal(gameTotal);
		} else if (player2.isActive()) {
			player2.setGameTotal(gameTotal);
		}
	}

	public void checkWinner() {
		if (player1.getGameTotal() >= 100) {
			setWinner(player1);
			setLoser(player2);
		} else if (player2.getGameTotal() >= 100) {
			setWinner(player2);
			setLoser(player1);
		}
		if (getWinner() != null) {
			System.out.println(getWinner().getPlayerName() + " wins!");
		}
	}

	public abstract void playGame();
}
