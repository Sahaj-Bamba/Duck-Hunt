package DuckHunt.Main;

import DuckHunt.Constant.LineType;
import DuckHunt.Request.GameState;
import DuckHunt.Request.Move;

public class Game {
	
	private int numberOfRounds;
	private int roundNumber;
	private boolean over;
	private GameState gameState;
	private Duck[] duck;
	
	public Game(int size) {
		this.numberOfRounds = size;
		this.roundNumber = -1;
		over = false;
		newRound();
	}
	
	public void newRound(){
	
		roundNumber++;
		if (roundNumber == numberOfRounds){
			over = true;
			return;
		}
		int numberOfDucks = roundNumber / 2 + 2;
		int numOfTrips = 20;
		gameState = new GameState(roundNumber,numberOfDucks,numOfTrips);
		duck = new Duck[numberOfDucks];
		for (int i = 0; i < numberOfDucks; i++) {
			duck[i] = new Duck(1);
		}
		
	}
	
	public boolean isOver() {
		return over;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	
}


class Duck{
	int hp;
	
	public Duck(int hp) {
		this.hp = hp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void damage(int damage){
		hp -= damage;
		if (hp<0){
			hp=0;
		}
	}
	
}