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

    public static void main(String[] args) {
        Duck_hunt.animation();
        Menu menu = new Menu();
        menu.run();
    }
    public static void animation(){
        
    }
    
}