package com.DotGame.Main;

/**
 * @author Sahaj
 *
 */

/**
 * The start point of the project
 *
 */
public class Main {
	
	/**
	 * Create and start the server class object
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Server On");
		new Server().start();
	}
	
}
