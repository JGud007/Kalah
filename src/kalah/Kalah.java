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
		KalahManager kalahManager = new KalahManager(io);
		// Replace what's below with your implementation
		boolean gameInProgress = true;
		while(gameInProgress) {
			gameInProgress = kalahManager.playTurn();
		}
	}
}
