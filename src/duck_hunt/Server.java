package duck_hunt;

import static duck_hunt.Duck_hunt.chatwindow;
import static duck_hunt.Duck_hunt.error;
import static duck_hunt.Duck_hunt.menu;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
        // s tell if server is on or not 
    
    private boolean is_on ;
    public int port;
    public String name;
    public String IP;
    
    Server(int Port){
        this.port = Port;
    }
    
    public void close(){    
        is_on = false;
    }   
    
    @Override
    public void run() {
        this.is_on = true;
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("Server started");
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            error.run(" Something went wrong please try again .");
            menu.start();
            e.printStackTrace();
            return;
        }
        
        new Thread(chatwindow).start();
        
        while (true) {
            try {
                socket = serverSocket.accept();
                Thread t = new Thread(new HandleClient(socket));
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
