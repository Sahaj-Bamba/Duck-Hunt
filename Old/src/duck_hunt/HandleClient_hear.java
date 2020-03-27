/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static duck_hunt.Restart.Duck_hunt.chatwindow;

/**
 *
 * @author admin
 */
public class HandleClient_hear implements Runnable  {

    private Socket socket = null;
    ObjectInputStream in = null;
    public String name;

    HandleClient_hear(Socket socket, String name) {
        this.socket = socket;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    @Override
    public void run() {
        
         while (true) {
            try {
                Message message = (Message) in.readObject();
                System.out.println("Message received");
                
                //      What to do after message is received
                
                chatwindow.MessageReceived(message);
                
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
         
    }

}



