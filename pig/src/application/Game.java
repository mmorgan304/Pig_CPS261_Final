package application;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3997838651077916682L;
	private Player player1;
	private Player player2;
	private Date gameDate;
	private Integer player1Score;
	private Integer player2Score;
	private boolean didPlayer1Win;
	private boolean didPlayer2Win;
	
}
