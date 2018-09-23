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
 * This class creates the main game menu which can be used to change settings , view credits , high score , start game etc.
 * 
 * It opens in a Dialogue Window
 * 
 * It creates objects for different windows and then start them when the user selects the respective option.
 */
    
public class Menu extends DialogProgram implements Runnable
{
    @Override
    public void run()
    {
        int cnt;                                                            // option selection controller 
        StartGame temp1=new StartGame();                                        // game Window
        Connect connect = new Connect();                                    // Connect Window
        Credits temp8=new Credits();                                        // Credits Window
        Settings temp5=new Settings();                                      // Settings Window
        Instructions temp7 = new Instructions();                            // Instructions Window
        almnac almn = new almnac();
        
        while (true)
        {
            cnt=readInt("\tDUCK HUNT \n\n1- Single Player \n2- Two Player\n3- Chat\n4- Connect\n5-Settings\n6- Almnac \n7- How to Play\n8- Credits \n9- Quit \n 10- Login/Signup");
            if(cnt>0&&cnt<11)
                break;
            print("\tError \nYou Entered an incorrect value .");
        }
        switch(cnt)
        {
            case 1: temp1.start();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:connect.start();
                break;
            case 5:temp5.start();
                break;
            case 6:almn.start();
                break;
            case 7:temp7.start();
                break;
            case 8:temp8.start();
                break;
            case 9:System.exit(0);
                break;
            case 10:String args[] = null;
                    new LoginForm().main(args);
                    break;
        }
    }
    
}