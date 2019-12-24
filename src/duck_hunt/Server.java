package duck_hunt;

import duck_hunt.Restart.Menu;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import static duck_hunt.Restart.Duck_hunt.*;

public class Server extends Thread implements Serializable{
        // s tell if server is on or not 
    
    private boolean is_on ;
    public int port;
    public String name;
    public String IP;
    
    public Server(int Port){
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
            this.is_on = false;
            error.run(" Something went wrong please try again .");
            //menu.start();
            System.out.print("njvsdkvsdnjsdv dslvknkjdsvnjhdsbvhjbdskjvbsajhvhgbxchfgvsdcgfvgsdcvjhcsvjkscvjdskfcvkuzsdvcsghn");
            e.printStackTrace();
            return;
        }
        /*
        WhoAmI = "Server";
        WhoAmICalled = this.name;
        chatwindow.start();
        */
//      enclose this in while loop for multiple clients handelling        

            try {
                socket = serverSocket.accept();
                WhoAmI = "Server";
                WhoAmICalled = this.name;
                
              //  new Thread(new Menu()).start();
           new Thread(new Menu()).start();
              //new Thread(new ChatWindow()).start();
//                server_hear = new Thread(new HandleClient_hear(socket , this.name));
//                server_speak = new Thread(new HandleClient_speak(socket , this.name));
//                server_speak.start();
//                server_hear.start();
               // new Thread(new ChatWindow()).start();
            //   new Thread(new ChatWindow()).start();
               new Thread(new Menu()).start();

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

    }
}
