
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;


/**
 *
 * @author admin
 */
/*

*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import static duck_hunt.Duck_hunt.Black;
import static duck_hunt.Duck_hunt.Blue;
import static duck_hunt.Duck_hunt.Red;
import static duck_hunt.Duck_hunt.Yellow;
import static duck_hunt.Duck_hunt.screen_height_fraction;
import static duck_hunt.Duck_hunt.screen_width_fraction;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StartGame extends GraphicsProgram 
{
    
        //          Flash Part
    
    

    public void FL(String a)
    {

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
            label_initial.setColor(Color.CYAN);
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
        
        removeAll();
        
    }
    
            //              Flash Ends
    
    
    
    
    
    private final double game_height = 1000;
    private final double game_width = 1800;
    
    
    Color[] colors;                                     // Array of colors for flash

    
    
    //      Public variables must be transported to Duck-Hunt class
    
    public long frame;
    public Player player = new Player("Sahaj");
    public int number_of_birds = 2;
    public Ducks ducks[] = new Ducks[number_of_birds];
    public GImage ducks_pic[] = new GImage[number_of_birds];
    public int round_num = 0;
    
    
    
    //          Variables used in this class
    
    GRect level1 = new GRect(1800*screen_width_fraction,550*screen_height_fraction);                        //      gamezone
    GRect level2 = new GRect(1800*screen_width_fraction,200*screen_height_fraction);                        //      mid
    GRect level3 = new GRect(1800*screen_width_fraction,200*screen_height_fraction);                        //      graund base
    GRect Gun = new GRect(200*screen_width_fraction,400*screen_height_fraction);
    GRect Gun1 = new GRect(200*screen_width_fraction,200*screen_height_fraction);
    GRect Gun2 = new GRect(200*screen_width_fraction,200*screen_height_fraction);
    GRect Gun3 = new GRect(200*screen_width_fraction,200*screen_height_fraction);
    GRect Gun4 = new GRect(200*screen_width_fraction,200*screen_height_fraction);
    GPoint last_pos = new GPoint();
    GOval Pointer = new GOval(50*screen_width_fraction,50*screen_height_fraction);
    
    @Override
    public void init(){

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
	addKeyListeners();
            
        setSize((int)(game_width*screen_width_fraction),(int)(game_height*screen_height_fraction));
        setLocation(0,0);
        
        for(int i=0;i<number_of_birds;i++){
            int x =(int) (Math.random() * 4);
            switch (x) {
                case 0:
                    ducks[i] = new Ducks(Red);
                    break;
                case 1:
                    ducks[i] = new Ducks(Yellow);
                    break;
                case 2:
                    ducks[i] = new Ducks(Blue);
                    break;
                case 3:
                    ducks[i] = new Ducks(Black);
                    break;
                default:
                    break;
            }
        }
        
        //      Adhusting different zones or levels
        
        level1.setLocation(0,0);
        level2.setLocation(0,550*screen_height_fraction);
        level3.setLocation(0,750*screen_height_fraction);
        Gun.setLocation(600,550*screen_height_fraction);
        Gun1.setLocation(0,550*screen_height_fraction);
        Gun2.setLocation(200,550*screen_height_fraction);
        Gun3.setLocation(400,550*screen_height_fraction);
        Gun4.setLocation(600,550*screen_height_fraction);
        
        Pointer.setLocation(0,0);
        
        level1.setFilled(true);
        level2.setFilled(true);
        level3.setFilled(true);
        Gun.setFilled(true);
        Gun1.setFilled(true);
        Gun2.setFilled(true);
        Gun3.setFilled(true);
        Gun4.setFilled(true);
        
        Pointer.setFilled(true);
        
        level1.setColor(Color.BLUE);
        level2.setColor(Color.yellow);
        level3.setColor(Color.GREEN);
        Gun.setColor(Color.BLUE);
        Gun1.setColor(Color.LIGHT_GRAY);
        Gun2.setColor(Color.ORANGE);
        Gun3.setColor(Color.YELLOW);
        Gun4.setColor(Color.RED);
        
        Pointer.setColor(Color.RED);
        
        frame = 0;
        
    }
    
    @Override
    public void run(){
        init();
        //FL("Game Begins  ");
        
        
        addall();
        while(true){
            
            
        }

    }

    
    public void addall(){
        
        add(level1);
        add(level2);
        add(level3);
        
        add(Gun1);
        add(Gun2);
        add(Gun3);
        add(Gun4);
    
        add(Gun);
        
        add(Pointer);
        
    }

    public void render(){
        
        removeAll();
        
        //              Perform Changes
        
        
        
        
        
        
        
        //              Add Back All
        
        addall();
        
    }
    
    
    
    public void KeyPressed(KeyEvent e){
        
        switch(e.getKeyCode()){
            
        }
    
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        
        if(e.getY()<550*screen_height_fraction){
            
            remove(Gun);
            Gun.setLocation(e.getX()-100*screen_width_fraction,550*screen_height_fraction);
            add(Gun);

            remove(Pointer);
            Pointer.setLocation(e.getX()-25*screen_width_fraction,e.getY()-25*screen_width_fraction);
            add(Pointer);

        }
        //pause(10);
        /*
        Gun.move(e.getX() - last_pos.getX(), e.getY() - last_pos.getY());
        last_pos = new GPoint(e.getPoint());
        */

    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        
    }
    
    

}








