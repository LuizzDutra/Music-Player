import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AudioPlayer
{
    private final ArrayList<File> audioArray;
    private final Clip audioPlayer = AudioSystem.getClip();

    private boolean isLoopMode = false;

    private File curAudio;

    enum States{
        UNINITIALIZED,
        INITIALIZED
    }

    public States curState = States.UNINITIALIZED;

    private final String audioFolderPath = "./src/AudioFiles/";
    AudioPlayer() throws LineUnavailableException
    {
        audioArray = loadAudioArrayElements();
    }

    public void loadAudioByIndex(int index)
    {
        try {
            curState = States.INITIALIZED;
            curAudio = audioArray.get(index);
            audioPlayer.close();
            audioPlayer.open(AudioSystem.getAudioInputStream(curAudio));
        }
        catch (IndexOutOfBoundsException | UnsupportedAudioFileException | IOException e){
            System.out.println("Audio Not Found");
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadAudioByName(String name)
    {
        int index = -1;

        int i = 0;
        for(File file : audioArray){
            if(file.getName().equals(name)){
                index = i;
                break;
            }
            i++;
        }
        loadAudioByIndex(index);
    }

    public void stopAudio()
    {
        if (curState != States.UNINITIALIZED) {
            audioPlayer.close();
            curState = States.UNINITIALIZED;
        }
    }

    public void startAudio()
    {
        if(curState != States.UNINITIALIZED) {
            if(isLoopMode){
                audioPlayer.loop(Clip.LOOP_CONTINUOUSLY);
            }else{
                audioPlayer.loop(0);
            }
        }
    }

    public void pauseAudio()
    {
        if (curState != States.UNINITIALIZED) {
            this.audioPlayer.stop();
        }
    }

    public void restartAudio()
    {
        if (curState != States.UNINITIALIZED) {
            this.stopAudio();
            loadAudioByName(curAudio.getName());
            this.startAudio();
        }
    }
    public String[] getAudioList()
    {
        return new File(audioFolderPath).list();
    }

    public void toggleLoopMode(){
        isLoopMode = !isLoopMode;
        startAudio();
    }

    public boolean getLoopMode(){return isLoopMode;}

    public ArrayList<File> loadAudioArrayElements()
    {
        ArrayList<File> newAudioArray = new ArrayList<>();

        for(File file : Objects.requireNonNull(new File(audioFolderPath).listFiles())){
            if(file.isFile()){
                newAudioArray.add(file);
            }
        }

        return newAudioArray;
    }

}
