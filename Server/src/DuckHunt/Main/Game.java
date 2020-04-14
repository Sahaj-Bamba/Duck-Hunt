package DuckHunt.Main;

import DuckHunt.Constant.LineType;
import DuckHunt.Constant.MoveType;
import DuckHunt.Request.GameState;
import DuckHunt.Request.Move;

public class Game {
	
	private int numberOfRounds;
	private int roundNumber;
	private boolean over;
	private GameState gameState;
	private Duck[] duck;
	private String[] players;
	private int[] score;
	private String message;
	
	public Game(int size,String[] players) {
		message = "";
		this.players = players;
		score = new int[2];
		score[0]=0;
		score[1]=0;
		this.numberOfRounds = size;
		this.roundNumber = 0;
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
			duck[i] = new Duck(1,100);
		}
		
	}
	
	public boolean isOver() {
		return over;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	
	public void makeMove(Move move,String by) {
		if (move.getMoveType().equals(MoveType.Left)){
			duck[move.getDuckNumber()].setHp(0);
		}else if (move.getMoveType().equals(MoveType.Damage)){
			if (duck[move.getDuckNumber()].getHp()!=0){
				duck[move.getDuckNumber()].damage(move.getDamage());
				if (duck[move.getDuckNumber()].getHp()==0) {
					for (int i=0;i<2;i++) {
						if (players[i].equals(by)){
							score[i] += duck[move.getDuckNumber()].getScore();
							message = "Duck killed by " + by;
						}
					}
				}
			}
		}
	}
	
	public boolean checkOver() {
		for (Duck tmp : duck) {
			if (tmp.getHp()!=0){
				return false;
			}
		}
		return true;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int[] getScore() {
		System.out.println(score[0]+" "+score[1]);
		return score;
	}
}


class Duck{
	int hp;
	int score;
	
	public Duck(int hp,int score) {
		this.hp = hp;
		this.score = score;
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
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getScore() {
		return score;
	}
}