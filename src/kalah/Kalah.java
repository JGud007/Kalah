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
		GameManager kalahManager = new KalahManager(io);
		Game game = new Game(kalahManager);
		boolean gameInProgress = true;
		while(gameInProgress) {
			gameInProgress = game.playTurn();
		}
	}
}
