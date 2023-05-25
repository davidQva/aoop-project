package application;

public class StartGames {

    public static void main(String[] args) throws Exception {

        /**        
         * set tile size to whatever you want bewteen 1 and something big
         */
        SokobanGame game = new SokobanGame(13, 13, 50);
        SnakeGame game2 = new SnakeGame(18, 20, 20);

        /**
         * add prototype tile to the game
         */
        ImageTile prototype = new ImageTile();
        game.addTile(99, prototype);

        /**
         * add tiles to the game and set the tile to match the board value
         */
        game2.addTile(1, prototype, "snekkbod");
        game2.addTile(2, prototype, "snekkapple");
        game2.addTile(0, prototype, "blank");
        game2.addTile(3, prototype, "snekkhead");

        game.addTile(0, prototype, "crate");
        game.addTile(1, prototype, "cratemarked");
        game.addTile(2, prototype, "blank");
        game.addTile(3, prototype, "blankmarked");
        game.addTile(4, prototype, "wall");
        game.addTile(5, prototype, "player");
        game.addTile(6, prototype, "player");
        game.repaintBoard();

        /**
         * add observers to the game
         */
        // GameSound sound = new GameSound();
        // game.attach(sound);

        // PrintOut printOut = new PrintOut();
        // game.attach(printOut);

        // GameMonitor monitor = new GameMonitor();

        /**
         * add controller to the game
         */
        KeyboardController sokobanKey = new KeyboardController(game.getController());
        KeyboardController snakeKey = new KeyboardController(game2.getController());

        game.setController(sokobanKey);
        game2.setController(snakeKey);

        /*
         * MouseController mouseC = new MouseController(game.getController());
         * game.getFrame().addMouseListener(mouseC);
         * mouseC.setGame(game);
         * game.getController().setController(mouseC);
         */

    }

}
