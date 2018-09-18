/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

/**
 *
 * @author user
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;
public class playwav extends Thread
{
    private InputStream in;
    private AudioStream au;
    private String song;

    public playwav(String s) {
        try {
            this.in = new FileInputStream(s);
            this.au= new AudioStream(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(playwav.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(playwav.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    public void run(){
        
        while(true){
        AudioPlayer.player.start(au);
        }
        
    }

}
    
