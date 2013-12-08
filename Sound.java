import java.io.File;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles playing, pausing, and looping of music for the game.
 * @author Eric Cochran
 */
public class Sound {
    private Clip clip;

    /**
     * Creates the sound from a file.
     *
     * @param fileName the file name of the sound
     */
    public Sound(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName);
            //File file = new File(getClass().getResource(fileName).getPath());
            //System.out.println(getClass().getResource(fileName).getPath());
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                //AudioInputStream sound =
                //AudioSystem.getAudioInputStream(this.getClass()
                //.getResourceAsStream(fileName));
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            } else {
                try {
                    InputStream in = getClass().getResourceAsStream(fileName);
                    //add buffer for mark/reset support
                    InputStream bufferedIn = new BufferedInputStream(in);
                    AudioInputStream sound = AudioSystem
                            .getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(sound);
                } catch (Exception e) {
                    e.printStackTrace();
                    //throw new RuntimeException(
                    //"Sound: file not found: " + fileName);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Sound: Line Unavailable Exception Error: " + e);
        }

    /**
     * Plays the sound.
     */
    }
    public void play() {
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }

    /**
     * Loops the sound, muhahahahaha.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Pauses the sound.
     */
    public void stop() {
        clip.stop();
    }
}
