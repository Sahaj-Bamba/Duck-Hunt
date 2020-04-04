package DuckHunt.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sahaj
 */

/**
 * Contain the I/O of the client.
 */
public class Client {
	
	private String name;
	private String groupName;
	private boolean isOwner;
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	/**
	 * Creates a new client and a connection to the server using ip and port provided.
	 * @param ip The ip address of the server.
	 * @param port The port of the server
	 */
	public Client(String ip, int port) {
		try {
			this.socket = new Socket(ip,port);
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.name = "Gamer";
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name,String groupName) {
		this.name = name;
		this.groupName = groupName;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public boolean isIsOwner() {
		return isOwner;
	}
	
	public void makeOwner(){
		isOwner = true;
	}
	
	/**
	 * Sends a Message to server
	 * @param Message The Object which needs to be sent.
	 * @return True if the message is sent successfully.
	 */
	public boolean sendMessage(Object Message){
		try {
			System.out.println(Message.toString());
			this.objectOutputStream.writeObject(Message);
			return true;
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	
	/**
	 * Receive an Object from the sender.
	 * @return The received object.
	 */
	public Object receiveMessage(){
		try {
			Object obj = this.objectInputStream.readObject();
			System.out.println(obj.toString());
			return obj;
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	
	
	
}

