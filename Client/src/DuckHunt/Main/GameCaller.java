package DuckHunt.Main;

import java.util.Date;

public class GameCaller implements Runnable {
	
	private Game game;
	
	public GameCaller(Game game){
		this.game = game;
	}
	
	@Override
	public void run() {
		while(true) {
			Date date = new Date();
			while (new Date().getTime() - date.getTime() < 1000);
			game.update();
//			if (game.isRoundOver()){
//				newRound();
//			}
		}
	}
	
//	private void newRound() {
//		Date date = new Date();
//		while (new Date().getTime() - date.getTime() < 3000);
//		game.roundStart();
//	}
}
