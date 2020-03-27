/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt.Restart.backups;

import duck_hunt.ChatWindow;
import duck_hunt.Client;
import duck_hunt.Player;
import duck_hunt.Restart.Menu;
import duck_hunt.Restart.utilities.Error;
import duck_hunt.Server;

/**
 *
 * @author Sahaj Bamba
 * 
 * The main runnable class containing main function.
 * 
 * This Class first displays an Animation the front start game animation,
 * Then it creates a Menu object and calls its run function which displays menu and everything proceeds.
 *
 *
 * This class also sets various Global parameters.
 */


public class Duck_huntbk {

    static String labelText; 







        //public static Player gamer = new Player();

        //          Main Game Variables
        
        //          Ducks
        //      float angle , float speed , int randomability , int HP , int LT , String b , String p , String name

        /*
        public static Ducks Red = new Ducks(1.0, 7.0, 6 * 1000, 80, 30 * 1000, "On an island occupied by the cheeriest birds imaginable, Red is a bit of an outcast. His cynical outlook has kept him at odds with the other birds,  but a bit of cynicism might be just what they n" + "eed", "Images\\Menu_1.png", "The Red", 10, 0);
        public static Ducks Blue = new Ducks(1.0, 5.0, 4 * 1000, 50, 25 * 1000, "Why am I blue people usually ask me and my answer to them is because I am so cool. I am not bragging I really am cool. I listen to none since I am better then them all. I keep moving and changing directions as much as I want. That means almost infinitely. ", "Images\\Menu_3.png", "Blues", 10, 2);
        public static Ducks Yellow = new Ducks(1.0, 10.0, 8 * 1000, 100, 40 * 1000, "Chuck is constantly running at 100 mph and that includes his mouth as well . His impulsive nature often gets him into trouble. Lukily he is fast enough to escape from the situation. Chuck says I am not fast I am just walking its you who is slow . ", "Images\\Menu_2.png", "Chuck", 10, 1);
        public static Ducks Black = new Ducks(1.0, 12.0, 2, 2, 40 * 1000, "Generally Speaking Bomb is the Chillest Bird you will ever meet. However get him worked up and he has a tendency to go Off - explosively speaking. This can have its advantages, but Bomb struggles to control his power.", "Images\\Menu_4.png", "Bomb", 10, 3);
        */
        
        //          Guns
        /*
        public int Delay;                                                   //      frames
        public int Reload;                                                  //      frames
        public int Damage;                                                  //      integer
        public int Clip_size;                                               //      number of bullets    
        public String pic_location;
        */

        /*
        public static Guns ak = new Guns(200, 1, 25, 1, "Images\\Images\\Guns\\1", "Images\\Songs\\1.wav");
        public static Guns shotgun = new Guns(700, 1, 40, 1, "Images\\Images\\Guns\\2", "Images\\Songs\\2.wav");
        public static Guns laser = new Guns(10, 1, 5, 1, "Images\\Images\\Guns\\3", "Images\\Songs\\3.wav");
        public static Guns sniper = new Guns(1000, 1, 60, 1, "Images\\Images\\Guns\\4", "Images\\Songs\\4.wav");
        */
        
        //      Screen Size for system independence
        

        
        //  Different static variables containing data which is being used across various windows.
        public static int flag_speak = 0;
        public static Error error = new Error();
        public static Server server = new Server(1);
        public static Thread server_hear = null;
        public static Thread server_speak = null;
        public static Client client = new Client();
        public static Thread client_hear = null;
        public static Thread client_speak = null;
        public static ChatWindow chatwindow = new ChatWindow();
        public static String WhoAmI;                                     //  Server/Client
        public static String WhoAmICalled;                               //  My name
        public static String ChattedMessage;


        //      main game real use variables
    
        public static boolean is_logged_in = false;
        // public static Sql_query_executer sqe = new Sql_query_executer("root", "Gen123@", "jdbc:mysql://localhost:3306/duckhunt");
        public static Player gamer = new Player();
     
        
        private void initialise() {

        }



        
        
    public static void main(String[] args) {
            
            //  For a Good Start.
        System.out.println("hello world");
            

        
        new Menu().start();

    }
    
    public static void animation(){
        // TODO: 24/12/19 Create a nice animation effect for the game if possible here.         
    }
    
}
