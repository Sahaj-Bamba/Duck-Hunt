/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt;

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

    //  Different static variables containing data which is being used across various windows.
    
    public static Error error = new Error();    
    public static Server server = new Server(1);
    public static Thread server_hear = null;
    public static Thread server_speak = null;    
    public static Client client = new Client();
    public static Thread client_hear = null;
    public static Thread client_speak = null;    
    public static Menu menu = new Menu();
    public static ChatWindow chatwindow = new ChatWindow();
    
    public static void main(String[] args) {
        Duck_hunt.animation();
        Menu menu = new Menu();
        menu.run();
    }
    public static void animation(){
        
    }
    
}
