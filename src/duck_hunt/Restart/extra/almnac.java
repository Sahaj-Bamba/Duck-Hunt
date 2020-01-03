/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt.Restart.extra;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import duck_hunt.Restart.GameGlobalVariables;
import duck_hunt.Restart.GameObjects.Ducks.*;

import java.awt.*;
import java.awt.event.MouseEvent;


/**
 *
 * @author Sahaj Bamba
 *
 * Almanac
 *
 * A brief intro about different in-game objects like birds guns and many more.
 *
 */


public class almnac extends GraphicsProgram{
    
    private final double Height;
    private final double Width;

    private final double screen_height_fraction = GameGlobalVariables.getInstance().getScreenHeightFraction();
    private final double screen_width_fraction = GameGlobalVariables.getInstance().getScreenWidthFraction();

    GImage[] Menu1 = new GImage[4];
    Duck[] ducks =new Duck[4];

    GImage[] star = new GImage[15];
  
    GImage Main_image = null;

    GRect Main = null;
    GRect back = null;

    GLabel Topic = null;
    GLabel Name = null;
    GLabel Description = null;
    GLabel Property1 = null;
    GLabel Property2 = null;
    GLabel Property3 = null;

    public almnac() {

        this.Height = 1000;
        this.Width = 1900;

        ducks[0]=new RedDuck();
        ducks[1] = new BlueDuck();
        ducks[2]=new YellowDuck();
        ducks[3] = new BlackDuck();
        for (int i=0;i<4;i++){
            Menu1[i] = new GImage(ducks[i].getMenuPicLocation());
        }
        for(int i=0;i<15;i++){
            star[i] = new GImage("Images/Images/star1.jpg");
        }
    }

    @Override
    public void init() {
        addMouseListeners();
    }

    @Override
    public void run(){
        //exit();

        setSize((int)(Width*screen_width_fraction) ,(int)(Height*screen_height_fraction));
        setLocation((int)(350.0*screen_width_fraction) , (int)(screen_height_fraction*50.0));
        
                //      Create  Objects

        Main_image = new GImage("");

        Main = new GRect(600*screen_width_fraction,500*screen_height_fraction);
        back = new  GRect(1900*screen_width_fraction,1000*screen_height_fraction);         
        
        Topic = new GLabel("Almnac");
        Description = new GLabel("");
        Property1 = new GLabel("");
        Property2 = new GLabel("");
        Property3 = new GLabel("");
        Name = new GLabel("");
        
        //GOval p = new GOval(200,200);

                //      Set  Size

        for (GImage m : Menu1) {
            m.setSize(200*screen_width_fraction,150*screen_height_fraction);
        }
        for(int i=0;i<15;i++){
            star[i].setSize(40*screen_width_fraction,40*screen_height_fraction);
        }


       // this line is not in use curently
       // Main_image.setSize(300*screen_width_fraction,300*screen_height_fraction);

        Main.setSize(600*screen_width_fraction, 450*screen_height_fraction);
        back.setSize(1900*screen_width_fraction, 1000*screen_height_fraction);
        
        Topic.setFont((new Font("Colonna MT", Font.BOLD, (int)(150*screen_width_fraction))));
        
        Description.setFont((new Font("Harrington", Font.BOLD, (int)(20*screen_width_fraction))));
        Property1.setFont((new Font("Serif", Font.ITALIC,(int)(25*screen_width_fraction))));
        Property2.setFont((new Font("Serif", Font.ITALIC,(int)(25*screen_width_fraction))));
        Property3.setFont((new Font("Serif", Font.ITALIC,(int)(25*screen_width_fraction))));
        Name.setFont((new Font("Curlz MT", Font.BOLD, (int)(50*screen_width_fraction))));
        

                //      Set  Locations

        Menu1[0].setLocation(400*screen_width_fraction,20*screen_height_fraction);
        Menu1[1].setLocation(1250*screen_width_fraction,20*screen_height_fraction);
        Menu1[2].setLocation(400*screen_width_fraction,720*screen_height_fraction);
        Menu1[3].setLocation(1200*screen_width_fraction,720*screen_height_fraction);
        
        Main_image.setLocation(600*screen_width_fraction,220*screen_height_fraction);
        Main.setLocation(600*screen_width_fraction, 220*screen_height_fraction);
        
        back.setLocation(0,0);
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
        
        back.setColor(Color.MAGENTA);
        back.setFilled(true);
        
        Topic.setColor(Color.blue);
        
        Description.setColor(Color.CYAN);
        Property1.setColor(Color.CYAN);
        Property2.setColor(Color.CYAN);
        Property3.setColor(Color.CYAN);
        Name.setColor(Color.magenta);
        
        //p.setFillColor(Color.yellow);
        //p.setFilled(true);
                
                //      Add  Everything on the screen




        add(back);
        add(Main);
        add(Topic);
        add(Main_image);
        add(Description);
        add(Property1);
        add(Property2);
        add(Property3);
        add(Name);
        for (GImage m : Menu1) {
            add(m);
        }
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

        for (int i=0;i<Menu1.length;i++) {

            if(Menu1[i].contains(e.getX(),e.getY())) {

                Main.setColor(ducks[i].getColor());

                Name.setLabel(ducks[i].getName());

                Property1.setLabel("Speed");
                Property2.setLabel("Hit Points");
                Property3.setLabel("Randomability");

                Description.setLabel(ducks[i].getBio());
                Main_image.setImage(ducks[i].getPicLocation());
                Main_image.setSize(600*screen_width_fraction, 450*screen_height_fraction);

                for (int j=0;j<ducks[i].getProperty().length;j++) {
                    for (int k=0;k<ducks[i].getProperty()[j];k++) {
                        add(star[j*5+k]);
                    }
                }
            }

        }
        
    }
            
}
