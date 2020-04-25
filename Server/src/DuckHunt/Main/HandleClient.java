package DuckHunt.Main;
/**
 * @author Sahaj
 *
 */

import DuckHunt.Constant.Request;
import DuckHunt.Constant.Responses;
import DuckHunt.Request.*;
import DuckHunt.Other.GameGlobalVariables;
import DuckHunt.Request.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet6Address;
import java.net.Socket;

public class HandleClient implements Runnable{

	private String clientName;
	private String groupName;
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private boolean left;
	private boolean goBack;
	
	/**
	 * It accepts the socket and create object input and output streams from it.
	 *
	 * @param socket The socket of the accepted client
	 */
	public HandleClient(Socket socket) {
		this.socket = socket;
		System.out.println(socket.getLocalAddress().toString());
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Streams created");
			left = false;
		} catch (IOException e) {
			System.out.println("Stream Creation error");
		}
	}
	
	/**
	 * The method where indirectly all the processing of Objects sent and receive occur.
	 */
	@Override
	public void run() {
		
		do {
			goBack=true;
			groupInit();
		}while(goBack && !left);
		
		if (left){
			return;
		}
		
		startWait();
		
		if (left){
			return;
		}
		
		gamePlay();
		
	}
	
	/**
	 * Initialises the group of the client.
	 * After this method is successfully completed the group of the client is decided.
	 */
	private void groupInit(){
		System.out.println("Group Initialising");
		try{
			
			GroupDetails groupDetails = (GroupDetails) objectInputStream.readObject();
			groupName = groupDetails.get_group_name();
			clientName = groupDetails.get_client_name();
			if (groupDetails.toString().equals(String.valueOf(Request.CREATEGROUP))) {
				objectOutputStream.writeObject(createGroup(groupDetails));
			}else if (groupDetails.toString().equals(String.valueOf(Request.JOINGROUP))) {
				objectOutputStream.writeObject(joinGroup(groupDetails));
			}else if (groupDetails.toString().equals(String.valueOf(Request.RANDOM))) {
				objectOutputStream.writeObject(randomGroup(groupDetails));
			}
			objectOutputStream.flush();
		} catch (Exception e) {
			System.out.println("Client Disconnected During group joining step");
			leftMember();
		}
		
	}
	
	/**
	 * Creates a new group and adds the client in it.
	 * @param groupDetails The details of client and group
	 * @return The response to send back to client
	 */
	private Object createGroup(GroupDetails groupDetails){
		
		if (GameGlobalVariables.getInstance().getGAMER().group_exist(groupDetails.get_group_name())){
			return new Response(Responses.ERROR,"Group already exists.");
		}
		GameGlobalVariables.getInstance().getGAMER().add_group(groupDetails.get_group_name(),groupDetails.get_password(),groupDetails.get_client_name());
		GameGlobalVariables.getInstance().getGAMER().add_client(groupDetails.get_group_name(),groupDetails.get_client_name(),this.objectOutputStream,socket.getInetAddress(),socket.getPort());
		goBack = false;
		System.out.println("Group Created");
		return new Response(Responses.OK,"Group Created");
	}
	
	/**
	 * Adds the client to the specified group .
	 * @param groupDetails The details of client and group
	 * @return The response to send back to client
	 */
	private Object joinGroup(GroupDetails groupDetails){
		
		if (!GameGlobalVariables.getInstance().getGAMER().group_exist(groupDetails.get_group_name())){
			return new Response(Responses.ERROR,"Group doesnot exists.");
		}
		
		if (!GameGlobalVariables.getInstance().getGAMER().check_pass(groupDetails.get_group_name(),groupDetails.get_password())){
			return new Response(Responses.ERROR,"Password doesnot match.");
		}
		
		if (GameGlobalVariables.getInstance().getGAMER().numOfClients(groupDetails.get_group_name()) > GameGlobalVariables.getInstance().getSIZE()){
			return new Response(Responses.ERROR,"Client Limit Reached.");
		}
		
		if (GameGlobalVariables.getInstance().getGAMER().client_exist(groupName,clientName)){
			return new Response(Responses.ERROR,"A gamer with that name already exist .");
		}
		
		GameGlobalVariables.getInstance().getGAMER().add_client(groupDetails.get_group_name(),groupDetails.get_client_name(),this.objectOutputStream, socket.getInetAddress(),socket.getPort());
		goBack = false;
		return new Response(Responses.OK,"Group Joined");
	}
	
	
	/**
	 * Adds the client to a random group.
	 * @param groupDetails The details of client
	 * @return The response to send back to client
	 */
	private Object randomGroup(GroupDetails groupDetails){
		String grp = GameGlobalVariables.getInstance().getGAMER().getGroup(groupDetails.get_client_name());
		if (grp.equals("")){
			return new Response(Responses.ERROR,"There are currently no free groups available. You can create your own though.");
		}
		GameGlobalVariables.getInstance().getGAMER().add_client(grp,groupDetails.get_client_name(),this.objectOutputStream,socket.getInetAddress(),socket.getPort());
		groupName = grp;
		goBack = false;
		return new Response(Responses.OK,grp);
	}
	
	/**
	 * Here the process from group joining to the start of game occur.
	 * Includes joining of new members, chatting and the epic message of the start of Game.
	 * @return false if it ends and the game is started.
	 */
	public void startWait(){
		
		try {
			GroupList groupList = (GroupList) objectInputStream.readObject();
			objectOutputStream.writeObject(new GroupList(GameGlobalVariables.getInstance().getGAMER().getClientList(groupList.getGroupName())));
			
			GameGlobalVariables.getInstance().getGAMER().send_message(new AddMember(clientName),groupName);
			
			while (true){
				Object obj = objectInputStream.readObject();
				
				if (obj.toString().equals(String.valueOf(Request.MESSAGE))){
					receivedMessage((Message)obj);
				}else if (obj.toString().equals(String.valueOf(Request.MEMBERADD))){
					addMember((AddMember)obj);
				}else if (obj.toString().equals(String.valueOf(Request.MEMBERREMOVE))){
					removeMember((RemoveMember)obj);
					left = true;
					return;
				}else if (obj.toString().equals(String.valueOf(Request.STARTGAME))){
					startGame((StartGame) obj);
				}else if (obj.toString().equals(String.valueOf(Request.MOVETOSTART))){
					return;
				}
			}
			
		} catch (IOException e) {
			System.out.println("Client io exception during group wait state");
			leftMember();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found during group wait state");
			leftMember();
		}
		
	}
	
	/**
	 * Start the game of the group with the size supplied in parameter
	 * @param startGame It contain size of the grid
	 */
	private void startGame(StartGame startGame) {
		GameGlobalVariables.getInstance().getGAMER().send_message(startGame,groupName);
		GameGlobalVariables.getInstance().getGAMER().startGame(groupName, startGame.getSize());
	}
	
	/**
	 * Does its work when a message object is received.
	 * It broadcasts the message in the entire group.
	 * @param obj The message object
	 */
	private void receivedMessage(Message obj) {
		GameGlobalVariables.getInstance().getGAMER().send_message(obj,groupName);
	}
	
	/**
	 * Tell the clients that a new member has joined there group.
	 * @param addMember details of new member
	 */
	private void addMember(AddMember addMember) {
		GameGlobalVariables.getInstance().getGAMER().send_message(addMember,groupName);
	}
	
	/**
	 * Remove a member from the group
	 * @param removeMember the details of the member to remove.
	 */
	private void removeMember(RemoveMember removeMember) {
		GameGlobalVariables.getInstance().getGAMER().remove_client(groupName,clientName);
		GameGlobalVariables.getInstance().getGAMER().send_message(removeMember,groupName);
	}
	
	private void leftMember(){
		left = true;
		removeMember(new RemoveMember(clientName));
//		GameGlobalVariables.getInstance().getGAMER().remove_client(groupName,clientName);
	}
	
	/**
	 * Controls all that happens during actual gameplay takes place
	 */
	private void gamePlay() {
		
		try {
			GroupList groupList = (GroupList) objectInputStream.readObject();
			objectOutputStream.writeObject(new GroupList(GameGlobalVariables.getInstance().getGAMER().getClientList(groupList.getGroupName())));
			objectOutputStream.flush();
			OpponentAddress opponentAddress = (OpponentAddress) objectInputStream.readObject();
			objectOutputStream.writeObject(new OpponentAddress(GameGlobalVariables.getInstance().getGAMER().getOpponentAddress(groupName,clientName),GameGlobalVariables.getInstance().getGAMER().getOpponentPort(groupName,clientName)));
			objectOutputStream.flush();
			
			GameGlobalVariables.getInstance().getGAMER().send_message(new NewRound(),groupName,clientName);
//			wait(100);
			GameGlobalVariables.getInstance().getGAMER().sendState(groupName,clientName);
			while (true){
				Object obj = objectInputStream.readObject();
				
				if (obj.toString().equals(String.valueOf(Request.MESSAGE))){
					receivedMessage((Message)obj);
				}else if (obj.toString().equals(String.valueOf(Request.MEMBERREMOVE))){
					removeMember((RemoveMember)obj);
					return;
				}else if (obj.toString().equals(String.valueOf(Request.MOVE))){
					GameGlobalVariables.getInstance().getGAMER().makeMove(groupName,clientName,(Move) obj);
				}else if (obj.toString().equals(String.valueOf(Request.GAMEOVER))){
					GameGlobalVariables.getInstance().getGAMER().remove_client(groupName,clientName);
					return;
				}
			}
			
		} catch (IOException e) {
			System.out.println("IO exception during gameplay");
			leftMember();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found during game play");
			leftMember();
		} catch (Exception e){
			e.printStackTrace();
//			System.out.println("Other Exception");
		}
		
	}
	
	
}
