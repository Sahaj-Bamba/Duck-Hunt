package duck_hunt;  

import static duck_hunt.Duck_hunt.server_hear;
import static duck_hunt.Duck_hunt.server_speak;
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
                server_hear = new Thread(new HandleClient_hear(socket , this.name));
                server_speak = new Thread(new HandleClient_speak(socket , this.name));
                server_speak.start();
                server_hear.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
