/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Request;

import DuckHunt.Constant.LineType;
import DuckHunt.Constant.MoveType;
import DuckHunt.Constant.Request;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class ScoreBoard implements Serializable{
	
	private int[] score;
	
	public ScoreBoard(int[] score) {
		this.score = score;
	}
	
	public ScoreBoard() {
	
	}
	
	public int[] getScore() {
		return score;
	}
	
	public void setScore(int[] score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.SCORE);
	}
	
}
