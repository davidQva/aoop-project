package framework;

/**
 * GameStateAndDirection is an enum that contains the different states of the
 * game. The states are used to notify the observer of changes in the game.
 */
public enum GameStateAndDirection {
    UP, DOWN, LEFT, RIGHT, NONE,
    GAME_OVER, GAME_WON, GAME_START,
    GAME_PAUSE, GAME_UNPAUSE, GAME_RESTART, GAME_LOAD, GAME_SAVE,MUTE;

}