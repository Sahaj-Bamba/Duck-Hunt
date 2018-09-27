/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;
import static duck_hunt.StartGame.number_of_birds;
import acm.program.DialogProgram;

/**
 *
 * @author admin
 */
public class setting_1 extends DialogProgram{
    int cnt;
    @Override
    public void run(){
        do{
            cnt=readInt("\n\nEnter the number of birds in each round between 5 and 20");
        }while(cnt<5||cnt>20);
        number_of_birds = cnt;
        new Menu().run();
    }
    
}
