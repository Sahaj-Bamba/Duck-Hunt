package DuckHunt.GUI;

import DuckHunt.Main.Game;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.*;
import javafx.util.Duration;

public class GroupSelect extends GridPane {
	
	public GroupSelect(){
		
		setPrefSize(600,600);
	
//		setHgap(10);
		setVgap(20);
		
		HBox buttonContainer = new HBox();
		buttonContainer.setPrefHeight(100);
		buttonContainer.setPrefWidth(100);
		
		buttonContainer.getChildren().addAll(buttonCreater("Create Group"),buttonCreater("Join Group"),buttonCreater("Random"));
		
		Text text1 = textCreater("Your Name");
		Text text2 = textCreater("Group Name");
		Text text3 = textCreater("Password");
		
		JFXTextField clientName = new JFXTextField();
		JFXTextField groupName = new JFXTextField();
		JFXPasswordField password = new JFXPasswordField();
		
		clientName.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		groupName.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		password.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		
		
		getColumnConstraints().addAll(new ColumnConstraints(300),new ColumnConstraints(300));
		getRowConstraints().addAll(new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100));
		
		add(text1, 0, 0);
		add(text2, 0, 1);
		add(text3, 0, 2);
		add(clientName, 1, 0);
		add(groupName, 1, 1);
		add(password, 1, 2);
		
		add(buttonContainer,0,3,2,2);
		
		init();
		
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
		if (str.equals("Offline")) {
			Scene scene = this.getScene();
			scene.setRoot(new Game().getGroup());
		} else if (str.equals("Online")) {
		
		} else if (str.equals("Settings")) {
		
		} else if (str.equals("Shop")) {
		
		} else if (str.equals("Credits")) {
		
		} else if (str.equals("Quit")) {
	
		}
	}
	
	
}
