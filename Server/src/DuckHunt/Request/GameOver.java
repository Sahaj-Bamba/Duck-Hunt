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
public class GameOver implements Serializable{
	
	private String name;
	private String message;
	
	public GameOver(String name, String message) {
		this.name = name;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.GAMEOVER);
	}
	
}
