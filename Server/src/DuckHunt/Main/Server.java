package DuckHunt.Main;

/**
 * @author Sahaj
 *
 */

import DuckHunt.Other.GameGlobalVariables;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Server Class accepts clients and call the handle client thread on each
 *
 */
public class Server {
	
	/**
	 * Blank default constructor
	 */
	public Server() {
	
	}
	
	private ServerSocket serverSocket;
	private Socket socket;
		//  Group name vs group class
	private HashMap<String,Group> group;
	
	
	/**
	 *  Create server socket and call run method.
	 *
	 */
	public void start(){
		
		
		new HandleUDP().start();
		try {
			serverSocket = new ServerSocket(GameGlobalVariables.getInstance().getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Server Socket Created");
		run();

	}
	
	/**
	 * Accepts clients and call handle thread for each one
	 *
	 */
	protected void run() {

		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Client socket accepted");
				Thread t = new Thread(new HandleClient(socket));
				System.out.println("Handle client created");
				t.start();
				System.out.println("Handle Thread Started");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}