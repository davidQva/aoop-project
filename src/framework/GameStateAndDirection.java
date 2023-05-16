package framework;

import java.io.Serializable;

public enum GameStateAndDirection implements Serializable {
    UP, DOWN, LEFT, RIGHT, NONE,
    GAME_OVER, GAME_WON, GAME_START,
    GAME_PAUSE, GAME_UNPAUSE, GAME_RESTART,GAME_LOAD, GAME_SAVE;   
    
}