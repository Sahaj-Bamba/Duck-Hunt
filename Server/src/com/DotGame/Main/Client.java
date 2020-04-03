package com.DotGame.Main;

/**
 * @author Sahaj
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The bottom level units clients . One instance for each client. Used for actual writing to clients.
 *
 */
public class Client {

	private String name;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	
	/**
	 * Create an instance of the Client
	 * @param name The name of the Client. Be sure that 2 clients do not have same name.
	 * @param objectOutputStream The output stream used for writing objects.
	 */
	public Client(String name, ObjectOutputStream objectOutputStream){
		this.name = name;
		this.objectOutputStream = objectOutputStream;
		try {
			FileWriter myWriter = new FileWriter("Gamers.txt",true);
			myWriter.write(name + "\n");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends the message to the client
	 * @param message the object to send
	 * @return true if message is sent successfully else false
	 */
	public boolean send_message(Object message) {
		System.out.println(message.toString());
		try {
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Get the name of the client
	 * @return the name of the client
	 */
	public String getName(){
		return this.name;
	}
	
}