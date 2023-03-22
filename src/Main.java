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
            String userInputBuffer = inputScanner.nextLine();
            userInput = userInputBuffer.toUpperCase();
            switch (userInput) {
                case ("P") -> MusicPlayer.pauseAudio();
                case ("S") -> MusicPlayer.startAudio();
                case ("R") -> MusicPlayer.restartAudio();
                case ("E") -> MusicPlayer.stopAudio();
                case ("L") -> {MusicPlayer.toggleLoopMode(); System.out.println(MusicPlayer.getLoopMode());}
                case ("Q") -> run = false;
                default -> {MusicPlayer.loadAudioByName(userInputBuffer); MusicPlayer.startAudio();}
            }

        }

    }
}