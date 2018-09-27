/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt;

//import static chatbox.Chatbox.chatwindow;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */

public class __Listen implements Serializable,Runnable {
    
    private Socket socket = null;
    ObjectInputStream in = null;
    public String name;
    public __ChatWindow chatWindow = null;

    __Listen (Socket socket, String name, __ChatWindow cw) {
        
        this.socket = socket;
        this.chatWindow = cw;
        try {
            System.out.println("in stream creation");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in stream created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.name = name;
    
    }


    @Override
    public void run() {
        
         while (true) {
            try {
                __MSG x = new __MSG();
                
                String txt = null;
                try {
                    x = (__MSG) in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(__Listen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Message received");
                
                //      What to do after message is received
                
                this.chatWindow.MessageReceived(x.txt);
                
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }    
    }
}

