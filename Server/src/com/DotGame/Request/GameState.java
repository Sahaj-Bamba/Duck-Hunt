
package com.DotGame.Request;

import com.DotGame.Constant.LineType;
import com.DotGame.Constant.Request;

import java.io.Serializable;

/**
 *
 * @author Sahaj
 */

/**
 *
 * This Class describes the state of the game at any instant .
 * The calculations will take place at the server and state,
 * will be sent by the server to be drawn by the client on screen.
 */
public class GameState implements Serializable{
	public int num;
	private int turn;
	private int size;
	private int numOfPlayer;
	private int totalScore;
	private int[] score = new int [10];
	private int[][] horizontalLines = new int[10][10];
	private int[][] verticalLines = new int[10][10];
	private int[][] rectangles = new int [10][10];
	
	public GameState(int size, int numOfPlayer) {
		this.size = size;
		this.numOfPlayer = numOfPlayer;
		turn = 0;
		totalScore = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				verticalLines[i][j] = -1;
			}
		}
		for (int i = 0; i < 10 ; i++) {
			for (int j = 0; j < 10; j++) {
				horizontalLines[i][j] = -1;
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				rectangles[i][j] = -1;
			}
		}
		for (int i = 0; i < 10; i++) {
			score[i] = 0;
		}
		
	}
	
	
	public void addHorizontalLine(int x, int y) {
		horizontalLines[x][y] = turn;
	}
	
	public void addVerticalLine(int x, int y) {
		verticalLines[x][y] = turn;
	}
	
	public int getVColor(int i, int j) {
		return verticalLines[i][j];
	}
	
	public int getHColor(int i, int j) {
		return horizontalLines[i][j];
	}
	
	public int getRColor(int i, int j) {
		return rectangles[i][j];
	}
	
	public int getTurn(){
		return turn;
	}
	
	public void makeMove(Move move){
		int x,y;
		x = move.getPoint().x;
		y = move.getPoint().y;
		boolean scored = false;
		if (move.getLineType().equals(LineType.Horizontal)){
			addHorizontalLine(x,y);
			if (move.getPoint().x != 0){
				if (horizontalLines[x-1][y] != -1 && verticalLines[x-1][y] != -1 && verticalLines[x-1][y+1] != -1){
					score[turn]++;
					totalScore++;
					rectangles[x-1][y] = turn;
					scored = true;
				}
			}
			if (horizontalLines[x+1][y] != -1 && verticalLines[x][y] != -1 && verticalLines[x][y+1] != -1){
				score[turn]++;
				totalScore++;
				rectangles[x][y] = turn;
				scored = true;
			}
		}else if (move.getLineType().equals(LineType.Vertical)){
			addVerticalLine(x,y);
			if (move.getPoint().y != 0){
				if (horizontalLines[x][y-1] != -1 && verticalLines[x][y-1] != -1 && horizontalLines[x+1][y-1] != -1){
					score[turn]++;
					totalScore++;
					rectangles[x][y-1] = turn;
					scored = true;
				}
			}
			if (horizontalLines[x][y] != -1 && verticalLines[x][y+1] != -1 && horizontalLines[x+1][y] != -1){
				score[turn]++;
				totalScore++;
				rectangles[x][y] = turn;
				scored = true;
			}
		}
		if (!scored){
			while(score[(++turn)%numOfPlayer] == -1);
			turn %= numOfPlayer;
		}
	}
	
	public boolean isOver(){
		if (totalScore == ((size-1)*(size-1))){
			return true;
		}
		return false;
	}
	
	public int getWinner(){
		int x = 0;
		for (int i = 0; i < size; i++) {
			if (score[i] > score[x]){
				x = i;
			}
		}
		return x;
	}
	
	public void remove(int i){
		score[i] = -1;
	}
	
	public String toString() {
		return String.valueOf(Request.GAMESTATE);
	}
	
}