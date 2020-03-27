/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;


//import static chatbox.Chatbox.chatwindow;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import static duck_hunt.Restart.Duck_hunt.gamer;

public class __Server implements Serializable{
    
    private boolean is_on ;
    public int port;
    public String name;

    
    __Server(int Port,String name){
        this.port = Port;
        this.name = name;
    }
    
    public void close(){    
        is_on = false;
    }   
    
    
    public void run() {
        gamer.set_name(this.name);
        this.is_on = true;
        ServerSocket serverSocket = null;
        Socket socket;
        System.out.println("Server started");
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            
            /*this.is_on = false;
            error.run(" Something went wrong please try again .");
            menu.start();
            System.out.print("njvsdkvsdnjsdv dslvknkjdsvnjhdsbvhjbdskjvbsajhvhgbxchfgvsdcgfvgsdcvjhcsvjkscvjdskfcvkuzsdvcsghn");*/
            
            e.printStackTrace();
        }
        
        /*
        WhoAmI = "Server";
        WhoAmICalled = this.name;
        chatwindow.start();
        */

//      enclose this in while loop for multiple clients handelling        

            try {
                
                socket = serverSocket.accept();
                
               // new Thread(new Listen(socket,this.name)).start();
               
                System.out.println("Server listen on.");
               
                // new ChatWindow(socket).setVisible(true);
                //chatwindow.start(socket);
                
                String[] a =null;
                new __ChatWindow(socket);
                
                /*
                WhoAmI = "Server";
                WhoAmICalled = this.name;
                */
                
            //  new Thread(new Menu()).start();
            //  new Thread(new Menu()).start();
            //  new Thread(new ChatWindow()).start();
//                server_hear = new Thread(new HandleClient_hear(socket , this.name));
//                server_speak = new Thread(new HandleClient_speak(socket , this.name));
//                server_speak.start();
//                server_hear.start();
            //  new Thread(new ChatWindow()).start();
            //  new Thread(new ChatWindow()).start();
            //  new Thread(new Menu()).start();

     
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
