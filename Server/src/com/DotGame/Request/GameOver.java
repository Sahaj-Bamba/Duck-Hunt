/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Request;

import com.DotGame.Constant.Request;
import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class GameOver implements Serializable{
	
	private int name;
	
	public GameOver(int name) {
		this.name = name;
	}
	
	public int getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.GAMEOVER);
	}
	
}
