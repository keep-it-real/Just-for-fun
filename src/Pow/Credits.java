package Pow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Credits extends JFrame
{
    private final JPanel panel;
    private ImageIcon background;
    private final JButton back;
    
    public Credits()
    {
        panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                background = new ImageIcon("Images//blue_land.png");
                g.drawImage(background.getImage(), 0, 0, null);
                g.setFont(new Font("Serif", Font.BOLD, 30));
                g.setColor(new Color(192, 140, 156));
                g.drawString("Made by:", 90, 200); 
                g.drawString("Dorota Kargul", 150, 250);
                g.drawString("(dorota.kargul.89@gmail.com)", 150, 285);
                g.drawString("Graphics:", 90, 355);
                g.drawString("Kenney.nl", 150, 405);
                g.drawString("Music & Sounds:", 90, 475);
                g.drawString("audionautix.com", 150, 525);
                g.drawString("freesound.org", 150, 560);
            }
        };
        
        add(panel);
        panel.setBorder(new LineBorder(new Color(163, 184, 204), 3));
        setUndecorated(true);
        setSize(550, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        back = new JButton(new ImageIcon("Images//signBack.png"));
        back.setPreferredSize(new Dimension(115, 95));
        back.setBackground(new Color(163, 184, 204));
        panel.add(back);
        back.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                dispose();
                Menu menu = new Menu();
            }
        });
    }
}
