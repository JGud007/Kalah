package kalah;

class Player {
    private final int playerNumber;
    private int score;
    private boolean isTurn;

    Player(int player) {
        playerNumber = player;
        score = 0;
        isTurn = false;
    }

    int getScore(){
        return score;
    }

    int getPlayerNumber(){ return playerNumber; }

    void increaseScore(int scoreIncrease){
        score += scoreIncrease;
    }

    boolean getIsTurn(){
        return isTurn;
    }

    void setIsTurnTrue(){ isTurn = true; }

    void setIsTurnFalse(){
        isTurn = false;
    }

    void updateScore(int index, int scoreInc){}
}
