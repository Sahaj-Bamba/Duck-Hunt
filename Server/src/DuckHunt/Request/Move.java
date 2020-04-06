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
public class Move implements Serializable{
	
	private int score;
	private int duckNumber;
	private int damage;
	private MoveType moveType;
	
	public Move(int duckNumber, int damage, MoveType moveType) {
		this.duckNumber = duckNumber;
		this.damage = damage;
		this.moveType = moveType;
	}
	
	public Move(int score, int duckNumber, int damage, MoveType moveType) {
		this.score = score;
		this.duckNumber = duckNumber;
		this.damage = damage;
		this.moveType = moveType;
	}
	
	public Move(int duckNumber, MoveType moveType) {
		this.duckNumber = duckNumber;
		this.moveType = moveType;
	}
	
	
	public MoveType getMoveType() {
		return moveType;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getDuckNumber() {
		return duckNumber;
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.MOVE);
	}
	
}
