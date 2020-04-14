package DuckHunt.GameObjects.Ducks;

public class BasicDuck extends NewDuck {
	
	
	public BasicDuck (double X[],double Y[],int numberOfTrips,boolean isBoss,int level){
		super("red",X,Y,numberOfTrips,50,1,isBoss,50000,100,level,0);
	}

	public BasicDuck (double X[],double Y[],int numberOfTrips,boolean isBoss,int level,int number){
		super("red",X,Y,numberOfTrips,50,1,isBoss,50000,100,level,number);
	}
	
	@Override
	public String toString() {
		return "";
	}
}
