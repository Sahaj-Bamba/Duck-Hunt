/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt.Restart.extra;

import acm.program.DialogProgram;
import duck_hunt.Restart.GameGlobalVariables;
import duck_hunt.Restart.Menu;

/**
 *
 * @author admin
 * 
 *  It opens up a dialog window
 * 
 * It helps in changing various setting of the game like game mode and all.
 * 
 */
public class Settings extends DialogProgram{

    public Settings() {
    }

    @Override
    public void run(){
        int cnt;
        do{
            cnt=readInt("\n\nEnter the number of birds in each round between 5 and 100");
        }while(cnt<5||cnt>100);
        GameGlobalVariables.getInstance().setDucksCount(cnt);
        System.out.println("Value of Ducks Count updated to "+cnt);
        //exit();
        new Menu().run();
    }
}
