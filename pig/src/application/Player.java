package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2196316305511165817L;
	private String playerName;
	private boolean isActive;
	private boolean isWinner;
	private Integer currentRoll;
	private Integer turnTotal;
	private Integer gameTotal;

	public Player(String playerName) {
		super();
		this.playerName = playerName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public Integer getCurrentRoll() {
		return currentRoll;
	}

	public void setCurrentRoll(int currentRoll) {
		this.currentRoll = currentRoll;
	}

	public Integer getTurnTotal() {
		return turnTotal;
	}

	public void setTurnTotal(int turnTotal) {
		this.turnTotal = turnTotal;
	}

	public Integer getGameTotal() {
		return gameTotal;
	}

	public void setGameTotal(int gameTotal) {
		this.gameTotal = gameTotal;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public static Player getPlayerByName(String playerName, ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        System.out.println("null player");
        return null;  // Player not found
    }
}
