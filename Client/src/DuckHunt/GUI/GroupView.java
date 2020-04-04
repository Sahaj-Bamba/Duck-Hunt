package DuckHunt.GUI;

import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Listeners.ListenGroup;
import DuckHunt.Main.Game;
import DuckHunt.Request.GroupList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.*;
import javafx.util.Duration;

public class GroupView extends GridPane {
	
	private Text groupName;
	private Text player1;
	private Text player2;
	private Text chatArea;
	
	private Thread listen;
	
	public GroupView(){
		
		setPrefSize(600,600);
	
//		setHgap(10);
		setVgap(20);
		
		groupName = textCreater("");
		player1 = textCreater("");
		player2 = textCreater("");
		chatArea = textCreater("");
	
		JFXTextField message = new JFXTextField();
		
		chatArea.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		
		getColumnConstraints().addAll(new ColumnConstraints(200),new ColumnConstraints(200),new ColumnConstraints(200));
		getRowConstraints().addAll(new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100));
		
		add(groupName, 1, 0);
		add(buttonCreater("Start Game", GameGlobalVariables.getInstance().getGamer().isIsOwner()), 0, 1);
		add(buttonCreater("Leave Group", GameGlobalVariables.getInstance().getGamer().isIsOwner()), 2, 1);
		add(player1, 0, 2);
		add(player2, 0, 3);
		add(chatArea,0,4,3,1);
		add(message,0,5,2,1);
		add(buttonCreater("Send",true),2,6);
		
		init();
		objectsInit();
		
	}
	
	private void objectsInit() {
		groupName.setText(GameGlobalVariables.getInstance().getGamer().getGroupName());
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupList(groupName.getText()));
		GroupList groupList = (GroupList) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		
		String[] x = groupList.getClients();
		player1.setText(x[0]);
		player2.setText(x[1]);
		
		listen = new Thread(new ListenGroup(this));
		listen.start();
		
		this.setVisible(true);
	}
	
	private void init() {
		setOpacity(0);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),this);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		setLayoutX(1920/2 - 600/2);
		setLayoutY(1080/2 - 600/2);
		fadeTransition.play();
	}
	
	public JFXButton buttonCreater(String str,boolean visibility){
		JFXButton button = new JFXButton();
		button.setPrefHeight(100);
		button.setPrefWidth(200);
		button.setText(str);
		button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		button.setTextAlignment(TextAlignment.CENTER);
		button.setVisible(visibility);
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
		System.out.println(str);
		if (str.equals("Create Group")) {
			createAction();
		} else if (str.equals("Join Group")) {
			joinAction();
		} else if (str.equals("Random")) {
			randomAction();
		}
		
	}
	
	
	public void gotMessage(String s) {
	}
	
	public void gotPlayer(String name) {
	}
	
	public void lostPlayer(String name) {
	}
	
	public void startGame(int size) {
	}
}
