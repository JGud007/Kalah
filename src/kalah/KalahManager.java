package kalah;

import com.qualitascorpus.testsupport.IO;

class KalahManager {
    private Player p1;
    private Player p2;
    private Pits pits;
    private IOManager ioManager;

    KalahManager(IO io) {
        p1 = new Player(1);
        p1.setIsTurnTrue();
        p2 = new Player(2);
        pits = new Pits();
        ioManager = new IOManager(io);
    }

    private int getCurrentPlayersTurn(){
        if (p1.getIsTurn()){
            return 1;
        }
        return 2;
    }

    boolean playTurn(){
        ioManager.printBoard(pits,p1,p2);
        int index = ioManager.receiveInput(getCurrentPlayersTurn());
        if (index != -1){
            if (pits.getValue(index) != 0) {
                boolean playerTurnFinished = moveSeeds(index);
                if (playerTurnFinished){alternatePlayersTurns();}
            }else{
                ioManager.emptyHouse();
            }
            if(checkGameFinished()){
                finishGame();
                return false;
            }
        }else {
            ioManager.gameOver();
            ioManager.printBoard(pits,p1,p2);
            return false;
        }
        return true;
    }

    private void finishGame(){
        ioManager.printBoard(pits,p1,p2);
        ioManager.gameOver();
        ioManager.printBoard(pits,p1,p2);
        pits.addRemainingSeeds(p1,p2);
        ioManager.printScore(1,p1.getScore());
        ioManager.printScore(2,p2.getScore());
        ioManager.printWinner(p1.getScore(),p2.getScore());
    }

    private void alternatePlayersTurns(){
        if(getCurrentPlayersTurn() == 1){
            p1.setIsTurnFalse();
            p2.setIsTurnTrue();
        }else{
            p1.setIsTurnTrue();
            p2.setIsTurnFalse();
        }
    }

    //returns true if the players turn is finished otherwise false
    private boolean moveSeeds(int index){
        int finalIndex;
        int numberOfSeeds = pits.emptyPit(index);
        if(p1.getIsTurn()){
            finalIndex = pits.moveSeeds(index, numberOfSeeds, 1);
            p1.updateScore(finalIndex, numberOfSeeds);
            if(pits.endsInOwnStore(finalIndex, 1)){
                return false;
            }
        }else {
            finalIndex = pits.moveSeeds(index, numberOfSeeds, 2);
            p2.updateScore(finalIndex, numberOfSeeds);
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
        if (p1.getIsTurn()) {
            if (finalSeedIndex < 6) {
                if (pits.getValue(finalSeedIndex) == 1) {
                    int seedsStolen = pits.getValue(matchingIndex[finalSeedIndex]);
                    if (seedsStolen > 0) {
                        pits.emptyPit(finalSeedIndex);
                        pits.emptyPit(matchingIndex[finalSeedIndex]);
                        p1.increaseScore(seedsStolen + 1);
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
                        p2.increaseScore(seedsStolen + 1);
                    }
                }
            }
        }
    }

    private boolean checkGameFinished(){
        return (p1.getIsTurn() && pits.playerPitsEmpty(1)) | (p2.getIsTurn() && pits.playerPitsEmpty(2));
    }
}
