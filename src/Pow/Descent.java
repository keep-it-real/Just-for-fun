package Pow;

public class Descent extends Thread
{
    private final FirstLevel fl;
    private final Bomb[][] bomb;
    
    public Descent(FirstLevel fl, Bomb[][] bomb)
    {
        this.fl = fl;
        this.bomb = bomb;
    }
    //range of motion and speed of falling bombs
    @Override
    public void run()
    {
        while(bomb[0][0].getyAxis()<1000)
        {
            for(int i=0; i<bomb.length; i++)
            {
                for(int j=0; j<bomb[i].length; j++)
                {
                    bomb[i][j].setyAxis(bomb[i][j].getyAxis()+10);
                }
            }
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
            fl.repaint();
        }
    }
}
