/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import static duck_hunt.Duck_hunt.Black;
import static duck_hunt.Duck_hunt.Blue;
import static duck_hunt.Duck_hunt.Red;
import static duck_hunt.Duck_hunt.Yellow;
import static duck_hunt.Duck_hunt.screen_height_fraction;
import static duck_hunt.Duck_hunt.screen_width_fraction;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

/**
 *
 * @author user
 */

public class almnac extends GraphicsProgram{
    
    private final double Height;
    private final double Width;
    
    GImage Menu1_1 = null;
    GImage Menu1_2 = null;
    GImage Menu1_3 = null;
    GImage Menu1_4 = null;
    GImage Main_image = null;
        
    GRect Main = new GRect(600,300);
    
    GLabel Topic = null;
    GLabel Description = null;
    GLabel Property = null;
    GLabel Name = null;
        
        
    
    public almnac() {
        this.Height = 1000;
        this.Width = 1850;
    }
    
    @Override
    public void run(){
        
        addMouseListeners();
        
        setSize((int)(Width*screen_width_fraction) ,(int)(Height*screen_height_fraction));
        setLocation((int)(350.0*screen_width_fraction) , (int)(screen_height_fraction*50.0));
        
        System.out.println((screen_width_fraction));
        
                //      Create  Objects
        
        Menu1_1 = new GImage(Red.pic_location);
        Menu1_2 = new GImage(Yellow.pic_location);
        Menu1_3 = new GImage(Blue.pic_location);
        Menu1_4 = new GImage(Black.pic_location);
        
        Main_image = new GImage("");
        
        Main = new GRect(600*screen_width_fraction,300*screen_height_fraction);
                
        Topic = new GLabel("Almnac");
        Description = new GLabel("");
        Property = new GLabel("");
        Name = new GLabel("");
        
        //GOval p = new GOval(200,200);
        
                //      Set  Size
                
        Menu1_1.setSize(200*screen_width_fraction,200*screen_height_fraction);
        Menu1_2.setSize(200*screen_width_fraction,200*screen_height_fraction);
        Menu1_3.setSize(200*screen_width_fraction,200*screen_height_fraction);
        Menu1_4.setSize(200*screen_width_fraction,200*screen_height_fraction);
        
        Main_image.setSize(300*screen_width_fraction,300*screen_height_fraction);

        Main.setSize(600*screen_width_fraction, 500*screen_height_fraction);
        
        Topic.setFont((new Font("Serif", Font.BOLD, (int)(150*screen_width_fraction))));
        
        Description.setFont((new Font("Serif", Font.BOLD, (int)(20*screen_width_fraction))));
        Property.setFont((new Font("Serif", Font.BOLD,(int)(20*screen_width_fraction))));
        Name.setFont((new Font("Serif", Font.BOLD, (int)(50*screen_width_fraction))));
        
        
                //      Set  Locations
        
        Menu1_1.setLocation(400*screen_width_fraction,20*screen_height_fraction);
        Menu1_2.setLocation(1200*screen_width_fraction,20*screen_height_fraction);
        Menu1_3.setLocation(400*screen_width_fraction,720*screen_height_fraction);
        Menu1_4.setLocation(1200*screen_width_fraction,720*screen_height_fraction);
        
        Main_image.setLocation(600*screen_width_fraction,220*screen_height_fraction);
        
        Main.setLocation(600*screen_width_fraction, 220*screen_height_fraction);
        
        Topic.setLocation(650*screen_width_fraction,160*screen_height_fraction);

        Description.setLocation(650*screen_width_fraction,620*screen_height_fraction);
        Property.setLocation(940*screen_width_fraction,440*screen_height_fraction);
        Name.setLocation(940*screen_width_fraction,340*screen_height_fraction);

        //p.setLocation(1200,720);
                
                //      Set other properties
        
        Main.setColor(Color.green);
        Main.setFilled(true);
        
        Topic.setColor(Color.CYAN);
        
        Description.setColor(Color.CYAN);
        Property.setColor(Color.CYAN);
        Name.setColor(Color.CYAN);
        
        //p.setFillColor(Color.yellow);
        //p.setFilled(true);
                
                //      Add  Everything on the screen
                
        add(Menu1_1);
        add(Menu1_2);
        add(Menu1_3);
        add(Menu1_4);
        
        add(Main);
                
        add(Topic);
        
        add(Main_image);
        add(Description);
        add(Property);
        add(Name);
        
        //add(p);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        if(Menu1_1.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.RED);
            
            Name.setLabel(Red.name);
            Property.setLabel("kfgwjkbagl");
            Description.setLabel(Red.bio);
            
            Main_image.setImage(Red.pic_location);
            
                
        }
        else if(Menu1_2.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.yellow);
            
            Name.setLabel(Yellow.name);
            
            Property.setLabel("kghesrnjkhageuik");
            
            Description.setLabel(Yellow.bio);
            
            Main_image.setImage(Yellow.pic_location);
            
        }
        else if(Menu1_3.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.BLUE);
            
            Name.setLabel(Blue.name);
            
            Property.setLabel("jdsbvljkashb");
            
            Description.setLabel(Blue.bio);
            
            Main_image.setImage(Blue.pic_location);
            
        }
        else if(Menu1_4.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.BLACK);
            
            Name.setLabel(Black.name);
            
            Property.setLabel("bhjsdghlkjsbgoloh");
            
            Description.setLabel(Black.bio);
            
            Main_image.setImage(Black.pic_location);
            
        }
        Main_image.setSize(300*screen_width_fraction,300*screen_height_fraction);
    }
            
}
