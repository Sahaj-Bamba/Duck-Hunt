package DuckHunt.Main;

/**
 * @author Sahaj
 */

import DuckHunt.Constant.MessageType;
import DuckHunt.Constant.MoveType;
import DuckHunt.Request.*;

import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The Group class contain name and password and links to various clients
 *
 */
public class Group {

	private String password;
	private String name;
	private String leader;
	private HashMap<String,Client> clients;
	private int size;
	private Game game;
	
	/**
	 * Creates a group using name and password
	 * @param name name of the group. Be sure that a group with that name doesn't already exists
	 * @param password the password of the group
	 */
	public Group(String name, String password, String leader){
		clients = new HashMap<String, Client>();
		this.name = name;
		this.password = password;
		this.leader = leader;
	}
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * Give a list of all the clients present in a group.
	 * @return The list of all clients of a group.
	 */
	public String[] getClientList(){
		String[] tmp = new String[8];
		int i = 0;
		Iterator group = clients.entrySet().iterator();
		while (group.hasNext()){
			Map.Entry g = (Map.Entry)group.next();
			tmp[i++] = (String) g.getKey();
		}
		while(i<8){
			tmp[i++]="";
		}
		return tmp;
	}
	
	/**
	 * Adds the client to the group
	 * @param name the name of the client
	 * @param objectOutputStream the object output stream of the client
	 */
	public void add_client(String name, ObjectOutputStream objectOutputStream, InetAddress inetAddress, int port){
		clients.put(name, new Client(name,objectOutputStream,inetAddress, port));
	}
	
	/**
	 * Removes the client from the group
	 * @param name The name of client to remove
	 * @return true if the client has been successfully removed
	 */
	public boolean remove_client(String name){
//		if (game != null){
//			String[] st = getClientList();
//			int i=0;
//			for (String s: st) {
//				if (s.equals(name)) {
//					break;
//				}
//				i++;
//			}
//			game.remove(i);
//		}
		if (client_exist(name)) {
			clients.remove(name);
			if (game != null){
				if (numOfClients()==1){
					onlyOnePlayerInGame();
				}
			}
			return true;
		}
		return false;
	}
	
	// @TODO: 3/14/2020 Problems in logic of remove all check latter
	/**
	 *
	 * @param name
	 * @return
	 */
	public boolean remove_all(String name){
		boolean flag = true;
		Iterator client = clients.entrySet().iterator();
		while (client.hasNext()){
			Map.Entry g = (Map.Entry)client.next();
			flag = flag & this.remove_client(((Client)g.getValue()).getName());
		}
		return flag;
	}
	
	/**
	 * Tell if the given client exist in this group or not.
	 * @param name Name of client
	 * @return true if client exists
	 */
	public boolean client_exist(String name){
		return clients.containsKey(name);
	}
	
	/**
	 * Send message to the all the clients
	 * @param message The object to send
	 * @return true if message is sent to all the clients
	 */
	public boolean send_message(Object message){

		boolean flag = true;
		Iterator client = clients.entrySet().iterator();
		while (client.hasNext()){
			Map.Entry g = (Map.Entry)client.next();
			flag = flag & ((Client)g.getValue()).send_message(message);
		}

		return flag;
	}
	
	/**
	 * Send message to a particular client
	 * @param message The object to send
	 * @param clientName The name of client
	 * @return True if the message is sent successfully. False otherwise.
	 */
	public boolean send_message(Object message, String clientName){
		if (client_exist(clientName)){
			return clients.get(clientName).send_message(message);
		}
		return false;
	}
	
	public int numOfClients(){
		int x=0;
		Iterator client = clients.entrySet().iterator();
		while (client.hasNext()){
			Map.Entry g = (Map.Entry)client.next();
			x++;
		}
		return x;
	}
	
	public void startGame(int size) {
		game = new Game(size,getClientList());
	}
	
	public void makeMove(Move move,String clientName){
		game.makeMove(move,clientName);
		if (move.getMoveType().equals(MoveType.Left)){
		
		}else if (move.getMoveType().equals(MoveType.Damage)){
			
			Iterator client = clients.entrySet().iterator();
			while (client.hasNext()){
				Map.Entry g = (Map.Entry)client.next();
				if (((String)g.getKey()).equals(clientName)){
				
				}else{
					((Client)g.getValue()).send_message(move);
				}
			}
			
		}
		if (game.getMessage().equals("")){
		
		}else{
			send_message(new Message("Refree","",game.getMessage(), MessageType.UserToGroup));
			game.setMessage("");
			ScoreBoard s = new ScoreBoard();
			s.setScore(game.getScore());
			send_message(s);
		}
		
		if (game.checkOver()){
			send_message(new NewRound());
			game.newRound();
			Date date = new Date();
			while( (new Date().getTime() - date.getTime()) < 500);
			send_message(game.getGameState());
		}
		
		if (game.isOver()){
			
			// @TODO: 4/6/2020 OVER TASK
			
		}else{
//			send_message(game.getGameState());
		}
		
		
		
//		if(game.makeMove(move)){
//			send_message(new GameOver(game.getWinner()));
//			return;
//		}
//		send_message(game.getGameState());
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				System.out.println(game.getGameState().getHColor(i, j));
//			}
//		}
//		send_message(move);

	}

	
	private void onlyOnePlayerInGame() {
		send_message(new GameOver(getClientList()[0],"Others have forfeited the game"));
		game = null;
	}
	
	public void sendState(String client){
		send_message(game.getGameState(),client);
	}
	
	public byte[] getOpponentAddress(String clientName) {
		byte[] st = null;
		Iterator client = clients.entrySet().iterator();
		while (client.hasNext()){
			Map.Entry g = (Map.Entry)client.next();
			if( ((String)g.getKey()).equals(clientName)){
			
			}else{
				return ((Client)(g.getValue())).getInetAddress().getAddress();
			}
		}
		return st;
	}
	
	public int getOpponentPort(String clientName) {
		int st = 0;
		Iterator client = clients.entrySet().iterator();
		while (client.hasNext()){
			Map.Entry g = (Map.Entry)client.next();
			if( ((String)g.getKey()).equals(clientName)){
			
			}else{
				return ((Client)(g.getValue())).getPort();
			}
		}
		return st;
	}
	
	public void sendUDP(String clientName, DatagramPacket packet) {
		clients.get(clientName).setAddress(packet.getAddress(),packet.getPort());
		Iterator client = clients.entrySet().iterator();
		while (client.hasNext()){
			Map.Entry g = (Map.Entry)client.next();
			if( ((String)g.getKey()).equals(clientName)){
			
			}else{
				((Client)(g.getValue())).sendUDP(packet);
			}
		}
	}
	
	public void setAddress(String clientName, InetAddress inetAddress,int port){
		clients.get(clientName).setAddress(inetAddress,port);
	}
	
	public int getPort(String clientName){
		return clients.get(clientName).getPort();
	}
	
}






