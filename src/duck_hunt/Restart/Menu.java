/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt.Restart;

import acm.program.DialogProgram;
import duck_hunt.Restart.extra.Credits;
import duck_hunt.Restart.extra.Instructions;
import duck_hunt.Restart.extra.Settings;
import duck_hunt.Restart.extra.almnac;

/**
 *
 * @author Sahaj Bamba
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

/*
        Connect connect = new Connect();                                      // Connect Window
        setting_1 temp5=new setting_1();                                      // Settings Window
        String [] args = null;
*/

		//while (true) {
			while (true) {
				cnt = readInt("\n\n\tDUCK HUNT \n\n1- Single Player \n2- Chat\n3- Connect\n4-Settings\n5- Almnac \n6- How to Play\n7- Credits \n8- Login/Signup\n9- Quit");
				if (cnt > 0 && cnt < 10) {
					// TODO: 24/12/19 This will be used to make some things only accessable when login function is available.
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

			switch (cnt) {
				case 1:
					new StartGame().start();
					break;
				case 2: // TODO: 24/12/19 Chat
					break;
				case 3: // TODO: 24/12/19 Connect
					break;
				case 4:
					new Settings().start();
					break;
				case 5:
					new almnac().start();
					break;
				case 6:
					new Instructions().start();
					break;
				case 7:
					new Credits().start();
					break;
				case 8:
					// TODO: 24/12/19 Login/Signup
    /*
                    String[] args;
                    new LoginForm().main(args);
                        break;
    */              break;
				case 9:
					GameController.exit(0);
					break;
			}
			System.out.println("Got back");
			while (true);
		//}
	}
}