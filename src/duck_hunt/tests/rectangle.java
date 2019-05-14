/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt.tests;

/**
 *
 * @author user
 */import acm.graphics.*;
  import acm.program.*;
import java.awt.Color;
public class rectangle extends GraphicsProgram
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int REFRESH = 5;
    
    public void run()
    {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        addMouseListeners();
        addKeyListeners();
        GRect rect1 = new GRect(50, 50);
        rect1.setLocation(300,  300);
        rect1.setColor(Color.PINK);
        rect1.setFilled(true);
        rect1.setFillColor(Color.yellow);
        GRect rect2 = new GRect(100,10);
        rect2.setLocation(10,100);
        rect2.setColor(Color.RED);
        rect2.setFilled(true);
        add(rect1);
        add(rect2);
        while(true)
        {
            rect2.setLocation(rect2.getLocation().getX() + 1 ,rect2.getLocation().getY());
            if(rect2.getLocation().getX() > 790)
            {
                rect2.setLocation(0,rect2.getLocation().getY());
           
            }
            pause(REFRESH);
        }
    }
}

