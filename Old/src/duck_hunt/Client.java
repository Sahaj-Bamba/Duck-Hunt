package duck_hunt;

import duck_hunt.Restart.Menu;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import static duck_hunt.Restart.Duck_hunt.WhoAmI;
import static duck_hunt.Restart.Duck_hunt.WhoAmICalled;

public class Client extends Thread implements Serializable {

    public String IP;
    public int port;
    public String name;
    
    @Override
    public void run() {
    
        try {
            Socket socket = new Socket(IP, port);
            System.out.println("Client created.");
            WhoAmI = "Client";
            WhoAmICalled = this.name;

           // new Thread(new Menu()).start();
           new Thread(new Menu()).start();
          // new Thread(new ChatWindow()).start();
            System.out.println("chk");
//            client_hear = new Thread(new HandleClient_hear(socket , this.name));
//            client_speak = new Thread(new HandleClient_speak(socket , this.name));
//            client_speak.start();
//            client_hear.start();
            System.out.println("chk");
           // new Thread(new ChatWindow()).start();
          // new Thread(new ChatWindow()).start();
             //  new Thread(new Menu()).start();
while(true){
                  System.out.print(".");
              }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
