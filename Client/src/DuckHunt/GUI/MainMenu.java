package DuckHunt.GUI;

import DuckHunt.Main.Game;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.util.Optional;


public class MainMenu extends VBox {
	
	public MainMenu(){
		this.setFillWidth(true);
		this.setWidth(500);
		this.setHeight(600);
		this.getChildren().addAll(buttonCreater("Offline"),buttonCreater("Online"),buttonCreater("Settings"),buttonCreater("Shop"),buttonCreater("Credits"),buttonCreater("Quit"));
		init();
	}
	
	public JFXButton buttonCreater(String str){
		JFXButton button = new JFXButton();
		button.setPrefHeight(100);
		button.setPrefWidth(500);
		button.setText(str);
		button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		button.setTextAlignment(TextAlignment.CENTER);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				clickEventHandller(((JFXButton)e.getTarget()).getText());
			}
		});
		
		if(str.equals("Quit")){
			button.setCancelButton(true);
		}else if (str.equals("Offline")){
			button.setDefaultButton(true);
		}
		
		return button;
	}
	
	public void clickEventHandller(String str) {
		System.out.println(str);
		if (str.equals("Offline")) {
			Scene scene = this.getScene();
			scene.setRoot(new Game().getGroup());
		} else if (str.equals("Online")) {
		
			Group group = (Group)this.getScene().getRoot();
			ObservableList<Node> observableList = group.getChildren();
			observableList.remove(this);
			observableList.add(new GroupSelect());
			
		} else if (str.equals("Settings")) {
		
		} else if (str.equals("Shop")) {
		
		} else if (str.equals("Credits")) {
		
		} else if (str.equals("Quit")) {
			quitAction();
		}
	}
	
	private void quitAction(){
		System.exit(0);
//		JFXDialogLayout dialogLayout = new JFXDialogLayout();
//		dialogLayout.setHeading(new Text("Hi I am the heading"));
//		dialogLayout.setBody(new Text("Hi I am the body"));
//		StackPane stackPane = new StackPane();
//		stackPane.setPrefHeight(500);
//		stackPane.setPrefWidth(500);
//		JFXDialog dialog = new JFXDialog(stackPane,dialogLayout, JFXDialog.DialogTransition.CENTER,false);
//		dialog.setContent(new Label("Content"));
//
//
//		((Group) this.getParent()).getChildren().add(stackPane);
//
//		dialog.show();
//		button.setOnAction((action)->dialog.show(rootStackPane));

//		Region content = new Region();
//		JFXDialog dialog = new JFXDialog(this,content, JFXDialog.DialogTransition.CENTER);
//		dialog.setOverlayClose(false);
//
	}
	
	private void init() {
		setLayoutX(1920/2 - 600/2);
		setLayoutY(1080/2 - 600/2);
	}
	
	
}
