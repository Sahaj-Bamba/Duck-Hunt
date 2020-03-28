package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class test extends Application {
	@Override
	public void start(Stage stage) {
		
		//Drawing a Circle
		Circle circle = new Circle();
		
		//Setting the position of the circle
		circle.setCenterX(150.0f);
		circle.setCenterY(135.0f);
		
		//Setting the radius of the circle
		circle.setRadius(60.0f);
		
		//Setting the color of the circle
		circle.setFill(Color.BROWN);
		
		//Setting the stroke width of the circle
		circle.setStrokeWidth(20);
		
		//Creating Translate Transition
		TranslateTransition translateTransition = new TranslateTransition();
		
		//Setting the duration of the transition
		translateTransition.setDuration(Duration.millis(10000));
		
		//Setting the node for the transition
		translateTransition.setNode(circle);
		
		//Setting the value of the transition along the x axis.
		translateTransition.setByX(100);
		
		//Setting the cycle count for the transition
		translateTransition.setCycleCount(1);
		
		//Setting auto reverse value to false
		translateTransition.setAutoReverse(false);
		
		//Playing the animation
		translateTransition.play();
		
		//Creating a Group object
		Group root = new Group(circle);
		
		//Creating a scene object
		Scene scene = new Scene(root, 1500, 900);
		
		TranslateTransition translateTransitiondown = new TranslateTransition();
		translateTransitiondown.setDuration(Duration.millis(10000));
		translateTransitiondown.setNode(circle);
		translateTransitiondown.setByX(-100);
		translateTransitiondown.setCycleCount(1);
		translateTransitiondown.setAutoReverse(false);
		translateTransitiondown.stop();
		
		EventHandler<javafx.scene.input.MouseEvent> eventHandlerBox =
				new EventHandler<javafx.scene.input.MouseEvent>() {
					
					@Override
					public void handle(javafx.scene.input.MouseEvent e) {
						translateTransition.stop();
						
						translateTransitiondown.play();
					}
				};
		//Adding the event handler to the box
		circle.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerBox);
		
		
		//Setting title to the Stage
		stage.setTitle("Translate transition example");
		
		//Adding scene to the stage
		stage.setScene(scene);
		
		//Displaying the contents of the stage
		stage.show();
	}
	public static void main(String args[]){
		launch(args);
	}
}