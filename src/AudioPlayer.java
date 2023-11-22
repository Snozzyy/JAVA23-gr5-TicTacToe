import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;
import static javax.sound.sampled.AudioSystem.getClip;

// Källa till SFX: https://www.soundjay.com/button-sounds-4.html
// https://mixkit.co/free-sound-effects/win/
// https://mixkit.co/free-sound-effects/lose/

class AudioPlayer {

    public AudioPlayer() {

        // SFX till knappar
        File btnSFX = new File("src/SFX/button-28.wav");

        Clip clip = null;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(btnSFX);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

// Test SFX
        /*
        JButton button = new JButton(spela up");
        Clip finalClip = clip;
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                finalClip.setFramePosition(0);
                finalClip.start();
            }
        });

        JOptionPane.showMessageDialog(null, button);


         */
    }
}

