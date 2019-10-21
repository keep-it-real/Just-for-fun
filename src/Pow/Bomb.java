package Pow;

import java.awt.*;
import javax.swing.*;

public class Bomb 
{
    private int xAxis, yAxis;
    private String imageSource;
    
    public Bomb(int xAxis, int yAxis, String imageSource)
    {
        super();
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.imageSource = imageSource;
    }
    
    public int getxAxis()
    {
        return xAxis;
    }
    
    public void setxAxis(int xAxis)
    {
        this.xAxis = xAxis;
    }  
    
    public int getyAxis()
    {
        return yAxis;
    }
    
    public void setyAxis(int yAxis)
    {
        this.yAxis = yAxis;
    }  
    
    public String getimageSource()
    {
        return imageSource;
    }
    
    public void setimageSource(String imageSource)
    {
        this.imageSource = imageSource;
    } 
    
    public void drawBomb(Graphics g)
    {
        ImageIcon bomb = new ImageIcon(imageSource);
        g.drawImage(bomb.getImage(), xAxis, yAxis, null);
    }
}
