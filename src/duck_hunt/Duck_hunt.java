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
    public static Server server_read = new Server(1);
    public static Server server_write = new Server(1);
    public static Client client_read = new Client();
    public static Client client_write = new Client();
    public static Menu menu = new Menu();
    public static ChatWindow chatwindow = new ChatWindow();
    
    public static void main(String[] args) {
        Duck_hunt.animation();
        Menu menu = new Menu();
        menu.start();
    }
    public static void animation(){
        
    }
    
}
