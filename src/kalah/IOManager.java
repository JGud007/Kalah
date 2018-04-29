package kalah;

import com.qualitascorpus.testsupport.IO;

class IOManager {
    private IO io;
    IOManager(IO ioIn){
        io = ioIn;
    }

    void printBoard(Pits pits, Player p1, Player p2){
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",pits.getValue(12),pits.getValue(11),pits.getValue(10),pits.getValue(9),pits.getValue(8),pits.getValue(7),p1.getScore()));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",p2.getScore(),pits.getValue(0),pits.getValue(1),pits.getValue(2),pits.getValue(3),pits.getValue(4),pits.getValue(5)));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    int receiveInput(int playersTurn) {
        String prompt = String.format("Player P%d's turn - Specify house number or 'q' to quit: ", playersTurn);
        int suppliedInput = io.readInteger(prompt, 1, 6, -1, "q");
        if (suppliedInput != -1) {
            if (playersTurn == 1) {
                return suppliedInput - 1;
            } else {
                return suppliedInput + 6;
            }
        }
        return -1;
    }

    void emptyHouse(){
        io.println("House is empty. Move again.");
    }

    void gameOver(){
        io.println("Game over");
    }

    void printScore(int player, int score){
        io.println(String.format("\tplayer %d:%d", player, score));
    }

    void printWinner(int score1, int score2){
        if(score1 >score2){
            io.println("Player 1 wins!");
        }else if(score1 < score2){
            io.println("Player 2 wins!");
        }else{
            io.println("A tie!");
        }
    }
}

