package kalah;

abstract class GameManager implements IGameBehaviour {
    private Player[] playerArray;

    GameManager(){
    }

    void setPlayerArray(Player[] playerArray) { this.playerArray = playerArray; }

    int getCurrentPlayersTurn(){
        for (Player aPlayerArray : playerArray) {
            if (aPlayerArray.getIsTurn()) {
                return aPlayerArray.getPlayerNumber();
            }
        }
        return 0;
    }

    void nextPlayersTurn(){
        int currentPlayersTurn = getCurrentPlayersTurn();
        playerArray[currentPlayersTurn - 1].setIsTurnFalse();
        if (currentPlayersTurn == playerArray.length){
            playerArray[0].setIsTurnTrue();
        }else{
            playerArray[currentPlayersTurn].setIsTurnTrue();
        }
    }

    int getPlayersScore(int player){
        return playerArray[player].getScore();
    }

    void increasePlayersScore(int player, int scoreIncrease){
        playerArray[player-1].increaseScore(scoreIncrease);
    }

    Player getPlayer(int player){ return playerArray[player-1]; }


}
