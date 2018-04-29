package kalah;

class Player {
    private int playerNumber;
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

    void updateScore(int finalIndex, int numSeeds){
        if(playerNumber == 1){
            if(numSeeds >= 19){increaseScore(2);}
            else if(finalIndex >= 7 || numSeeds > 5){increaseScore(1);}
        }else{
            if(numSeeds >= 19){increaseScore(2);}
            else if(finalIndex < 7 || numSeeds > 5){increaseScore(1);}
        }
    }
}
