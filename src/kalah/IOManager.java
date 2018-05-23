package kalah;

import com.qualitascorpus.testsupport.IO;

class IOManager {
    private final IO io;
    IOManager(IO ioIn){
        io = ioIn;
    }

    void gameOver(){
        io.println("Game over");
    }

    void printScore(int player, int score){
        io.println(String.format("\tplayer %d:%d", player, score));
    }

    void printWinner(int player){
        io.println(String.format("Player %d wins!", player));
    }

    void printTie(){io.println("A tie!");}

}

