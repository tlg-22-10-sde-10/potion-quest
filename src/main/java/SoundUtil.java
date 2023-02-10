import javax.sound.sampled.BooleanControl;

public class SoundUtil {
    public static void toggleMusicMute() {
        Sound music = Game.getGameInstance().getSound();
        boolean mute = true;
        final BooleanControl muteControl = (BooleanControl) music.getClip().getControl(BooleanControl.Type.MUTE);
        if(!muteControl.getValue()) {
            muteControl.setValue(mute);
        } else {
            muteControl.setValue(!mute);
        }

    }
}
