package application;

import java.io.Serializable;
import java.util.Date;

public class CompletedGame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3997838651077916682L;
	private Date gameDate;
	private Player winner;
	private Player loser;
	private Integer winningScore;
	private Integer losingScore;

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

	public Integer getWinningScore() {
		return winningScore;
	}

	public void setWinningScore(Integer winningScore) {
		this.winningScore = winningScore;
	}

	public Integer getLosingScore() {
		return losingScore;
	}

	public void setLosingScore(Integer losingScore) {
		this.losingScore = losingScore;
	}

	public CompletedGame(Date gameDate, Player winner, Player loser, Integer winningScore, Integer losingScore) {
		super();
		this.gameDate = gameDate;
		this.winner = winner;
		this.loser = loser;
		this.winningScore = winningScore;
		this.losingScore = losingScore;
	}
	
}
