/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;
import acm.program.DialogProgram;
/**
 *
 * @author admin
 * 
 * This is the credits Section.
 * 
 * It displays credits in a dialogue window.
 */
public class Credits extends DialogProgram{
    
    @Override
    public void run()
    {
        Menu menu = new Menu();
        println("             Made By     \n\n         SAHAJ BAMBA\n   VAISHNAV AGARWAL\n           VIVEK MEENA  ");
        menu.run();
    }
}

