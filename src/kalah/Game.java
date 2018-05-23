package kalah;

public class Game {
    private IGameBehaviour gameBehaviour;

    public Game(IGameBehaviour gameBehaviour) {
        this.gameBehaviour = gameBehaviour;
    }

    void setGameBehaviour(IGameBehaviour newGameBehaviour){gameBehaviour = newGameBehaviour;}

    boolean playTurn(){return gameBehaviour.playTurn();}
}
