package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Loading the game and player data from file
	        ArrayList<Game> loadedGameResults = loadGameResultsFromFile();
	        ArrayList<Player> loadedPlayers = loadPlayersFromFile();
	        DataManager.getInstance().setGameResults(loadedGameResults);
	        DataManager.getInstance().setPlayers(loadedPlayers);

	        // Loading the main Pig frame
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PigFXML.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 640, 480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("PIG");
			primaryStage.show();
			PigController pigController = loader.getController();
			pigController.displayPlayerSelect();

			// Closing the program
			primaryStage.setOnCloseRequest(event -> {
				writeGameResultsToFile();
				writePlayersToFile();
				System.out.println("closing the thing");
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Player> loadPlayersFromFile() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		try (ObjectInputStream readPlayerFile = new ObjectInputStream(new FileInputStream("PlayerData"))){
			while (true) {
				try {
					Player player = (Player) readPlayerFile.readObject();
					playerList.add(player);
				} catch (EOFException | ClassNotFoundException e) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("can't read file PlayerData");
			e.printStackTrace();
		}
		return playerList;
	}

	public ArrayList<Game> loadGameResultsFromFile() {
		ArrayList<Game> gameResults = new ArrayList<Game>();
		try (ObjectInputStream readGameFile = new ObjectInputStream(new FileInputStream("GameData"))){
			while (true) {
				try {
					Game game = (Game) readGameFile.readObject();
					gameResults.add(game);
				} catch (EOFException | ClassNotFoundException e) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("can't read file GameData");
			e.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		return gameResults;
	}

	public void writePlayersToFile() {
		try (ObjectOutputStream writePlayerFile = new ObjectOutputStream(new FileOutputStream("PlayerData"))){
			ArrayList<Player> playerList = DataManager.getInstance().getPlayers();
			playerList.forEach(player -> {
				try {
					writePlayerFile.writeObject(player);
				} catch (IOException e) {
					System.out.println("can't write player");
					e.printStackTrace();
				}
			});
		} catch (FileNotFoundException e) {
			System.out.println("can't write to file PlayerData");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeGameResultsToFile() {
		try (ObjectOutputStream writeGameFile = new ObjectOutputStream(new FileOutputStream("GameData"))){
			ArrayList<Game> gameResultsList = DataManager.getInstance().getGameResults();
			gameResultsList.forEach(game -> {
				try {
					writeGameFile.writeObject(game);
				} catch (IOException e) {
					System.out.println("can't write game");
					e.printStackTrace();
				}
			});
		} catch (FileNotFoundException e) {
			System.out.println("can't write to file GameData");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
