package Pow;

import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;

public class Shoot extends Thread
{
    private final Fireball fireball;
    private final FirstLevel fl;
    private final Bomb[][] bomb;
    private File sound;
    private AudioInputStream bombSound;
    private Clip play;
    public static int score = 0;
    
    public Shoot(Fireball fireball, FirstLevel fl, Bomb[][] bomb)
    {
        this.fireball = fireball;
        this.fl = fl;
        this.bomb = bomb;
    }
    //track and speed of fireball
    @Override
    public void run()
    {
        while(fireball.getyAxis()>-80)
        {
            fireball.setyAxis(fireball.getyAxis()-80);
            try 
            {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
            fl.repaint();
            destroyBombs();   
        }
    }
    
    public void destroyBombs()
    {
        Rectangle fireballs = new Rectangle(fireball.getxAxis(), fireball.getyAxis(), 20, 20);
        for(int i=0; i<bomb.length; i++)
        {
            for(int j=0; j<bomb[i].length; j++)
            {
                Rectangle bombs = new Rectangle(bomb[i][j].getxAxis(), bomb[i][j].getyAxis(), 65, 65);
                if(fireballs.intersects(bombs))
                {
                    fireball.setxAxis(-20000);
                    bomb[i][j].setxAxis(20000);
                    score++;
                    try
                    {
                        sound = new File("Sounds//bomb.wav");
                        bombSound = AudioSystem.getAudioInputStream(sound);
                        play = AudioSystem.getClip();
                        play.open(bombSound);
                        play.start(); 
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
