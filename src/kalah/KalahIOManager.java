package kalah;

import com.qualitascorpus.testsupport.IO;

class KalahIOManager extends IOManager{
    private final IO io;

    KalahIOManager(IO ioIn) {
        super(ioIn);
        io = ioIn;
    }

    int readInteger1to6 (String prompt){return io.readInteger(prompt, 1, 6, -1, "q");}

    int receiveInputKalah(int playersTurn) {
        String prompt = String.format("Player P%d's turn - Specify house number or 'q' to quit: ", playersTurn);
        int suppliedInput = readInteger1to6(prompt);
        if (suppliedInput != -1) {
            if (playersTurn == 1) {
                return suppliedInput - 1;
            } else {
                return suppliedInput + 6;
            }
        }
        return -1;
    }

    void printKalahBoard(Pits pits, int p1Score, int p2Score){
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",pits.getValue(12),pits.getValue(11),pits.getValue(10),pits.getValue(9),pits.getValue(8),pits.getValue(7),p1Score));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",p2Score,pits.getValue(0),pits.getValue(1),pits.getValue(2),pits.getValue(3),pits.getValue(4),pits.getValue(5)));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    void emptyHouse(){
        io.println("House is empty. Move again.");
    }

    void printWinner(int score1, int score2){
        if(score1 >score2){
            printWinner(1);
        }else if(score1 < score2){
            printWinner(2);
        }else{
            printTie();
        }
    }
}
