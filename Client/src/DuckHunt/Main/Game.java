package DuckHunt.Main;

import DuckHunt.GameObjects.Ducks.BasicDuck;
import DuckHunt.GameObjects.Ducks.Duck;
import DuckHunt.GameObjects.Ducks.NewDuck;
import DuckHunt.GameObjects.Guns.Gun;
import DuckHunt.GameObjects.Guns.Rifle;
import DuckHunt.GameObjects.Guns.Sniper;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Request.GameState;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class Game {
	
	private Group group;
	private Group gameGroup;
	private int roundNumber;
	private ObservableList<Node> observableList;
	private ObservableList<Node> gameObservableList;
	private int numOfDucks;
	private int numOfTrips;
	private NewDuck[] ducks;
	private int missedAllowed;
	private Text score;
	private Text roundNum;
	private Text missed;
	private boolean isRoundOver;
	
	public Game() {
		missedAllowed = 5;
		roundNumber = 0;
		group = new Group();
		gameGroup = new Group();
		observableList = group.getChildren();
		gameObservableList = gameGroup.getChildren();
		GameGlobalVariables.getInstance().setActiveGun(new Sniper());
		GameGlobalVariables.getInstance().setScore(0);
		GameGlobalVariables.getInstance().setMissed(0);
		
		//  Background
		Image i1 = null;
		try {
			i1 = new Image(new FileInputStream("assets/image/level1.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView background = new ImageView(i1);
		background.setX(0);
		background.setY(100);
		background.setFitHeight(GameGlobalVariables.getInstance().getGameScreenHeight());
		background.setFitWidth(GameGlobalVariables.getInstance().getGameScreenWidth());
		
		// Layout of text
		score = new Text();
		missed = new Text();
		roundNum = new Text();
		update();
		score.setX(100);
		score.setY(20);
		roundNum.setX(700);
		roundNum.setY(20);
		missed.setX(1500);
		missed.setY(20);
		
		// Adding everything
		group.getChildren().addAll(gameGroup,score,roundNum,missed);
		gameGroup.getChildren().add(background);
		new Thread(new GameCaller(this)).start();
		roundStart();
	}
	
	public void roundStart() {
		isRoundOver = false;
		numOfDucks = roundNumber / 2 + 2;
		this.numOfTrips = 20;
		GameState gameState = new GameState(roundNumber, numOfDucks, numOfTrips);
		ducks = new BasicDuck[numOfDucks];
		for (int i = 0; i < numOfDucks; i++) {
			ducks[i] = new BasicDuck(gameState.getX(i), gameState.getY(i), numOfTrips, false, roundNumber);
			ducks[i].play();
			gameObservableList.add(ducks[i].getImageView());
		}
		addEvents();
	}
	
	public void addEvents() {
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
//				System.out.println("hi");
				GameGlobalVariables.getInstance().getActiveGun().fakeShot();
				checkOver();
			}
		};
		//Adding event Filter
		gameGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	public void checkOver() {
		int tmp=0;
		for (int i = 0; i < numOfDucks; i++) {
			if (ducks[i].isDead()){
				tmp++;
			}
		}
		if (tmp==numOfDucks){
			System.out.println("Round Over");
			roundNumber++;
			isRoundOver = true;
			roundStart();
		}
		if(gameOverCheck()){
			return;
		}
		update();
	}
	
	public void update() {
		score.setText(""+GameGlobalVariables.getInstance().getScore());
		roundNum.setText(""+roundNumber);
		missed.setText(""+(missedAllowed - GameGlobalVariables.getInstance().getMissed()));
	}
	
	
	public Group getGroup() {
		return group;
	}
	
	public boolean gameOverCheck(){
		if (GameGlobalVariables.getInstance().getMissed() >= missedAllowed){
			gameOver();
			return true;
		}
		return false;
	}
	
	public void gameOver(){
		System.out.println("GameOver");
	}
	
	public boolean isRoundOver() {
		return isRoundOver;
	}
}
