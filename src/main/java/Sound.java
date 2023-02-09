import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL []= new URL[10];

    public Sound (){
        soundURL[0] = getClass().getResource("");
        soundURL[1] = getClass().getResource("");
    }

    public void setFile(int i){
        try{
            AudioInputStream audioSystem = AudioSystem.getAudioInputStream(soundURL[i]);
        } catch (Exception e){

        }
    }

    public void play(){clip.start();}
    public void loopMusic(){clip.loop(Clip.LOOP_CONTINUOUSLY);}
    public void stopMusic(){clip.stop();}

}
