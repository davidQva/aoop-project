package application;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import framework.GameObserver;
import framework.GameStateAndDirection;

import java.io.IOException;
import java.net.URL;

/**
 * GameSound is an observer for the game. It plays sound effects when the game
 * is updated. The class uses the observer pattern to get updates from the game.
 * This GameSound class is only for the Sokoban sound effects.
 */
public class GameSound implements GameObserver {

    int[][] prevBoard;

    public static int MOVE = 0;
    public static int BOX_ON_TARGET = 1;

    private float volume = 0.3f;
    private Clip[] effects;
    private boolean effectMute = false;

    /**
     * Constructor for the GameSound class. Loads the sound effects and sets the
     * volume.
     */
    public GameSound() {
        loadEffects();
        updateEffectVolume();
    }

    /**
     * Plays the sound effect.
     * 
     * @param effect is the sound effect to be played.
     */
    public void playEffect(int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    /**
     * Set the array of sound effects.
     * effects is the array of sound effects provided.
     */
    private void loadEffects() {
        String[] effects = { "move2", "pling" };
        this.effects = new Clip[effects.length];
        for (int i = 0; i < effects.length; i++) {
            this.effects[i] = getClip(effects[i]);
        }
    }

    /**
     * Set the volume of the sound effects.
     */
    private void updateEffectVolume() {

        for (Clip clip : effects) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }

    /**
     * Get the sound effect from the audio file.
     * 
     * @param effect is the sound effect to be played.
     * @return clip is the sound effect that is fetched from the audio folder.
     */
    private Clip getClip(String effect) {

        URL url = ClassLoader.getSystemResource("audio/" + effect + ".wav");
        AudioInputStream audio;
        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * mutes the sound effects.
     * Toggle the mute state of the sound effects.
     */
    public void Mute() {

        this.effectMute = !effectMute;

        for (Clip clip : effects) {
            BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            muteControl.setValue(effectMute);
        }

        if (!effectMute) {
            playEffect(MOVE);
        }
    }

    /**
     * notify triggers the sound effects when the game is updated.
     * 
     * @param board is the game board and the state of the game.
     */
    @Override
    public void notify(int[][] board, GameStateAndDirection update) {

        /**
         * prevBoard is used to check if a box is moved onto a target. If so, the sound
         * effect is played.
         */
        if (prevBoard == null)
            prevBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 1 && prevBoard[i][j] == 3) {
                    playEffect(BOX_ON_TARGET);
                }
                prevBoard[i][j] = board[i][j];
            }
        }

        /**
         * Plays the sound effect when the player moves.
         */
        if (update == GameStateAndDirection.UP)
            playEffect(MOVE);
        else if (update == GameStateAndDirection.DOWN)
            playEffect(MOVE);
        else if (update == GameStateAndDirection.LEFT)
            playEffect(MOVE);
        else if (update == GameStateAndDirection.RIGHT)
            playEffect(MOVE);     
        else if (update == GameStateAndDirection.MUTE)
            Mute();
    }

}
