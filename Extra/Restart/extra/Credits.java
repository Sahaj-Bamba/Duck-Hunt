/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt.Restart.extra;
import acm.program.DialogProgram;
import duck_hunt.Restart.Menu;

/**
 *
 * @author Sahaj Bamba
 * 
 * This is the credits Section.
 * 
 * It displays credits in a dialogue window.
 */
public class Credits extends DialogProgram{
    
    @Override
    public void run()
    {
        println("             Made By     \n\n         SAHAJ BAMBA");
        new Menu().start();
    }
}

