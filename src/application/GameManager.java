package application;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameManager {
    private int level;
    private boolean isGameOver;
    // Other game-related variables

    public GameManager() {
        level = 1;
        isGameOver = false;
        // Other initialization logic
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void checkGameState() {
        // Implement the logic to check the game state
        // For example, check if the player has completed all levels or if the game is over
        if (level == 10) {
            System.out.println("Congratulations! You've completed all levels!");
            // Set the game over flag
            isGameOver = true;
        }
    }

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

