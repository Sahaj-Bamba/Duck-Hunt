/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;
import static duck_hunt.Duck_hunt.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class HandleClient_speak implements Runnable {

    //      Making necessary streams
    
    private Socket socket = null;
    ObjectOutputStream out = null;
    public String name = null;
    
    public HandleClient_speak (Socket socket , String name) {
        this.socket = socket;
        this.name = name;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //  nothing to do directly only does work when prompted from the window
    
    @Override
    public void run() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //         This function will Send Message
    
    public void send_msg( String text) throws IOException{
    
        String from = this.name + " : ";
        Message message = new Message(text, from, "Server");
        out.writeObject(message);
        out.flush();
    
    }
        
}



    
