public class GameModel {
    
    private int score = 0;
    private int lvl = 1;
    private boolean GameOver;

    public GameModel(int initScore, int initLvl) {
        this.score = initScore;
        this.lvl = initLvl;
        this.GameOver = false;
    }
}
