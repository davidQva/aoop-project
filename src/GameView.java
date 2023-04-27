public class GameView {

    private GameModel gameModel;
    
    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void displayGameBoard() {
        System.out.println("Hello!");        
    }

    public static void main(String[] args) {
        GameModel gameModel = new GameModel(0, 1);
        GameView gameUI = new GameView(gameModel);
        gameUI.displayGameBoard();
    }
}
