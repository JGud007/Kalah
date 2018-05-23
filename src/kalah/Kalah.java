package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		//create players for the game
		Player[] players = new Player[2];
		players[0] = new KalahPlayer(1);
		players[1] = new KalahPlayer(2);
		//Pass io and players to kalahManager
		KalahManager kalahManager = new KalahManager(io, players);
		boolean gameInProgress = true;
		while(gameInProgress) {
			gameInProgress = kalahManager.playTurn();
		}
	}
}
