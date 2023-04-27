public class GameUI {

    private GameModel gameModel;
    
    public GameUI(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void displayGameBoard() {
        System.out.println("Game board:");        
    }

    public static void main(String[] args) {
        GameModel gameModel = new GameModel(0, 1);
        GameUI gameUI = new GameUI(gameModel);
        gameUI.displayGameBoard();
    }

}
