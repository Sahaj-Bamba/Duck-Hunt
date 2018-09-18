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
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

/**
 *
 * @author user
 */

public class almnac extends GraphicsProgram{
    
    private final int Height;
    private final int Width;
    
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
        
        setSize(Width , Height);
        setLocation(350 , 50);
        
                //      Create  Objects
        
        Menu1_1 = new GImage(Red.pic_location);
        Menu1_2 = new GImage(Yellow.pic_location);
        Menu1_3 = new GImage(Blue.pic_location);
        Menu1_4 = new GImage(Black.pic_location);
        
        Main_image = new GImage("");
        
        Main = new GRect(600,300);
                
        Topic = new GLabel("Almnac");
        Description = new GLabel("");
        Property = new GLabel("");
        Name = new GLabel("");
        
        //GOval p = new GOval(200,200);
        
                //      Set  Size
                
        Menu1_1.setSize(200,200);
        Menu1_2.setSize(200,200);
        Menu1_3.setSize(200,200);
        Menu1_4.setSize(200,200);
        
        Main_image.setSize(300,300);

        Main.setSize(600, 500);
        
        Topic.setFont((new Font("Serif", Font.BOLD, 150)));
        
        Description.setFont((new Font("Serif", Font.BOLD, 20)));
        Property.setFont((new Font("Serif", Font.BOLD, 20)));
        Name.setFont((new Font("Serif", Font.BOLD, 50)));
        
        
                //      Set  Locations
        
        Menu1_1.setLocation(400,20);
        Menu1_2.setLocation(1200,20);
        Menu1_3.setLocation(400,720);
        Menu1_4.setLocation(1200,720);
        
        Main_image.setLocation(600,220);
        
        Main.setLocation(600, 220);
        
        Topic.setLocation(650,160);

        Description.setLocation(650,620);
        Property.setLocation(940,440);
        Name.setLocation(940,340);

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
            Main_image.setSize(300,300);
                
        }
        else if(Menu1_2.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.yellow);
            
            Name.setLabel(Yellow.name);
            
            Property.setLabel("kghesrnjkhageuik");
            
            Description.setLabel(Yellow.bio);
            
            Main_image.setImage(Yellow.pic_location);
            Main_image.setSize(300,300);
        }
        else if(Menu1_3.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.BLUE);
            
            Name.setLabel(Blue.name);
            
            Property.setLabel("jdsbvljkashb");
            
            Description.setLabel(Blue.bio);
            
            Main_image.setImage(Blue.pic_location);
            Main_image.setSize(300,300);
        }
        else if(Menu1_4.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.BLACK);
            
            Name.setLabel(Black.name);
            
            Property.setLabel("bhjsdghlkjsbgoloh");
            
            Description.setLabel(Black.bio);
            
            Main_image.setImage(Black.pic_location);
            Main_image.setSize(300,300);
        }
        
    }
            
}
