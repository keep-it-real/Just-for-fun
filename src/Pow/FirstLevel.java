package Pow;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.*;

public class FirstLevel extends JFrame implements KeyListener
{
    private JPanel panel;
    private ImageIcon background;
    private File sound, sound2, sound3, sound4;
    private AudioInputStream gameMusic, shootSound, winSound, loseSound;
    private Clip play1, play2, play3, play4;
    private Alien alien; 
    private Bomb[][] bomb;
    private int xAxis = 0, yAxis = 0, counter = 0;
    private Fireball[] fireball;
    private final Descent descent;
    private boolean lose = false;
   
    public FirstLevel()
    {
        panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                background = new ImageIcon("Images//blue_desert.png");
                g.drawImage(background.getImage(), 0, 0, null);
                alien.drawAlien(g);
                for(int i=0; i<bomb.length; i++)
                {
                    for(int j=0; j<bomb[i].length; j++)
                    {
                        bomb[i][j].drawBomb(g);
                    }
                }
                for(int i=0; i<fireball.length; i++)
                {
                    fireball[i].drawFireball(g);
                }
                g.setFont(new Font("Serif", Font.BOLD, 30));
                g.setColor(new Color(192, 140, 156));
                g.drawString("Score: " + Shoot.score + "/44", 10, 30); 
                win();
                lose();
            }
        };
        try
        {
            sound = new File("Sounds//gameMusic.wav");
            gameMusic = AudioSystem.getAudioInputStream(sound);
            play1 = AudioSystem.getClip();
            play1.open(gameMusic);
            play1.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        add(panel);
        panel.setBorder(new LineBorder(new Color(163, 184, 204), 3));
        setUndecorated(true);
        setSize(824, 700);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        panel.setFocusable(true);
        panel.addKeyListener(this);
        alien = new Alien(400, 530, "Images//alien.png");
        bomb = new Bomb[4][11];
        for(int i=0; i<bomb.length; i++)
        {
            for(int j=0; j<bomb[i].length; j++)
            {
                bomb[i][j] = new Bomb(xAxis, yAxis, "Images//bomb.png");
                xAxis += 75;
            }
            yAxis += 75;
            xAxis = 0;
        }
        fireball = new Fireball[10000];
        for(int i=0; i<fireball.length; i++)
        {
            fireball[i] = new Fireball(400, 1000, "Images//fireball.png");
        } 
        descent = new Descent(this, bomb);
        descent.start();
    }
    //alien's moves
    @Override
    public void keyPressed(KeyEvent ke) 
    {
        if(ke.getKeyCode() == KeyEvent.VK_D)
        {
            if(alien.getxAxis()<710)
            {
               alien.setxAxis(alien.getxAxis()+13);
            }
        }
        else if(ke.getKeyCode() == KeyEvent.VK_A)
        {
            if(alien.getxAxis()>0)
            {
               alien.setxAxis(alien.getxAxis()-13);
            }
        }
        panel.repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {} 
    
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            Shoot shoot = new Shoot(fireball[counter], this, bomb);
            fireball[counter].setxAxis(alien.getxAxis()+40);
            fireball[counter].setyAxis(alien.getyAxis()+130);
            shoot.start();
            counter++;
            try
            {
                sound2 = new File("Sounds//shoot.wav");
                shootSound = AudioSystem.getAudioInputStream(sound2);
                play2 = AudioSystem.getClip();
                play2.open(shootSound);
                play2.start(); 
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        panel.repaint();
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if(JOptionPane.showConfirmDialog(null, "Do you wanna exit?", 
                       "Pow", JOptionPane.YES_NO_OPTION) ==
                       JOptionPane.YES_NO_OPTION)
               {
                   System.exit(0);
               }
        }
    }
    
    public void win()
    {
        if(Shoot.score==44)
        {
            descent.stop();
            dispose();
            play1.stop();
            try
            {
                sound3 = new File("Sounds//win.wav");
                winSound = AudioSystem.getAudioInputStream(sound3);
                play3 = AudioSystem.getClip();
                play3.open(winSound);
                play3.loop(0);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "You win!");
            Credits credits = new Credits();
            
            MainGame.play.loop(Clip.LOOP_CONTINUOUSLY);
            Shoot.score = 0;
        }
    }
    //'game over' if bombs hit the ground or touch the alien
    public void lose()
    {
        Rectangle myAlien = new Rectangle(alien.getxAxis(), alien.getyAxis(), 100, 160);
        for(int i=0; i<bomb.length; i++)
        {
            for(int j=0; j<bomb[i].length; j++)
            {
                Rectangle bombs = new Rectangle(bomb[i][j].getxAxis(), bomb[i][j].getyAxis(), 60, 65);
                if(bomb[i][j].getyAxis()>850)
                {
                    lose = true;
                    descent.stop();
                    break;
                }
                if(myAlien.intersects(bombs))
                {
                    lose = true;
                    descent.stop();
                    break;  
                }
            }
        }
        if(lose==true)
        {
            dispose();
            play1.stop();
            try
            {
                sound4 = new File("Sounds//lose.wav");
                loseSound = AudioSystem.getAudioInputStream(sound4);
                play4 = AudioSystem.getClip();
                play4.open(loseSound);
                play4.loop(0);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Game Over!");
            Credits credits = new Credits();
            MainGame.play.loop(Clip.LOOP_CONTINUOUSLY);
            Shoot.score = 0;
        }
    }
}