/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt.Restart.utilities;

/**
 *
 * @author Sahaj Bamba
 *
 * A utility to play wav files.
 *
 * In the constructor pass the address of the wav file . It will load and play it automatically . Run it like threads.
 *
 * ex new playwav("Images\\Songs\\buz.wav").start();
 *
 */

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
public class playwav extends Thread
{
    
    private InputStream in;
    private AudioStream au;

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

    @Override
    public void run(){
            AudioPlayer.player.start(au);
    }

}
    
