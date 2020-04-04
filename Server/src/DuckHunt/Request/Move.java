/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Request;

import DuckHunt.Constant.LineType;
import DuckHunt.Constant.Request;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class Move implements Serializable{
	
	private int score;
	private int duckNumber;
	
	public Move(int score, int duckNumber) {
		this.score = score;
		this.duckNumber = duckNumber;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getDuckNumber() {
		return duckNumber;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.MOVE);
	}
}
