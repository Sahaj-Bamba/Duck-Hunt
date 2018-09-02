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
    
public class Menu extends DialogProgram
{
    @Override
    public void run()
    {
        int cnt;
        Credits temp4=new Credits();
        Settings temp3=new Settings();
        Instructions temp2 = new Instructions();
        
        while (true)
        {
            cnt=readInt("\tDUCK HUNT \n\n1- Start Game\n2- How to Play\n3- Settings \n4- Credits \n5- Exit ");
            if(cnt>0&&cnt<6)
                break;
            print("\tError \nYou Entered an incorrect value .");
        }
        switch(cnt)
        {
            case 1:new StartGame().start();
                break;
            case 2:temp2.run();
                break;
            case 3:temp3.run();
                break;
            case 4:temp4.run();
                break;
            case 5:System.exit(0);
                break;
        }
    }
    
}