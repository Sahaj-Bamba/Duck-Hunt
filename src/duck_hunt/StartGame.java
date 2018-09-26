
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

import java.util.Date;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import static duck_hunt.Duck_hunt.gamer;
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
import java.awt.Font;
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

    
            //          Scoring part 
    
    GLabel player_name = new GLabel("");
    GImage player_red_duck = new GImage(Red.pic_location);
    GImage player_blue_duck = new GImage(Blue.pic_location);
    GImage player_yellow_duck = new GImage(Yellow.pic_location);
    GLabel player_red_kill = new GLabel("");
    GLabel player_blue_kill = new GLabel("");
    GLabel player_yellow_kill = new GLabel("");

    private void set_loc_add(){
         
        // to set Loaction
        player_name.setLocation((1300.0*screen_width_fraction) , (screen_height_fraction*850.0));
        player_red_duck.setLocation((1570.0*screen_width_fraction),(screen_height_fraction)*810.0);
        player_blue_duck.setLocation((1500.0*screen_width_fraction),(screen_height_fraction)*810.0);
        player_yellow_duck.setLocation((1640.0*screen_width_fraction),(screen_height_fraction)*810.0);
        player_red_kill.setLocation((1565.0*screen_width_fraction),(screen_height_fraction)*840);
        player_blue_kill.setLocation((1495.0*screen_width_fraction),(screen_height_fraction)*840);
        player_yellow_kill.setLocation((1635.0*screen_width_fraction),(screen_height_fraction)*840);
         
        // To set Text Field 
        player_name.setLabel(gamer.get_name());
        player_red_kill.setLabel(""+gamer.get_kill(0));
        player_yellow_kill.setLabel(""+gamer.get_kill(1));
        player_blue_kill.setLabel(""+gamer.get_kill(2));
        
        
        
      
        
        // To set the image size
        player_red_duck.setSize(50*screen_width_fraction, 50*screen_height_fraction);
        player_blue_duck.setSize(50*screen_width_fraction, 50*screen_height_fraction);
        player_yellow_duck.setSize(50*screen_width_fraction,50*screen_height_fraction);
        
        //  To set Font 
        player_name.setFont((new Font("Serif", Font.BOLD,(int)(40*screen_width_fraction))));
        player_red_kill.setFont((new Font("Serif", Font.BOLD,(int)(30*screen_width_fraction))));
        player_red_kill.setColor(Color.RED);
        player_blue_kill.setFont((new Font("Serif", Font.BOLD,(int)(30*screen_width_fraction))));
        player_blue_kill.setColor(Color.BLUE);
        player_yellow_kill.setFont((new Font("Serif", Font.BOLD,(int)(30*screen_width_fraction))));
        player_yellow_kill.setColor(Color.yellow);
        
        
        player_name.setColor(Color.red);
                
        add(player_name);
        add(player_red_duck);
        add(player_blue_duck);
        add(player_yellow_duck);
        add(player_red_kill);
        add(player_blue_kill);
        add(player_yellow_kill);        
                
        player_name.setLabel(gamer.get_name());
    }

    private void update_scoring(){
        
        player_red_kill.setLabel(""+gamer.get_kill(0));
        player_yellow_kill.setLabel(""+gamer.get_kill(1));
        player_blue_kill.setLabel(""+gamer.get_kill(2));
        
    }
    
            
    
    
    //      Public variables must be transported to Duck-Hunt class
    
    //          Game control Variables
    
    int frame = 0;
    int active_gun = 0;


    private final int fps = 35;

    
    
    public int number_of_birds = 50;
    public Ducks ducks[] = new Ducks[number_of_birds];
    public GImage ducks_pic[] = new GImage[number_of_birds];
    public int round_num = 0;
    
    
    
    //          Variables used in this class
    
    GRect level1 = new GRect(1800*screen_width_fraction,550*screen_height_fraction);                        //      gamezone
    GRect level2 = new GRect(1800*screen_width_fraction,200*screen_height_fraction);                        //      mid
    GRect level3 = new GRect(1800*screen_width_fraction,200*screen_height_fraction);                        //      graund base
    GRect Gun = new GRect(200*screen_width_fraction,400*screen_height_fraction);
    GRect Gun1 = new GRect(200*screen_width_fraction,2010*screen_height_fraction);
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
    
    GImage _level1 = new GImage("Images\\Images\\level1.gif");
    
    GImage _gun1 = new GImage(ak.pic_location+"_1_1.png");
    GImage _gun2 = new GImage(shotgun.pic_location+"_1_1.png");
    GImage _gun3 = new GImage(laser.pic_location+"_1_1.png");
    GImage _gun4 = new GImage(sniper.pic_location+"_1_1.png");
    GImage _gun = new GImage(ak.pic_location+"_2_1.png");
    
    Guns __gun1 = new Guns(ak);
    Guns __gun2 = new Guns(shotgun);
    Guns __gun3 = new Guns(laser);
    Guns __gun4 = new Guns(sniper);
    Guns __gun[] = new Guns[4];
    
    public void initialisations(){
        
        for(int i=0;i<4;i++){
            switch(i){
                case 0:
                            __gun[i]=new Guns(ak);
                    break;
                case 1:
                            __gun[i]=new Guns(shotgun);
                    break;
                case 2:
                            __gun[i]=new Guns(laser);
                    break;
                case 3:
                            __gun[i]=new Guns(sniper);
                    break;
                default:
                    break;
            }
        }
    
    }
   
    GImage _pointer = new GImage("Images\\Images\\Pointers\\" + gamer.get_pointer_pic() + ".png");
    
    
    
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
        setLocation(1222220,112222220);
        
        
        for(int i=0;i<number_of_birds;i++){
            
            int x =(int) (Math.random() * 4);
            //x=1;          //  for testing
            int y =(int) (Math.random() * angles_allowed.length);
            
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
            
            ducks[i].set_entry_date(new Date());
            
            ducks[i].angle=angles_allowed[y];
            ducks_pic[i] = new GImage("Images\\Images\\"+(x+1)+"\\"+(int)ducks[i].angle+".png");
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
        ducks_pic[i].setSize(ducks[i].get_size(),ducks[i].get_size());
    }

        //          Start all threads for initial and suspend
       

        //          Seting initial location of birds
        
        for(int i=0;i<number_of_birds;i++){
            
            int x =(int) ((Math.random() * 1300) + 200);
            int y = (int) ((Math.random() * 600) + 50);
            
            ducks_pic[i].setLocation( x, y);
            
        }
        
        //      Adhusting different zones or levels
        
        level1.setLocation(0*screen_width_fraction,0*screen_height_fraction);
        level2.setLocation(0*screen_width_fraction,750*screen_height_fraction);
        level3.setLocation(0*screen_width_fraction,750*screen_height_fraction);
        Gun.setLocation(600*screen_width_fraction,550*screen_height_fraction);
        Gun1.setLocation(0*screen_width_fraction,750*screen_height_fraction);
        Gun2.setLocation(200*screen_width_fraction,750*screen_height_fraction);
        Gun3.setLocation(400*screen_width_fraction,750*screen_height_fraction);
        Gun4.setLocation(600*screen_width_fraction,750*screen_height_fraction);
        
        Pointer.setLocation(0*screen_width_fraction,0*screen_height_fraction);
        
        _level1.setLocation(0*screen_width_fraction,0*screen_height_fraction);
        
        
        _gun.setLocation(600*screen_width_fraction,550*screen_height_fraction);
        _gun1.setLocation(0*screen_width_fraction,750*screen_height_fraction);
        _gun2.setLocation(200*screen_width_fraction,750*screen_height_fraction);
        _gun3.setLocation(400*screen_width_fraction,750*screen_height_fraction);
        _gun4.setLocation(600*screen_width_fraction,750*screen_height_fraction);
        
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
    // declare grect for location
         GRect rc = new GRect(40*screen_width_fraction,105*screen_height_fraction);
         GRect rc1 = new GRect(80*screen_width_fraction,70*screen_height_fraction);
         GRect rc2 = new GRect (500*screen_width_fraction ,150*screen_height_fraction);
    @Override
    public void run(){
//        init();
        

         initialisations();
                 
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
        
        frame = 0;
           // To get location of a man.
        
        rc.setColor(Color.red);
        rc.setLocation(930*screen_width_fraction,675*screen_height_fraction);
        
        //To get location of three man
      
        rc1.setColor(Color.red);
        rc1.setLocation(600*screen_width_fraction,720*screen_height_fraction);
        
        //to Set Score Background
            
     
        rc2.setColor(Color.GRAY);
        rc2.setFilled(true);
        rc2.setLocation(1275*screen_width_fraction,800*screen_height_fraction);
      
        set_loc_add();
        
      
                 
        
        
        while(true){
            
        move_all();
        check_collision();
        check_death();
        check_has_left();
        update_scoring();
        frame++;
 
        pause(1000/fps);
        
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
        
add(rc);
        add(rc1);
        add(rc2);
        for(int i=0; i<number_of_birds; i++){
            if(ducks[i].is_alive)
            add(ducks_pic[i]);
        }
        
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
        
        
        move_all();
        check_collision();
        check_death();
        check_has_left();
        
        
        
        pause(1000/fps);
        //              Add Back All
        
        addall();
        
    }
    
    private void check_death(){
        
        for(int i=0; i<number_of_birds ;i++){
            if(ducks[i].is_alive == true)
            if(ducks[i].hitpoints <= 0){
                ducks[i].is_alive = false;
                Duck_hunt.gamer.set_score(Duck_hunt.gamer.get_score()+ducks[i].get_score());
                remove(ducks_pic[i]);
                gamer.kill_inc(ducks[i].get_type());
                System.out.println(Duck_hunt.gamer.get_score());
            }
        }
        
    }
    
    public void move_all(){
        
        for(int i=0; i<number_of_birds; i++){
            //if(ducks[i].get_has_left())
            if(ducks[i].is_alive)
            ducks_pic[i].move((Math.cos(ducks[i].angle*Math.PI/180.0)*ducks[i].speed),(-1)*(Math.sin(ducks[i].angle*Math.PI/180.0)*ducks[i].speed));
        }
        
    }
    
    public void check_has_left(){
        for(int i=0;i<number_of_birds;i++){
            if(new Date().getTime() - ducks[i].get_entry_date().getTime() > ducks[i].leavetime)
                ducks[i].set_has_left(true);
        }
    }
    
    public void check_collision(){
        
        boolean flag = false;
        int y,z,r = 0;
        
        for(int i=0; i<number_of_birds; i++){
            if(ducks[i].is_alive)
            if(!ducks[i].get_has_left())
            if(ducks_pic[i].getLocation().getX()>(1600*screen_width_fraction)||ducks_pic[i].getLocation().getX()<(200*screen_width_fraction)||ducks_pic[i].getLocation().getY()>(600*screen_height_fraction)||ducks_pic[i].getLocation().getY()<(10*screen_height_fraction)){
                y =(int) (Math.random() * 12);
                z= (int) (Math.random() * 3);
                int x=ducks[i].get_type();
                
                if(ducks_pic[i].getLocation().getX() > (1600*screen_width_fraction)){
                    switch(z){
                        case 0: r = 155;
                            break;
                        case 1: r = 180;
                            break;
                        case 2: r = 205;
                            break;
                    }
                }
                    
                if(ducks_pic[i].getLocation().getX() < (200*screen_width_fraction)){
                    switch(z){
                        case 0: r=25;
                            break;
                        case 1: r=0;
                            break;
                        case 2: r=335;
                            break;
                    }
                }
                    
                if(ducks_pic[i].getLocation().getY() > (600*screen_height_fraction)){
                    switch(z){
                        case 0: r=50;
                            break;
                        case 1: r=90;
                            break;
                        case 2: r=115;
                            break;
                    }
                }
                    
                if(ducks_pic[i].getLocation().getY() < (10*screen_height_fraction)){
                    switch(z){
                        case 0: r=270;
                            break;
                        case 1: r=310;
                            break;
                        case 2: r=230;
                            break;
                    }
                }
                    
                
                
                ducks[i].angle = r;
                
                ducks_pic[i].setImage("Images\\Images\\"+(x+1)+"\\"+(int)ducks[i].angle+".png");
                
                ducks_pic[i].setSize(ducks[i].get_size(),ducks[i].get_size());
                move_all();
                
                
            }
        }
      
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        System.out.println(player_blue_duck.getLocation().getY());
        System.out.println(rc2.getLocation().getY());
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
        
        remove(_gun);
        _gun.setImage("Images\\Images\\Guns\\"+(active_gun+1)+"_2_1"+".png");
        _gun.setSize(300*screen_width_fraction,400*screen_height_fraction); 
        add(_gun);
        
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
            remove(_pointer);
            _pointer.setLocation(e.getX()-25*screen_width_fraction,e.getY()-25*screen_width_fraction);
            _gun.setLocation(e.getX()-50*screen_width_fraction,600*screen_height_fraction);
            _gun.setSize(300*screen_width_fraction,400*screen_height_fraction); 
            _pointer.setSize(50*screen_width_fraction,50*screen_height_fraction);
            add(_pointer);
            add(_gun);
        }
                
}
    
    @Override
    public void mouseDragged(MouseEvent e){
        
        if(e.getY()<750*screen_height_fraction){
            remove(_gun);
            remove(_pointer);
            _pointer.setLocation(e.getX()-25*screen_width_fraction,e.getY()-25*screen_width_fraction);
            _gun.setLocation(e.getX()-50*screen_width_fraction,600*screen_height_fraction);
            _gun.setSize(300*screen_width_fraction,400*screen_height_fraction); 
            _pointer.setSize(50*screen_width_fraction,50*screen_height_fraction);
            add(_pointer);
            add(_gun);
        }        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        System.out.println(active_gun);
        
        java.util.Date dt = new java.util.Date();
        
        if(dt.getTime()-__gun[active_gun].get_prev_shoot_date().getTime() < __gun[active_gun].Delay){
            return;
        }
        
        /*
        if(frame - __gun[active_gun].get_previous_shoot() < __gun[active_gun].Delay){
            return;
        }
        */
        
        __gun[active_gun].set_prev_shoot_date( dt);
        
        // __gun[active_gun].set_previous_shoot(frame);
        
        for(int i=0; i<number_of_birds; i++){
            if(ducks[i].is_alive)
            if(ducks_pic[i].contains(e.getX(),e.getY())){
                ducks_pic[i].setSize(ducks_pic[i].getWidth()-ducks[i].width_dec,ducks_pic[i].getHeight()-ducks[i].width_dec);
                ducks[i].set_size((int) ducks_pic[i].getWidth());
                System.out.println(ducks[i].get_size());
                
                ducks[i].hitpoints  -= __gun[active_gun].Damage;
            }
        }
        
        //          Sounds
        
        
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
    playwav ak_sd = new playwav(ak.getsd());
    playwav shot_sd = new playwav(shotgun.getsd());
    playwav laser_sd = new playwav(laser.getsd());
    playwav sniper_sd = new playwav(sniper.getsd());
        */  
        
    }
    
    @Override
    public void mousePressed(MouseEvent e){
            //      Shooting sound
            
            
    }
    
    @Override
    public void mouseReleased(MouseEvent e){


    }

}








