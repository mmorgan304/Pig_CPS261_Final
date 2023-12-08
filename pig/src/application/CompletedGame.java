package application;

import java.io.Serializable;
import java.util.Date;

public class CompletedGame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3997838651077916682L;
	private Date gameDate;
	private Player player;
	private int winningScore;
	private boolean isPlayerWinner;

	public CompletedGame(Date gameDate, Player player, int winningScore, boolean isPlayerWinner) {
		super();
		this.gameDate = gameDate;
		this.player = player;
		this.winningScore = winningScore;
		this.isPlayerWinner = isPlayerWinner;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getWinningScore() {
		return winningScore;
	}

	public void setWinningScore(int winningScore) {
		this.winningScore = winningScore;
	}

	public boolean isPlayerWinner() {
		return isPlayerWinner;
	}

	public void setPlayerWinner(boolean isPlayerWinner) {
		this.isPlayerWinner = isPlayerWinner;
	}

}
