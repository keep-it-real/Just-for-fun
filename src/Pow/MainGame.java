package Pow;

import java.io.*;
import javax.sound.sampled.*;

public class MainGame 
{   
    static File sound;
    static AudioInputStream menuMusic;
    static Clip play;
    //game starts here with common music to Menu, Credits and Help
    public static void main(String[] args)
    {
        Menu menu = new Menu();
        
        try
        {
            sound = new File("Sounds//menuMusic.wav");
            menuMusic = AudioSystem.getAudioInputStream(sound);
            play = AudioSystem.getClip();
            play.open(menuMusic);
            play.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
