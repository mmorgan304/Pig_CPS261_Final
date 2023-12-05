package application;

import java.util.ArrayList;

public class DataManager {
	private static final DataManager instance = new DataManager();
    private ArrayList<Game> gameResults;
    private ArrayList<Player> players;

    private DataManager() {
        gameResults = new ArrayList<>();
        players = new ArrayList<>();
    }

    public static DataManager getInstance() {
        return instance;
    }

    public ArrayList<Game> getGameResults() {
        return gameResults;
    }

    public void setGameResults(ArrayList<Game> gameResults) {
        this.gameResults = gameResults;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
