package duck_hunt;  

import static duck_hunt.ChatWindow.Client_in;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {

    public String IP;
    public int port;
    public String name;
    
    @Override
    public void run() {
            
        /*
            char c;
            while( (c = in.read()) != -1){
                //do whatever with c
            }
        */    
        
        try {
            Socket socket = new Socket(IP, port);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(Client_in));
            System.out.println("Client created.");
            new Client().sendMessage(socket, bufferedReader, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage
            (Socket socket, BufferedReader bufferedReader, String name)
            throws IOException {
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(socket.getOutputStream());
        while (true) {
            System.out.println("Press 1 to quit");
            int ch = Integer.parseInt(bufferedReader.readLine());
/*
            if (ch == 1) {
                break;
            }
*/
            System.out.println("Enter message");
            String text = bufferedReader.readLine();
            String from = "Client - " + name;
            Message message = new Message(text, from, "Server");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        }
    }
}
