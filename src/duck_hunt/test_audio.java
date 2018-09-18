/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

import acm.program.GraphicsProgram;

/**
 *
 * @author user
 */
public class test_audio extends GraphicsProgram {
    playwav ac = new playwav();
    public static void main(String[] args){
        //this
    
    }
    
    public void play(){
    
        ac.start();
        pause(5000);
        while(true){
        ac.suspend();
        pause(2000);
        ac.resume();
        pause(2000);
        }
    }
}
