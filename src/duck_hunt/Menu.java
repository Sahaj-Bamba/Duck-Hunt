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
        setting_1 temp5=new setting_1();                                      // Settings Window
        Instructions temp7 = new Instructions();                            // Instructions Window
        almnac almn = new almnac();
        String [] args = null;
        
        while (true)
        {
            cnt=readInt("\n\n\tDUCK HUNT \n\n1- Single Player \n2- Chat\n3- Connect\n4-Settings\n5- Almnac \n6- How to Play\n7- Credits \n8- Login/Signup\n9- Quit");
            if(cnt>0&&cnt<10){
                /*
                if(!(is_logged_in)){
                    if(cnt==6||cnt==7||cnt==8||cnt==10||cnt==11)
                        break;
                    else{
                        print("\tError \nYou must log in first to access these features .");
                        continue;
                    }
                }
                */
                break;
            }
            print("\tError \nYou Entered an incorrect value .");
        }
        
        switch(cnt)
        {
            case 1: temp1.start();
                break;
            case 2: 
            
            case 3: 
                
            
                new __Chatbox().main(args);
                break;
            case 4:temp5.start();
                break;
            case 5:almn.start();
                break;
            case 6:temp7.start();
                break;
            case 7:temp8.start();
                break;
            case 8:
                
             new LoginForm().main(args);
                    break;
            case 9: System.exit(0);
        }
    }
}