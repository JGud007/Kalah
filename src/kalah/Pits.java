package kalah;

import java.util.Arrays;

class Pits {
    private int[] pits;

    Pits() {
        pits = new int[13];
        Arrays.fill(pits, 4);
    }

    //empties a pit and returns the number of seeds that was stored in the pit
    int emptyPit(int index) {
        int temp = pits[index];
        pits[index] = 0;
        return temp;
    }

    int getValue(int index) {
        return pits[index];
    }

    private void incrementPit(int index) {
        pits[index] += 1;
    }

    int moveSeeds(int startIndex, int numberOfSeeds, int player) {

        int currentPit = startIndex + 1;
        for (int i = 0; i < numberOfSeeds; i++) {
            if (currentPit < 6) {
                incrementPit(currentPit);
                currentPit++;
            } else if (currentPit == 6) {
                if (player == 1) {
                    currentPit++;
                } else {
                    incrementPit(currentPit + 1);
                    currentPit += 2;
                }
            } else if (currentPit < 13) {
                incrementPit(currentPit);
                currentPit++;
            } else {
                if (player == 2) {
                    currentPit = 0;
                } else {
                    incrementPit(0);
                    currentPit = 1;
                }
            }
        }
        return currentPit;
    }

    boolean playerPitsEmpty(int player) {
        int totalSeedsInPlayerPits = 0;

        for (int i = 0; i < 6; i++) {
            if (player == 1) {
                totalSeedsInPlayerPits += getValue(i);
            } else {
                totalSeedsInPlayerPits += getValue(i + 7);
            }
        }

        return totalSeedsInPlayerPits == 0;
    }

    void addRemainingSeeds(Player p1, Player p2){
        int p1SeedsRemaining = 0;
        int p2SeedsRemaining = 0;

        for(int i = 0;i<6;i++){
            p1SeedsRemaining += getValue(i);
            p2SeedsRemaining += getValue(i+7);
        }

        p1.increaseScore(p1SeedsRemaining);
        p2.increaseScore(p2SeedsRemaining);
    }

    boolean endsInOwnStore(int index, int player){
        if(player == 1){
            return index == 7;
        }else if (player == 2){
            return index == 0;
        }
        return false;
    }
}