import javax.sound.sampled.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws LineUnavailableException
    {
        AudioPlayer MusicPlayer = new AudioPlayer();

        System.out.println(Arrays.toString(MusicPlayer.getAudioList()));

        Scanner inputScanner = new Scanner(System.in);
        String userInput;

        boolean run = true;

        while(run){
            userInput = inputScanner.nextLine();
            switch (userInput.toUpperCase()) {
                case ("P") -> MusicPlayer.pauseAudio();
                case ("S") -> MusicPlayer.startAudio();
                case ("R") -> MusicPlayer.restartAudio();
                case ("E") -> MusicPlayer.stopAudio();
                case ("L") -> {MusicPlayer.toggleLoopMode(); System.out.println(MusicPlayer.getLoopMode());}
                case ("Q") -> run = false;
                default -> {MusicPlayer.loadAudioByName(userInput); MusicPlayer.startAudio();}
            }

        }

    }
}