package framework;

public enum GameStateAndDirection {
    UP, DOWN, LEFT, RIGHT, NONE,
    GAME_OVER, GAME_WON, GAME_RESTART,
    GAME_UNPAUSE, GAME_PAUSE, GAME_START,
    GAME_MENU, GAME_PLAY;

    public static GameStateAndDirection state = GAME_MENU; 
    
}