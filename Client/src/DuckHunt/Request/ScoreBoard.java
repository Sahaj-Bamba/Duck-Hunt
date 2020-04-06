/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Request;

import DuckHunt.Constant.Request;

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
	
	public int[] getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.SCORE);
	}
	
}
