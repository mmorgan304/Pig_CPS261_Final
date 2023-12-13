package application;

public interface ControlsListener {
	void onTurnEnd();
	void updateRunningScores();
	void updateUIAfterRoll(Integer result);
	void updateUIAfterTurn();
}
