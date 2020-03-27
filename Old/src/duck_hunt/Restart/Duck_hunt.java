/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt.Restart;

import duck_hunt.ChatWindow;
import duck_hunt.Client;
import duck_hunt.Player;
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


public class Duck_hunt {

    static String labelText; 


        
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
