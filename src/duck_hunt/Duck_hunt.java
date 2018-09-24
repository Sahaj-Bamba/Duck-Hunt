/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt;

import java.awt.Toolkit;

/**
 *
 * @author admin
 * 
 * The main runnable class containing main function.
 * 
 * This Class first displays an Animation the front start game animation.
 * Then it creates a Menu object and calls its run function which displays menu and everything proceeds
 */


public class Duck_hunt {

    static String labelText; 
    
    Duck_hunt(){
    //    labelText = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 10, );
           
    }
    
    public static Player gamer =null;
    
    //          Main Game Variables
    
    //          Ducks
    
    
        //float angle , float speed , int randomability , int HP , int LT , String b , String p , String name

    public static Ducks Red = new Ducks(1.0,2.0,2,2,3,"On an island occupied by the cheeriest birds imaginable, Red is a bit of an outcast. His cynical outlook has kept him at odds with the other birds,  but a bit of cynicism might be just what they need","Images\\Menu_1.jpg","The Red",10);
    public static Ducks Blue = new Ducks(1.0,4.0,300,20,720,"Why am I blue people usually ask me and my answer to them is because I am so cool. I am not bragging I really am cool. I listen to none since I am better then them all. I keep moving and changing directions as much as I want. That means almost infinitely. ","Images\\Menu_3.jpg","Blues",10);
    public static Ducks Yellow = new Ducks(1.0,2.0,2,2,3,"Chuck is <br> constantly running at 100 mph and that includes his mouth as well . His impulsive nature often gets him into trouble. Lukily he is fast enough to escape from the situation. Chuck says I am not fast I am just walking its you who is slow . ","Images\\Menu_2.png","Chuck",10);
    public static Ducks Black = new Ducks(1.0,2.0,2,2,3,"Generally Speaking Bomb is the Chillest Bird you will ever meet. However get him worked up and he has a tendency to go Off - explosively speaking. This can have its advantages, but Bomb struggles to control his power.","Images\\Menu_4.jpg","Bomb",10);


    
    //          Guns
    
    /*
    public int Delay;                                                   //      frames
    public int Reload;                                                  //      frames
    public int Damage;                                                  //      integer
    public int Clip_size;                                               //      number of bullets    
    public String pic_location;
    */
    
    public static Guns ak = new Guns(1,1,1,1,"Images\\Images\\Guns\\1","Images\\Songs\\1.wav");    
    public static Guns shotgun = new Guns(1,1,1,1,"Images\\Images\\Guns\\2","Images\\Songs\\2.wav");        
    public static Guns laser = new Guns(1,1,1,1,"Images\\Images\\Guns\\3","Images\\Songs\\3.wav");    
    public static Guns sniper = new Guns(1,1,1,1,"Images\\Images\\Guns\\4","Images\\Songs\\4.wav");    
    
    //      Screen Size for system independence
    
        
        public static double default_screen_height; 
        public static double default_screen_width;
    
        public static double screen_height;
        public static double screen_width;
    
        public static double screen_height_fraction;
        public static double screen_width_fraction;
    
    //  Different static variables containing data which is being used across various windows.
    public static int flag_speak =0;
    public static Error error = new Error();    
    public static Server server = new Server(1);
    public static Thread server_hear = null;
    public static Thread server_speak = null;    
    public static Client client = new Client();
    public static Thread client_hear = null;
    public static Thread client_speak = null;    
    public static Menu menu = new Menu();
    public static ChatWindow chatwindow = new ChatWindow();
    public static String WhoAmI ;                                     //  Server/Client
    public static String WhoAmICalled ;                               //  My name
    public static String ChattedMessage;
    
    public static void main(String[] args) {
        
        /*
                All The work of the screen size adjusting screen resolution independence
        */
        
            
        default_screen_height = 1080.0;
        default_screen_width = 1920.0;
    
        screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
        screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    
        screen_height_fraction = screen_height/default_screen_height;
        screen_width_fraction = screen_width/default_screen_width;
        

        
        Duck_hunt.animation();
        Menu menu = new Menu();
        menu.run();
    }
    
    public static void animation(){
        
    }
    
}
