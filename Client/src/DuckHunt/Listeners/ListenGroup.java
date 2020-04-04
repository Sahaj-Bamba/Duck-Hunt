package DuckHunt.Listeners;

import DuckHunt.Constant.Request;
import DuckHunt.GUI.GroupView;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Request.*;

/**
 *
 * @author Sahaj
 */
public class ListenGroup implements Runnable{
	
	private GroupView groupView;
	
	public ListenGroup(GroupView groupView) {
		this.groupView = groupView;
	}
	
	@Override
	public void run(){
		
		while (true){
			
			Object obj = GameGlobalVariables.getInstance().getGamer().receiveMessage();
			
			if (obj.toString().equals(String.valueOf(Request.MESSAGE))){
				receivedMessage((Message)obj);
			}else if (obj.toString().equals(String.valueOf(Request.MEMBERADD))){
				addMember((AddMember)obj);
			}else if (obj.toString().equals(String.valueOf(Request.MEMBERREMOVE))){
				removeMember((RemoveMember)obj);
			}else if (obj.toString().equals(String.valueOf(Request.STARTGAME))){
				startGame((StartGame) obj);
				break;
			}
			
		}
		
	}
	
	private void receivedMessage(Message obj) {
		groupView.gotMessage(obj.getFrom() + " :- " + obj.getContent());
	}
	
	private void addMember(AddMember addMember) {
		if (addMember.getName().equals(GameGlobalVariables.getInstance().getGamer().getName())){
			return;
		}
		groupView.gotPlayer(addMember.getName());
	}
	
	private void removeMember(RemoveMember removeMember) {
		groupView.lostPlayer(removeMember.getName());
	}
	
	private void startGame(StartGame startGame) {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new MoveToStart());
		System.out.println("Started Game in group " + GameGlobalVariables.getInstance().getGamer().getGroupName() + " of " + GameGlobalVariables.getInstance().getGamer().getName() + " Of size "+ startGame.getSize());
		groupView.startGame(startGame.getSize());
	}
	
}

