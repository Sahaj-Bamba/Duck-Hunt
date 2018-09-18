/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import java.awt.Color;

/**
 *
 * @author admin
 */


public class NewGame extends GraphicsProgram{

    
    public static Color x ;
    //x=Color.
    /**
     *
     */
    public static GLabel Player_Score = new GLabel("Score = ");    
    public static GOval Duck =new GOval(400,400,50,50);
    private Color[] colors;
    @Override
    public void init()
	{
        super.init();
        colors = new Color[9];
        colors[0]=Color.red;
        colors[1]=Color.GREEN;
        colors[2]=Color.BLUE;
        colors[3]=Color.CYAN;
        colors[4]=Color.GRAY;
        colors[5]=Color.MAGENTA;
        colors[6]=Color.PINK;
        colors[7]=Color.YELLOW;
        addMouseListeners();
    }
    
    public void FL(String a)
    {
        
       //System.out.println("Hi");
        char[] initial =new char [a.length()];
        char[] org =new char[a.length()];
        initial[0]=a.charAt(0);
        org[0]=' ';
        for(int i=1;a.charAt(i)!='\n';i++)
        {
            org[i]=a.charAt(i);
            initial[i]=' ';
            if(a.charAt(i)==' ')
            {
                
                i++;
                if(a.charAt(i)==' ')
                    break;
                org[i]=' ';
                initial[i]=a.charAt(i);
            }
                
        }
        String st_org=new String(org);
        String st_initial =new String(initial);
        GLabel label_initial =new GLabel(st_initial);
        GLabel label_org =new GLabel(st_org);
        GLabel size=new GLabel(a);
        System.out.println(size.isVisible());
        size.setFont("SansSerif-50");
        label_initial.setFont("SansSerif-50");
        label_org.setFont("SansSerif-50");
        double x;
        x = (getWidth()-size.getWidth())/2;
        double y;
        y=(getHeight()+size.getAscent())/2;
        add(label_initial,x,y);
        label_initial.move(0,100);
        for(int i=0;i<10;i++)
        {
            label_initial.move(0,-10);
            label_initial.setColor(colors[i%7]);
            pause(200);
        }
        add(label_org,x,y);
        label_org.move(0,100);
        for(int i=0;i<10;i++)
        {
            label_org.move(0,-10);
            label_org.setColor(colors[i%7]);
          //  label_org.scale(1.1);
            pause(200);
        }
        removeAll();
        add(size,x,y);
        pause(500);
        System.out.println(size.isVisible());
        removeAll();
        System.out.println(size.isVisible());
    }
    @Override
    public void run(){
        init();
    FL("Game Begins  ");
        

    }

    
}


