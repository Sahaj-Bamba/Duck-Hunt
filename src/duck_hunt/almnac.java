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
    
    GImage star[] = new GImage[15];
  
    GImage Main_image = null;
        
    GRect Main = null;
    
    GLabel Topic = null;
    GLabel Description = null;
    GLabel Property1 = null;
    GLabel Property2 = null;
    GLabel Property3 = null;
    GLabel Name = null;
        
        
    
    public almnac() {
        this.Height = 1000;
        this.Width = 1900;
    }
    
    @Override
    public void run(){
        
        addMouseListeners();
        
        setSize((int)(Width*screen_width_fraction) ,(int)(Height*screen_height_fraction));
        setLocation((int)(350.0*screen_width_fraction) , (int)(screen_height_fraction*50.0));
        
        //System.out.println((screen_width_fraction));
        
                //      Create  Objects
        
        Menu1_1 = new GImage(Red.pic_location);
        Menu1_2 = new GImage(Yellow.pic_location);
        Menu1_3 = new GImage(Blue.pic_location);
        Menu1_4 = new GImage(Black.pic_location);
    
        for(int i=0;i<15;i++){
            star[i] = new GImage("C:\\Users\\admin\\Desktop\\Avishkar\\Cyber Quest\\Softblitz\\Duck_hunt\\Images\\Images\\star1.jpg");
        }
        
        Main_image = new GImage("");
        
        Main = new GRect(600*screen_width_fraction,500*screen_height_fraction);
                
        Topic = new GLabel("Almnac");
        Description = new GLabel("");
        Property1 = new GLabel("");
        Property2 = new GLabel("");
        Property3 = new GLabel("");
        Name = new GLabel("");
        
        //GOval p = new GOval(200,200);
        
                //      Set  Size
                
        Menu1_1.setSize(200*screen_width_fraction,200*screen_height_fraction);
        Menu1_2.setSize(150*screen_width_fraction,150*screen_height_fraction);
        Menu1_3.setSize(200*screen_width_fraction,200*screen_height_fraction);
        Menu1_4.setSize(200*screen_width_fraction,200*screen_height_fraction);
        
        for(int i=0;i<15;i++){
            star[i].setSize(40*screen_width_fraction,40*screen_height_fraction);
        }
        
        
        
       // this line is not in use curently
       // Main_image.setSize(300*screen_width_fraction,300*screen_height_fraction);

        Main.setSize(600*screen_width_fraction, 500*screen_height_fraction);
        
        Topic.setFont((new Font("Colonna MT", Font.BOLD, (int)(150*screen_width_fraction))));
        
        Description.setFont((new Font("Harrington", Font.BOLD, (int)(20*screen_width_fraction))));
        Property1.setFont((new Font("Serif", Font.ITALIC,(int)(25*screen_width_fraction))));
        Property2.setFont((new Font("Serif", Font.ITALIC,(int)(25*screen_width_fraction))));
        Property3.setFont((new Font("Serif", Font.ITALIC,(int)(25*screen_width_fraction))));
        Name.setFont((new Font("Curlz MT", Font.BOLD, (int)(50*screen_width_fraction))));
        
        
                //      Set  Locations
        
        Menu1_1.setLocation(400*screen_width_fraction,20*screen_height_fraction);
        Menu1_2.setLocation(1250*screen_width_fraction,20*screen_height_fraction);
        Menu1_3.setLocation(400*screen_width_fraction,720*screen_height_fraction);
        Menu1_4.setLocation(1200*screen_width_fraction,720*screen_height_fraction);
        
        Main_image.setLocation(600*screen_width_fraction,220*screen_height_fraction);
        
        Main.setLocation(600*screen_width_fraction, 220*screen_height_fraction);
        
        Topic.setLocation(650*screen_width_fraction,160*screen_height_fraction);

        Description.setLocation(630*screen_width_fraction,550*screen_height_fraction);
        Property1.setLocation(940*screen_width_fraction,360*screen_height_fraction);
        Property2.setLocation(940*screen_width_fraction,400*screen_height_fraction);
        Property3.setLocation(940*screen_width_fraction,440*screen_height_fraction);
        Name.setLocation(950*screen_width_fraction,280*screen_height_fraction);

        //p.setLocation(1200,720);
                
        for(int i=0;i<2;i++){
            star[i].setLocation((1120+i*35)*screen_width_fraction,330*screen_height_fraction);
        }
        for(int i=5;i<7;i++){
            star[i].setLocation((945+i*35)*screen_width_fraction,370*screen_height_fraction);
        }
        for(int i=10;i<12;i++){
            star[i].setLocation((772+i*35)*screen_width_fraction,410*screen_height_fraction);
        }
        
        
                //      Set other properties
        
        Main.setColor(Color.green);
        Main.setFilled(true);
        
        Topic.setColor(Color.blue);
        
        Description.setColor(Color.CYAN);
        Property1.setColor(Color.CYAN);
        Property2.setColor(Color.CYAN);
        Property3.setColor(Color.CYAN);
        Name.setColor(Color.magenta);
        
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
        add(Property1);
        add(Property2);
        add(Property3);
        add(Name);
        
        /*
        for(int i=0;i<5;i++){
            add(star[i]);
        }
        */
        
        //add(p);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        for(int i=0;i<15;i++){
            remove(star[i]);
        }
        if(Menu1_1.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.RED);
            
            Name.setLabel(Red.name);
            Property1.setLabel("Speed");
            Property2.setLabel("Hit Points");
            Property3.setLabel("Randomability");
            
            Description.setLabel(Red.bio);
            
            Main_image.setImage(Red.pic_location);
            
        
            for(int i=0;i<0;i++){
                add(star[i]);
            }
            for(int i=5;i<6;i++){
                add(star[i]);
            }
            for(int i=10;i<11;i++){
                add(star[i]);
            }

        
            
        }
        else if(Menu1_2.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.yellow);
            
            Name.setLabel(Yellow.name);
            
            Property1.setLabel("Speed");
            Property2.setLabel("Hit Points");
            Property3.setLabel("Randomability");
            
            Description.setLabel(Yellow.bio);
            
            Main_image.setImage(Yellow.pic_location);
    
            for(int i=0;i<2;i++){
                add(star[i]);
            }
            for(int i=5;i<6;i++){
                add(star[i]);
            }
            for(int i=10;i<10;i++){
                add(star[i]);
            }
            
        }
        else if(Menu1_3.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.BLUE);
            
            Name.setLabel(Blue.name);
            
            Property1.setLabel("Speed");
            Property2.setLabel("Hit Points");
            Property3.setLabel("Randomability");
            
            Description.setLabel(Blue.bio);
            
            Main_image.setImage(Blue.pic_location);
    
            for(int i=0;i<1;i++){
                add(star[i]);
            }
            for(int i=5;i<5;i++){
                add(star[i]);
            }
            for(int i=10;i<12;i++){
                add(star[i]);
            }
            
        }
        else if(Menu1_4.contains(e.getX(),e.getY())){
            
            Main.setColor(Color.BLACK);
            
            Name.setLabel(Black.name);
            
            Property1.setLabel("Speed");
            Property2.setLabel("Hit Points");
            Property3.setLabel("Randomability");
            
            Description.setLabel(Black.bio);
            
            Main_image.setImage(Black.pic_location);
    
            for(int i=0;i<0;i++){
                add(star[i]);
            }
            for(int i=5;i<7;i++){
                add(star[i]);
            }
            for(int i=10;i<11;i++){
                add(star[i]);
            }
            
        }
        
        Main_image.setSize(300*screen_width_fraction,300*screen_height_fraction);
    }
            
}
