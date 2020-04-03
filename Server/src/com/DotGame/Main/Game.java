package com.DotGame.Main;

import com.DotGame.Constant.LineType;
import com.DotGame.Request.GameState;
import com.DotGame.Request.Move;

public class Game {
	
	private int size;
	private GameState gameState;
	
	public Game(int size, int numOfPlayers) {
		this.size = size;
		gameState = new GameState(size,numOfPlayers);
	}
	
	public void update(LineType lineType, int x , int y){
		if (lineType == LineType.Horizontal){
			gameState.addHorizontalLine(x,y);
		}else if (lineType == LineType.Vertical){
			gameState.addVerticalLine(x,y);
		}
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public boolean makeMove(Move move){
		gameState.makeMove(move);
		return gameState.isOver();
	}
	
	public int getWinner(){
		return gameState.getWinner();
	}
	
	public void remove(int i){
		gameState.remove(i);
	}
	
}
