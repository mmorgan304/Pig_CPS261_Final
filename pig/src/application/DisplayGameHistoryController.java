package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisplayGameHistoryController {
	ArrayList<CompletedGame> games = DataManager.getInstance().getGameResults();

	@FXML
	TableView<CompletedGame> viewIndividualGames = new TableView<>();
	@FXML
	ListView<String> viewPlayerRecords = new ListView<String>();

	public void initialize() {
		ObservableList<CompletedGame> gameList = FXCollections.observableArrayList(games);
		viewIndividualGames.setItems(gameList);
		TableColumn<CompletedGame, String> playerNameColumn = new TableColumn<>("Player Name");
		playerNameColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPlayer().getPlayerName()));
		TableColumn<CompletedGame, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				new SimpleDateFormat("yyyy-MM-dd hh:mm").format(cellData.getValue().getGameDate())));
		TableColumn<CompletedGame, String> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getPlayersScore())));
		TableColumn<CompletedGame, String> winLoseColumn = new TableColumn<>("Result");
		winLoseColumn.setCellValueFactory(cellData -> {
			boolean isWin = cellData.getValue().isWinner();
			return new SimpleStringProperty(isWin ? "Win" : "Lose");
		});
		viewIndividualGames.getColumns().setAll(playerNameColumn, dateColumn, scoreColumn, winLoseColumn);

		ObservableList<String> playerRecords = games.stream()
				.collect(Collectors.groupingBy(game -> game.getPlayer().getPlayerName(),
						Collectors.summarizingInt(game -> game.isWinner() ? 1 : 0)))
				.entrySet().stream().map(entry -> entry.getKey() + " - Wins: " + entry.getValue().getSum())
				.collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList));

		viewPlayerRecords.setItems(playerRecords);

	}
}
