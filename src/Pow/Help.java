package Pow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Help extends JFrame
{
    private final JPanel panel;
    private ImageIcon background;
    private final JButton back;
  
    public Help()
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
                g.drawString("Shoot all the bombs", 150, 200); 
                g.drawString("before they blow you up!", 120, 250);
                g.drawString("Move:  A, D", 200, 350);
                g.drawString("Fire:    Shift", 200, 400);
                g.drawString("Exit:    Esc", 200, 450);
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
