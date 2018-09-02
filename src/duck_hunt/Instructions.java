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
 */

public class Instructions extends DialogProgram
{
    @Override
    public void run()
    {
        Menu menu = new Menu();
        println("                                                                          INSTRUCTIONS \n\n\n1- This is a Duck Hunt Game Especially built for hunters to test there reflexes.\n 2- It consist of several rounds. \n3- In each round either 1 or 2 ducks will appear on the screen randomly moving in various directions. \n4- Your Task click on duck before it flies away .");
        menu.run();
    }
}
