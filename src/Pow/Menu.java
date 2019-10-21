package Pow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Menu extends JFrame
{
    private final JPanel panel;
    private final Button[] menuButton = new Button[4];
    private ImageIcon background;
   
    public Menu()
    {
        panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                background = new ImageIcon("Images//colored-desert.png");
                g.drawImage(background.getImage(), 0, 0, null);
            }
        };
            
        add(panel);
        panel.setBorder(new LineBorder(new Color(163, 184, 204), 3));
        setUndecorated(true);
        setSize(550, 600); 
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        menuButton[0] = new Button(new ImageIcon("Images//signPlay.png"));
        menuButton[1] = new Button(new ImageIcon("Images//signHelp.png"));
        menuButton[2] = new Button(new ImageIcon("Images//signCredits.png"));
        menuButton[3] = new Button(new ImageIcon("Images//signExit.png"));
        for(int i=0; i<menuButton.length; i++)
        {
            menuButton[i].setPreferredSize(new Dimension(270, 85));
            panel.add(menuButton[i]);
            menuButton[i].set(); 
        }
        menuButton[0].addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
               dispose();
               MainGame.play.stop();
               FirstLevel fl = new FirstLevel();
            }
        });
        menuButton[1].addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
              dispose();
              Help help = new Help();
            }
        });
        menuButton[2].addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                dispose();
                Credits credits = new Credits();
            }
        });
        menuButton[3].addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
              if(JOptionPane.showConfirmDialog(null, "Do you wanna exit?", 
                       "Pow", JOptionPane.YES_NO_OPTION) == 
                      JOptionPane.YES_NO_OPTION)
               {
                   System.exit(0);
               }
            }
        });  
    } 
    
    private class Button extends JButton
    {
        Icon icon;
        
        public Button(Icon icon)
        {
            this.icon = icon;
        }
        
        void set()
        {
            setIcon(icon);
            setBackground(new Color(163, 184, 204));
        }
    }
}
