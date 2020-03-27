/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt;

//import static chatbox.Chatbox.chatwindow;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author admin
 */


public class __Client implements Serializable {

    public String IP;
    public int port;
    public String name;
    
    public __Client(String name,String IP,int port){
        this.name = name;
        this.IP = IP;
        this.port = port;
    }
    
    
    public void run() {
    
        try {
            
            Socket socket = new Socket(this.IP, this.port);
            System.out.println("Client created.");
            
        
               // new Thread(new Listen(socket,this.name)).start();
               // System.out.println("Client created. ");
               // chatwindow.start(socket);
               String []a = null;
               new __ChatWindow(socket);
                  System.out.println("hreighoeh");     
                
                
/*
            WhoAmI = "Client";
            WhoAmICalled = this.name;
*/
           // new Thread(new Menu()).start();
//           new Thread(new Menu()).start();
          // new Thread(new ChatWindow()).start();
//            System.out.println("chk");
//            client_hear = new Thread(new HandleClient_hear(socket , this.name));
//            client_speak = new Thread(new HandleClient_speak(socket , this.name));
//            client_speak.start();
//            client_hear.start();
//            System.out.println("chk");
           // new Thread(new ChatWindow()).start();
          // new Thread(new ChatWindow()).start();
             //  new Thread(new Menu()).start();
             
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
