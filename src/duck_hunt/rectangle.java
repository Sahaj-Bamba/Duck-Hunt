/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

/**
 *
 * @author user
 */import acm.graphics.*;
  import acm.program.*;
public class rectangle extends GraphicsProgram
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int REFRESH = 50;
    
    public void run()
    {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        addMouseListeners();
        addKeyListeners();
        GRect rect = new GRect(50, 50);
        add(rect);
    }
}

