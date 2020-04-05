package DuckHunt.Listeners;

import DuckHunt.Constant.MessageType;
import DuckHunt.Constant.Request;
import DuckHunt.GUI.GroupView;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Online.OnlineGame;
import DuckHunt.Request.*;

/**
 *
 * @author Sahaj
 */
public class ListenGame implements Runnable{
	
	private OnlineGame onlineGame;
	
	public ListenGame(OnlineGame onlineGame) {
		this.onlineGame = onlineGame;
	}
	
	@Override
	public void run(){
		
		while (true){
			
			Object obj = GameGlobalVariables.getInstance().getGamer().receiveMessage();
			
			if (obj.toString().equals(String.valueOf(Request.MESSAGE))){
				receivedMessage((Message)obj);
			}else if (obj.toString().equals(String.valueOf(Request.MEMBERREMOVE))){
				removeMember((RemoveMember)obj);
			}else if (obj.toString().equals(String.valueOf(Request.GAMEOVER))){
				gameOver((GameOver)obj);
			}
			
		}
		
	}
	
	private void gameOver(GameOver obj) {
		onlineGame.gameOver(obj);
	}
	
	private void receivedMessage(Message obj) {
		if (obj.getType().equals(MessageType.Provoke)){
			if (obj.getFrom().equals(GameGlobalVariables.getInstance().getGamer().getName())){
			
			}else{
				onlineGame.provoke();
			}
		}else{
			onlineGame.gotMessage(obj.getFrom() + " :- " + obj.getContent());
		}
	}
	
	
	private void removeMember(RemoveMember removeMember) {
		onlineGame.lostPlayer(removeMember.getName());
	}
	
	
}

