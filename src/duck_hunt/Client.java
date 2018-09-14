package duck_hunt;  

import static duck_hunt.Duck_hunt.WhoAmICalled;
import static duck_hunt.Duck_hunt.WhoAmI;
import static duck_hunt.Duck_hunt.chatwindow;
import static duck_hunt.Duck_hunt.client_hear;
import static duck_hunt.Duck_hunt.client_speak;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

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
            chatwindow.start();
            client_hear = new Thread(new HandleClient_hear(socket , this.name));
            client_speak = new Thread(new HandleClient_speak(socket , this.name));
            client_speak.start();
            client_hear.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
