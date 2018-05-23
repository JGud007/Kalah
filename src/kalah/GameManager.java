package kalah;

class GameManager {
    private final Player[] playerArray;

    GameManager(Player[] players){ //, Player typeOfPlayers){
        //typeOfPlayers = typeOfPlayers.getClass();
        playerArray = players;
        playerArray[0].setIsTurnTrue();
    }

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

    Player getPlayer(int player){
        return playerArray[player-1];
    }
}
