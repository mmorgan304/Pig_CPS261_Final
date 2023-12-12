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
	private Integer playersScore;
	private boolean isWinner;

	public CompletedGame(Date gameDate, Player player, Integer playersScore, boolean isWinner) {
		super();
		this.gameDate = gameDate;
		this.player = player;
		this.playersScore = playersScore;
		this.isWinner = isWinner;
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

	public Integer getPlayersScore() {
		return playersScore;
	}

	public void setPlayersScore(Integer playersScore) {
		this.playersScore = playersScore;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	
}
