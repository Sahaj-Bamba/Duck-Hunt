package DuckHunt.Main;

/**
 * @author Sahaj
 *
 */


import DuckHunt.Other.GameGlobalVariables;
import DuckHunt.Request.Move;
import DuckHunt.Request.OpponentCameraFeed;

import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Owner links to various groups
 */
public class Owner {
	
	
	
	/**
	 * groups is hash map of group name vs group class objects
	 *
	 */
	private HashMap<String,Group> groups;
	
	/**
	 * Initialises groups hash map.
	 */
	public Owner(){
		groups = new HashMap<String, Group>();
	}
	
	/**
	 * Give a list of all the groups max number of groups is 100.
	 * @return The list of all the groups
	 */
	public String[] getGroupList(){
		String[] tmp = new String[100];
		int i = 0;
		Iterator group = groups.entrySet().iterator();
		while (group.hasNext()){
			Map.Entry g = (Map.Entry)group.next();
			tmp[i++] = (String) g.getKey();
		}
		return tmp;
	}
	
	/**
	 * Give a list of all the clients present in a specific group.
	 * @param name The name of the group.
	 * @return The list of all clients of a group.
	 */
	public String[] getClientList(String name){
		return groups.get(name).getClientList();
	}
	
	/**
	 * Creates a new group with given name and password. The name must be unique
	 * @param name The name of the group.
	 * @param password  The password of the group.
	 * @return True if the group is successfully created.
	 */
	public boolean add_group(String name,String password,String leader){
		if (group_exist(name)){
			return false;
		}
		groups.put(name,new Group(name,password,leader));
		return true;
	}
	
	/**
	 * Verify the password of the group
	 * @param name Name of the group
	 * @param pass Password to check
	 * @return True if the password matches
	 */
	public boolean check_pass(String name,String pass){
		if (groups.get(name).getPassword().equals(pass)){
			return true;
		}
		return false;
	}
	
	/**
	 * Delete a group
	 * @param name The name of the group.
	 */
	public void remove_group(String name){
		groups.remove(name);
	}
	
	/**
	 * Get the list of all the groups
	 * @return the Group List
	 */
	
	/**
	 * Adds a client to the group
	 * @param groupName The name of group. Be sure that the group exists.
	 * @param clientName The name of client
	 * @param objectOutputStream The object output stream of client
	 */
	public void add_client(String groupName, String clientName, ObjectOutputStream objectOutputStream, InetAddress inetAddress, int port){
		groups.get(groupName).add_client(clientName,objectOutputStream,inetAddress,port);
	}
	
	/**
	 * Remove a client from the group.
	 * @param groupName Group name. Be sure that the group exists.
	 * @param clientName Client name. Be sure that the client exists.
	 */
	public void remove_client(String groupName, String clientName){
		if (group_exist(groupName)){
			groups.get(groupName).remove_client(clientName);
		}
		if ( groups.get(groupName)!= null) {
			if (groups.get(groupName).getClientList()[0].equals("")) {
				remove_group(groupName);
			}
		}
	}
	
	/**
	 * Tell is the given group exists.
	 * @param name The name of the group.
	 * @return True if the group exists.
	 */
	public boolean group_exist(String name){
		return groups.containsKey(name);
	}
	
	/**
	 * Send message to everyone.
	 * @param message The message to send
	 * @return True if received by everyone.
	 */
	public boolean send_message(Object message){

		boolean flag = true;
		Iterator group = groups.entrySet().iterator();
		while (group.hasNext()){
			Map.Entry g = (Map.Entry)group.next();
			flag = flag & ((Group)g.getValue()).send_message(message);
		}

		return flag;
	}
	
	/**
	 * Send message to all the members of a group
	 * @param message Message Object
	 * @param groupName Name of the group
	 * @return true if received
	 */
	public boolean send_message(Object message,String groupName){
		if (group_exist(groupName)){
			return groups.get(groupName).send_message(message);
		}
		return false;
	}
	
	/**
	  * Send message to a particular client of a particular group
	 * @param message Object to send.
	 * @param groupName Name of group of client.
	 * @param clientName Name of client.
	 * @return True if received.
	 */
	public boolean send_message(Object message,String groupName, String clientName){
		if (group_exist(groupName)){
			return groups.get(groupName).send_message(message,clientName);
		}
		return false;
	}
	
	/**
	 * Check if a client with given name already exist
	 * @param group Name of the group
	 * @param name Client name to check
	 * @return True if client doesnot exist
	 */
	public boolean client_exist(String group,String name){
		if (groups.get(group).client_exist(name)){
			return true;
		}
		return false;
	}
	
	/**
	 * Start the game of the supplied group
	 * @param group Name of the group
	 * @param size The size of the board
	 */
	public void startGame(String group,int size){
		groups.get(group).startGame(size);
	}
	
	/**
	 * Make a move in the supplied group
	 * @param group Name of the group
	 * @param move The move to make
	 */
	public void makeMove(String group, String clientName,Move move){
		groups.get(group).makeMove(move,clientName);
	}
	
	/**
	 * Send the current state of the game
	 * may be used for synchronisation purposes
	 * @param group Name of the group
	 * @param client Name of client
	 */
	public void sendState(String group,String client){
		groups.get(group).sendState(client);
	}
	
	/**
	 * Send the number of clients in a group
	 * @param group Name of the group
	 * @return number of clients in a group
	 */
	public int numOfClients (String group){
		return groups.get(group).numOfClients();
	}
	
	/**
	 * remove all groups having no members.
	 */
	public void cleanse(){
		Iterator group = groups.entrySet().iterator();
		while (group.hasNext()){
			Map.Entry g = (Map.Entry)group.next();
			if ( ((Group)g.getValue()).getClientList()[0].equals("")){
				remove_group((String)g.getKey());
			}
		}
	}
	
	/**
	 * Get a group for match making
	 * @param client the name of client
	 * @return the name of geoup
	 */
	public String getGroup(String client){
		String ans = "";
		Iterator group = groups.entrySet().iterator();
		while (group.hasNext()){
			Map.Entry g = (Map.Entry)group.next();
			if( ((Group)g.getValue()).numOfClients() < GameGlobalVariables.getInstance().getSIZE()) {
				if (!(((Group)g.getValue()).client_exist(client))){
					ans = ((String) g.getKey());
				}
			}
		}
		return ans;
	}
	
	public byte[] getOpponentAddress(String groupName, String clientName) {
		return groups.get(groupName).getOpponentAddress(clientName);
	}
	
	public int getOpponentPort(String groupName, String clientName) {
		return groups.get(groupName).getOpponentPort(clientName);
	}
	
	public void sendUDP(String group, String client, DatagramPacket packet) {
		
		groups.get(group).sendUDP(client,packet);
		
	}
	
	public void setAddress(String groupName,String clientName, InetAddress inetAddress,int port){
		groups.get(groupName).setAddress(clientName,inetAddress,port);
	}
	
	public int getPort(String groupName, String clientName){
		return groups.get(groupName).getPort(clientName);
	}
	
}
