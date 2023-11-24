import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;

import java.io.*;

import static javax.sound.sampled.AudioSystem.getClip;

// Källa till SFX: https://www.soundjay.com/button-sounds-4.html
// https://mixkit.co/free-sound-effects/win/
// https://mixkit.co/free-sound-effects/lose/

class Audio {
    private static Mixer.Info getInfo(File file) {
        Clip clip; // Playback till för-laddade ljud fil
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(file); // laddar in ljud fil i clip
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // Volym kontrol
        gainControl.setValue(-00.0f); // + Högre VOL | - Lägre VOL i Decibel
        clip.setFramePosition(0); // Startar om ljud fil
        clip.start(); // Spelar upp ljud fil

        return null;
    }

    static Mixer.Info btnSound() {
        File file = new File("src/SFX/button-28.wav");

        return getInfo(file);
    }

    static Mixer.Info winningSound() {
        File file = new File("src/SFX/player-winning-2020.wav");

        return getInfo(file);
    }

    static Mixer.Info playerDrawSound() {
        File file = new File("src/SFX/player-draw-947.wav");

        return getInfo(file);
    }
}
