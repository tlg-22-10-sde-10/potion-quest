import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {


    public void playSound(){
        String soundFile = "Medieval_game.wav";
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(this.getClass().getResource(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){

        }
    }
}
