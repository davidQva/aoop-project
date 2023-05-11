package framework;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class GameSound implements GameObserver {

    int[][] prevBoard;
    GameStateAndDiraction prevState;

    public static int MOVE = 0;
    public static int BOX_ON_TARGET = 1;

    private float volume = 0.5f;
    private Clip[] effects;
    private boolean effectMute = false;

    public GameSound() {
        loadEffects();
    }

    public void playEffect(int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    private void loadEffects() {
        String[] effects = { "move2", "pling" };
        this.effects = new Clip[effects.length];
        for (int i = 0; i < effects.length; i++) {
            this.effects[i] = getClip(effects[i]);
        }
    }

    private void updateEffectVolume() {

        for (Clip clip : effects) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }

    private Clip getClip(String effect) {

        URL url = getClass().getResource("/audio/" + effect + ".wav");
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

    @Override
    public void updateGame(int[][] board, GameStateAndDiraction update) {

        if (prevBoard == null) {
            this.prevBoard = new int[board.length][board[0].length];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 1 && prevBoard[i][j] != -1) {
                    prevBoard[i][j] = -1;
                    playEffect(BOX_ON_TARGET);
                }
            }
        }

        if (update == GameStateAndDiraction.UP)
            playEffect(MOVE);
        else if (update == GameStateAndDiraction.DOWN)
            playEffect(MOVE);
        else if (update == GameStateAndDiraction.LEFT)
            playEffect(MOVE);
        else if (update == GameStateAndDiraction.RIGHT)
            playEffect(MOVE);

        /*
         * else if(update == GameStateAndDiraction.BOX_ON_TARGET)
         * playEffect(BOX_ON_TARGET);
         */
    }

}