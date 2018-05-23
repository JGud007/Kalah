package kalah;

class KalahPlayer extends Player {
    KalahPlayer(int player) {
        super(player);
    }

    void updateScore(int finalIndex, int numSeeds){
        if(super.getPlayerNumber() == 1){
            if(numSeeds >= 19){increaseScore(2);}
            else if(finalIndex >= 7 || numSeeds > 5){increaseScore(1);}
        }else{
            if(numSeeds >= 19){increaseScore(2);}
            else if(finalIndex < 7 || numSeeds > 5){increaseScore(1);}
        }
    }
}
