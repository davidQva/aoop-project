package application;

public class GameTest {

    public static void main(String[] args) throws Exception {

        /**
         * choose the game you want to play
         */

        SokobanGame game = new SokobanGame(13, 13, 50);

       // SnakeGame game = new SnakeGame(10, 10, 30);

        /**
         * add prototype tile to the game
         */
        ImageTile prototype = new ImageTile();
        game.addTile(99, prototype);

        /**
         * add tiles to the game that you want to use
         */
        switch (game.getClass().getSimpleName()) {
            case "SnakeGame":
                game.addTile(1, prototype, "snekkbod");
                game.addTile(2, prototype, "snekkapple");
                game.addTile(0, prototype, "blank");
                game.addTile(3, prototype, "snekkhead");
                break;
            case "SokobanGame":
                game.addTile(0, prototype, "crate");
                game.addTile(1, prototype, "cratemarked");
                game.addTile(2, prototype, "blank");
                game.addTile(3, prototype, "blankmarked");
                game.addTile(4, prototype, "wall");
                game.addTile(5, prototype, "player");
                game.addTile(6, prototype, "player");
                game.repaintBoard();
                break;
            default:
                break;
        }

        /**
         * add observers to the game
         */
      //  GameSound sound = new GameSound();
      //  game.attach(sound);
/* 
        PrintOut printOut = new PrintOut();
        game.attach(printOut);
 */

        /**
         * add controller to the game
         */
        KeyboardController snakeController = new KeyboardController(game.getController());
        game.getController().setController(snakeController);
        game.getFrame().addKeyListener(snakeController);

       /*  SokobanMouseController snakeController2 = new SokobanMouseController(game.getController());
        game.getFrame().addMouseListener(snakeController2);
        snakeController2.setGame(game);
        game.getController().setController(snakeController2); */

    }

}
