package DuckHunt.Request;


import DuckHunt.Constant.Request;

import java.io.Serializable;

public class GameState implements Serializable {
	
	private double[][] X;
	private double[][] Y;
	
	public GameState(int roundNumber,int numOfDucks,int numOfTrips){
		X = new double[numOfDucks][numOfTrips+1];
		Y = new double[numOfDucks][numOfTrips+1];
		for (int i = 0; i < numOfDucks; i++) {
			for (int j = 0; j < numOfTrips; j++) {
				X[i][j] = Math.random()*(1500 - 100);
				Y[i][j] = Math.random()*(900 - 100);
			}
			X[i][numOfTrips] = 1920 + Math.random()*(300);
			Y[i][numOfTrips] = 1080 + Math.random()*(300);
			
		}
	}
	
	public double[] getX(int num) {
		return X[num];
	}
	
	public double[] getY(int num) {
		return Y[num];
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.GAMESTATE);
	}
}
