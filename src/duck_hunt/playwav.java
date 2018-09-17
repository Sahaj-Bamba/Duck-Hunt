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
import static acm.util.JTFTools.pause;
import java.awt.Component;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import sun.audio.*;
public class playwav extends Thread
    {
    /*
    public playwav()
    {
        try
        {
            JFileChooser openf = new JFileChooser();
            Component j = null;
            openf.showOpenDialog(j);
            File f1 = openf.getSelectedFile();
            String st = f1.getPath();
            String st2 = f1.getCanonicalPath();
            
            System.out.println(st);
            System.out.println(st2);
    
            InputStream in = new FileInputStream(st);
            AudioStream au= new AudioStream(in);
            AudioPlayer.player.start(au);
        }
         catch(Exception e){}
        
        }
*/     
public void run()
     {
        
        try {
            play("C:\\Users\\user\\Desktop\\Duck-Hunt\\Song.wav");
        } catch (IOException ex) {
            Logger.getLogger(playwav.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
    static void play(String song) throws IOException{
            InputStream in = null;
            AudioStream au =null;
            in = new FileInputStream(song);
            au= new AudioStream(in);
            AudioPlayer.player.start(au);
        }
    
}
    
