package DuckHunt.Online;

import DuckHunt.Constant.MessageType;
import DuckHunt.GameObjects.Ducks.BasicDuck;
import DuckHunt.GameObjects.Ducks.NewDuck;
import DuckHunt.GameObjects.Guns.Sniper;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Main.Game;
import DuckHunt.Main.GameCaller;
import DuckHunt.Request.GameState;
import DuckHunt.Request.GroupList;
import DuckHunt.Request.Message;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OnlineGame extends GridPane {
	
	private Group gameGroup;                //  Contains the game pannel and ducks
	private Text generalText;               //  A general text field for showing in game events
	private JFXTextField message;           //  The message text field
	private Player[] player;                //  List of player container
	private int roundNumber;                //  Current round number
	private NewDuck[] ducks;                //  Ducks list
	private Thread listen;
	
	public OnlineGame() {
		
		GameGlobalVariables.getInstance().setActiveGun(new Sniper());
		GameGlobalVariables.getInstance().setOnline(true);
		roundNumber = 0;
		
		//  Screen Configuration
		
		setHgap(0);
		setVgap(0);
		setPrefSize(1920,1080);
		getColumnConstraints().addAll(new ColumnConstraints(1500),new ColumnConstraints(420));
		getRowConstraints().addAll(new RowConstraints(100),new RowConstraints(980));

		//  Top Row
		
		//  1st column fill latter with stuff like group name, round number and so
		
		GridPane container = new GridPane();
		getColumnConstraints().addAll(new ColumnConstraints(900),new ColumnConstraints(400),new ColumnConstraints(200));
		getRowConstraints().addAll(new RowConstraints(100));
		
		generalText = textCreater("");
		message = new JFXTextField();
		message.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		
		container.addRow(0,generalText,message,buttonCreater("Send"));
		
		add(container,0,0);
		
		// 2nd column some buttons
		
		HBox buttonContainer = new HBox(20);
		buttonContainer.setPrefHeight(100);
		buttonContainer.setPrefWidth(420);
		buttonContainer.getChildren().addAll(buttonCreater("Provoke"),buttonCreater("Leave"));
		
		add(buttonContainer,1,0);
		
		// Main game
		
		Group gameGroup = new Group();
		
		Image i1 = null;
		try {
			i1 = new Image(new FileInputStream("assets/image/level1.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView background = new ImageView(i1);
		background.setFitHeight(980);
		background.setFitWidth(1500);
		
		gameGroup.getChildren().add(background);
		
		add(gameGroup,0,1);
		
		// Player Score Board kind of
		
		VBox playerContainer = new VBox();
		playerContainer.setPrefHeight(980);
		playerContainer.setPrefWidth(420);
		player = new Player[2];
		player[0] = new Player();
		player[1] = new Player();
		playerContainer.getChildren().addAll(player[0],player[1]);
		
		add(playerContainer,1,1);
		
		init();
		
		roundStart();
		
 	}
	
	private void init() {
		setOpacity(0);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),this);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.play();
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupList(GameGlobalVariables.getInstance().getGamer().getGroupName()));
		GroupList groupList = (GroupList) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		String[] players = groupList.getClients();
		player[0].setName(players[0]);
		player[1].setName(players[1]);
	}
	
	public JFXButton buttonCreater(String str){
		JFXButton button = new JFXButton();
		button.setPrefHeight(100);
		button.setPrefWidth(200);
		button.setText(str);
		button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		button.setTextAlignment(TextAlignment.CENTER);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				clickEventHandller(((JFXButton)e.getTarget()).getText());
			}
		});
		
		return button;
	}
	
	public Text textCreater(String str){
		Text text = new Text(str);
		text.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		return text;
	}
	
	public void clickEventHandller(String str) {
		System.out.println(str);
		if (str.equals("Send")) {
			sendAction();
		}
	}
	
	private void sendAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new Message(GameGlobalVariables.getInstance().getGamer().getName(),GameGlobalVariables.getInstance().getGamer().getGroupName(),message.getText(), MessageType.UserToGroup));
	}
	
	
	public void roundStart() {
		generalText.setText("Round " + roundNumber + " beginning.");
		
		int numberOfDucks = roundNumber / 2 + 2;
		int numOfTrips = 20;
		GameState gameState = (GameState) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		ducks = new BasicDuck[numberOfDucks];
		for (int i = 0; i < numberOfDucks; i++) {
			ducks[i] = new BasicDuck(gameState.getX(i), gameState.getY(i), numOfTrips, false, roundNumber,i);
			ducks[i].play();
			gameGroup.getChildren().add(ducks[i].getImageView());
		}
		
	}
	
	public void gotMessage(String s) {
		generalText.setText(s);
	}


//	public void checkOver() {
//		int tmp=0;
//		for (int i = 0; i < numOfDucks; i++) {
//			if (ducks[i].isDead()){
//				tmp++;
//			}
//		}
//		if (tmp==numOfDucks){
//			System.out.println("Round Over");
//			roundNumber++;
//			isRoundOver = true;
//			roundStart();
//		}
//		if(gameOverCheck()){
//			return;
//		}
//		update();
//	}
//
//	public void update() {
//		score.setText(""+GameGlobalVariables.getInstance().getScore());
//		roundNum.setText(""+roundNumber);
//		missed.setText(""+(missedAllowed - GameGlobalVariables.getInstance().getMissed()));
//	}
//
//
//	public Group getGroup() {
//		return group;
//	}
//
//	public boolean gameOverCheck(){
//		if (GameGlobalVariables.getInstance().getMissed() >= missedAllowed){
//			gameOver();
//			return true;
//		}
//		return false;
//	}
//
//	public void gameOver(){
//		System.out.println("GameOver");
//	}
//
//	public boolean isRoundOver() {
//		return isRoundOver;
//	}
//
}
