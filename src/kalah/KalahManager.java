package kalah;

import com.qualitascorpus.testsupport.IO;

class KalahManager extends GameManager{
    private final Pits pits;
    private final KalahIOManager ioManager;
    private Player[] kalahPlayers;

    KalahManager(IO io) {
        kalahPlayers = new Player[2];
        kalahPlayers[0] = new KalahPlayer(1);
        kalahPlayers[0].setIsTurnTrue();
        kalahPlayers[1] = new KalahPlayer(2);
        super.setPlayerArray(kalahPlayers);
        pits = new Pits();
        ioManager = new KalahIOManager(io);
    }

    public boolean playTurn(){
        ioManager.printKalahBoard(pits,getPlayersScore(0),getPlayersScore(1));
        int index = ioManager.receiveInputKalah(getCurrentPlayersTurn());
        if (index != -1){
            if (pits.getValue(index) != 0) {
                boolean playerTurnFinished = moveSeeds(index);
                if (playerTurnFinished){super.nextPlayersTurn();}
            }else{
                ioManager.emptyHouse();
            }
            if(checkGameFinished()){
                finishGame();
                return false;
            }
        }else {
            ioManager.gameOver();
            ioManager.printKalahBoard(pits,getPlayersScore(0),getPlayersScore(1));
            return false;
        }
        return true;
    }

    private void finishGame(){
        ioManager.printKalahBoard(pits,getPlayersScore(0),getPlayersScore(1));
        ioManager.gameOver();
        ioManager.printKalahBoard(pits,getPlayersScore(0),getPlayersScore(1));
        increasePlayersScore(1, pits.remainingSeeds(1));
        increasePlayersScore(2, pits.remainingSeeds(2));
        ioManager.printScore(1,getPlayersScore(0));
        ioManager.printScore(2,getPlayersScore(1));
        ioManager.printWinner(getPlayersScore(0),getPlayersScore(1));
    }

    //returns true if the players turn is finished otherwise false
    private boolean moveSeeds(int index){
        int finalIndex;
        int numberOfSeeds = pits.emptyPit(index);
        if(getCurrentPlayersTurn() == 1){
            finalIndex = pits.moveSeeds(index, numberOfSeeds, 1);
            getPlayer(1).updateScore(finalIndex, numberOfSeeds);
            if(pits.endsInOwnStore(finalIndex, 1)){
                return false;
            }
        }else {
            finalIndex = pits.moveSeeds(index, numberOfSeeds, 2);
            getPlayer(2).updateScore(finalIndex, numberOfSeeds);
            if(pits.endsInOwnStore(finalIndex, 2)){
                return false;
            }
        }
        checkAndReactToCapture(finalIndex);
        return true;
    }

    private void checkAndReactToCapture(int index) {
        int finalSeedIndex = index - 1;
        //when accessed with an index returns the index of the opposite pit
        int[] matchingIndex = {12, 11, 10, 9, 8, 7, 0, 5, 4, 3, 2, 1, 0};
        if (getCurrentPlayersTurn() == 1) {
            if (finalSeedIndex < 6) {
                if (pits.getValue(finalSeedIndex) == 1) {
                    int seedsStolen = pits.getValue(matchingIndex[finalSeedIndex]);
                    if (seedsStolen > 0) {
                        pits.emptyPit(finalSeedIndex);
                        pits.emptyPit(matchingIndex[finalSeedIndex]);
                        increasePlayersScore(1,seedsStolen + 1);
                    }
                }
            }
        } else {
            if (finalSeedIndex > 6) {
                if (pits.getValue(finalSeedIndex) == 1) {
                    int seedsStolen = pits.getValue(matchingIndex[finalSeedIndex]);
                    if (seedsStolen > 0) {
                        pits.emptyPit(finalSeedIndex);
                        pits.emptyPit(matchingIndex[finalSeedIndex]);
                        increasePlayersScore(2,seedsStolen + 1);
                    }
                }
            }
        }
    }

    private boolean checkGameFinished(){
        return ((getCurrentPlayersTurn() == 1) && pits.playerPitsEmpty(1)) | ((getCurrentPlayersTurn() == 2) && pits.playerPitsEmpty(2));
    }
}
