
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
import static duck_hunt.Duck_hunt.ak;
import static duck_hunt.Duck_hunt.laser;
import static duck_hunt.Duck_hunt.screen_height_fraction;
import static duck_hunt.Duck_hunt.screen_width_fraction;
import static duck_hunt.Duck_hunt.shotgun;
import static duck_hunt.Duck_hunt.sniper;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StartGame extends GraphicsProgram 
{
    
        //          Flash Part
    
    int angles_allowed[]={0,25,50,90,115,155,180,205,230,270,310,335};

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
    
    //          Game control Variables
    
    long frame = 0;
    int active_gun = 0;
    
    
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

    //      Sounds
    /*
    playwav ak_sd = new playwav(ak.getsd());
    playwav shot_sd = new playwav(shotgun.getsd());
    playwav laser_sd = new playwav(laser.getsd());
    playwav sniper_sd = new playwav(sniper.getsd());
    */
    //          Images for different rects
    
    GImage _level1 = new GImage("C:\\Users\\admin\\Desktop\\Avishkar\\Cyber Quest\\Softblitz\\Duck_hunt\\Images\\Images\\level1.gif");
    
    GImage _gun1 = new GImage(ak.pic_location+"_1_1.png");
    GImage _gun2 = new GImage(shotgun.pic_location+"_1_1.png");
    GImage _gun3 = new GImage(laser.pic_location+"_1_1.png");
    GImage _gun4 = new GImage(sniper.pic_location+"_1_1.png");
    GImage _gun = new GImage(ak.pic_location+"_2_1.png");
    
    Guns __gun1 = new Guns(ak);
    Guns __gun2 = new Guns(shotgun);
    Guns __gun3 = new Guns(laser);
    Guns __gun4 = new Guns(sniper);
    Guns __gun = new Guns(ak);
    
    GImage _pointer = new GImage("C:\\Users\\admin\\Desktop\\Avishkar\\Cyber Quest\\Softblitz\\Duck_hunt\\Images\\Images\\Pointers\\1.png");
    
    @Override
    public void init(){

        addMouseListeners();
	addKeyListeners();
        
        colors = new Color[9];
        colors[0]=Color.red;
        colors[1]=Color.GREEN;
        colors[2]=Color.BLUE;
        colors[3]=Color.CYAN;
        colors[4]=Color.GRAY;
        colors[5]=Color.MAGENTA;
        colors[6]=Color.PINK;
        colors[7]=Color.YELLOW;
        

            
        setSize((int)(game_width*screen_width_fraction),(int)(game_height*screen_height_fraction));
        setLocation(0,0);
        
        
        for(int i=0;i<number_of_birds;i++){
            int x =(int) (Math.random() * 4);
            switch (x) {
                case 0:
                    ducks[i] = new Ducks(Red);
                    ducks_pic[i] = new GImage(Red.pic_location);
                    break;
                case 1:
                    ducks[i] = new Ducks(Yellow);
                    ducks_pic[i] = new GImage(Yellow.pic_location);
                    break;
                case 2:
                    ducks[i] = new Ducks(Blue);
                    ducks_pic[i] = new GImage(Blue.pic_location);
                    break;
                case 3:
                    ducks[i] = new Ducks(Black);
                    ducks_pic[i] = new GImage(Black.pic_location);
                    break;
                default:
                    break;
            }
        }
        

        //          Set initial angle of ducks
        
        
        

        //      Adjusting size
        
    _level1.setSize(1800*screen_width_fraction,950*screen_height_fraction);                        //      gamezone
    
    _gun1.setSize(200*screen_width_fraction,200*screen_height_fraction); 
    _gun2.setSize(200*screen_width_fraction,200*screen_height_fraction); 
    _gun3.setSize(200*screen_width_fraction,200*screen_height_fraction); 
    _gun4.setSize(200*screen_width_fraction,200*screen_height_fraction); 
    
    _gun.setSize(300*screen_width_fraction,400*screen_height_fraction); 
    
    _pointer.setSize(50*screen_width_fraction,50*screen_height_fraction);
        
        
        
    for(int i=0; i<number_of_birds; i++){
        ducks_pic[i].setSize(100, 100);
    }

        //          Start all threads for initial and suspend
       

        //      Adhusting different zones or levels
        
        level1.setLocation(0,0);
        level2.setLocation(0,750*screen_height_fraction);
        level3.setLocation(0,750*screen_height_fraction);
        Gun.setLocation(600,550*screen_height_fraction);
        Gun1.setLocation(0,750*screen_height_fraction);
        Gun2.setLocation(200,750*screen_height_fraction);
        Gun3.setLocation(400,750*screen_height_fraction);
        Gun4.setLocation(600,750*screen_height_fraction);
        
        Pointer.setLocation(0,0);
        
        _level1.setLocation(0,0);
        
        
        _gun.setLocation(600,550*screen_height_fraction);
        _gun1.setLocation(0,750*screen_height_fraction);
        _gun2.setLocation(200,750*screen_height_fraction);
        _gun3.setLocation(400,750*screen_height_fraction);
        _gun4.setLocation(600,750*screen_height_fraction);
        
        _pointer.setLocation(0,0);
        
        
        
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
//        init();
        

        
        //FL("Game Begins  ");
        /*
        System.out.println("hi");
            ak_sd.start();
            ak_sd.suspend();
            laser_sd.start();
            laser_sd.suspend();
            shot_sd.start();
            shot_sd.suspend();
            sniper_sd.start();
            sniper_sd.suspend();
            System.out.println("bye");
        */
        System.out.println("hello");
        addall();
        while(true){
            
            
        }

    }

    
    public void addall(){
        
        add(level2);
        add(level3);
        add(level1);
        
        add(Gun1);
        add(Gun2);
        add(Gun3);
        add(Gun4);
    
//        add(Gun);
        
//        add(Pointer);
        
        
        
        
        
        add(_level1);
        
        add(_gun1);
        add(_gun2);
        add(_gun3);
        add(_gun4);
    
        add(_gun);
        
        add(_pointer);
        
    }

    public void render(){
        
        removeAll();
        
        //              Perform Changes
        
        
        
        
        
        
        
        //              Add Back All
        
        addall();
        
    }
    
    
    
    @Override
    public void keyPressed(KeyEvent e){
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                                active_gun++;
                                break;
            case KeyEvent.VK_LEFT:    
            case KeyEvent.VK_A:
                                active_gun--;
                                break;
        }
        if(active_gun<0)
            active_gun=3;
        if(active_gun>3)
            active_gun=0;
    
        System.out.println(active_gun);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        
        if(e.getY()<750*screen_height_fraction){
/*            
            remove(Gun);
            Gun.setLocation(e.getX()-100*screen_width_fraction,550*screen_height_fraction);
            add(Gun);

            remove(Pointer);
            Pointer.setLocation(e.getX()-25*screen_width_fraction,e.getY()-25*screen_width_fraction);
            add(Pointer);
*/

            remove(_gun);
            _gun.setLocation(e.getX()-100*screen_width_fraction,550*screen_height_fraction);
            add(_gun);

            remove(_pointer);
            _pointer.setLocation(e.getX()-25*screen_width_fraction,e.getY()-25*screen_width_fraction);
            add(_pointer);


        }
        
        
        //pause(10);
        /*
        Gun.move(e.getX() - last_pos.getX(), e.getY() - last_pos.getY());
        last_pos = new GPoint(e.getPoint());
        */

    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        
        if(e.getY()<750*screen_height_fraction){

            remove(_gun);
            _gun.setLocation(e.getX()-100*screen_width_fraction,550*screen_height_fraction);
            add(_gun);

            remove(_pointer);
            _pointer.setLocation(e.getX()-25*screen_width_fraction,e.getY()-25*screen_width_fraction);
            add(_pointer);

        }        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        /*
    playwav ak_sd = new playwav(ak.getsd());
    playwav shot_sd = new playwav(shotgun.getsd());
    playwav laser_sd = new playwav(laser.getsd());
    playwav sniper_sd = new playwav(sniper.getsd());
        */  
    }
    
    @Override
    public void mousePressed(MouseEvent e){
            //      Shooting sound
            
            
            switch(active_gun){
                case 0:new playwav(ak.getsd()).start();
                        break;
                case 1:new playwav(shotgun.getsd()).start();
                        break;
                case 2:new playwav(laser.getsd()).start();
                        break;
                case 3:new playwav(sniper.getsd()).start();
                        break;
                default:
                        break;
            }
            
            
            /*
            switch(active_gun){
                case 0:ak_sd.resume();
                        break;
                case 1:shot_sd.resume();
                        break;
                case 2:laser_sd.resume();
                        break;
                case 3:sniper_sd.resume();
                        break;
                default:
                        break;
            }
            */
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
            //      Turn off shooting sound
            
/*            
            switch(active_gun){
                case 0:ak_sd.suspend();
                        break;
                case 1:shot_sd.suspend();
                        break;
                case 2:laser_sd.suspend();
                        break;
                case 3:sniper_sd.suspend();
                        break;
                default:
                        break;
            }
*/  
    }
}








