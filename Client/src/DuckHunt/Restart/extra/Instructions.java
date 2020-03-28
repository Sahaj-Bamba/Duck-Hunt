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
 */

public class Instructions extends DialogProgram
{
    @Override
    public void run()
    {
        //  println("                                                                          INSTRUCTIONS \n\n\n1- This is a Duck Hunt Game Especially built for hunters to test there reflexes.\n 2- It consist of several rounds. \n3- In each round either 1 or 2 ducks will appear on the screen randomly moving in various directions. \n4- Your Task click on duck before it flies away .");
        println("                                                  INSTRUCTIONS                          \n\n\n Keep waiting and watch beautiful birds flying on the screen. \n\n They will kepp flying beautifully and swiftly in different directions. \n\n They are a lot smarter then you. \n\n Just sit around and relax watching them .\n\n When all the birds fly off you win.\n\n     NOTE -: This help section was brought to you by ducks.             \n\n");
        new Menu().start();
    }
}
