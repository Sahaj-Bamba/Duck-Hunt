package DuckHunt.GUI;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Base extends Group {

	public Base () {
		
		splashScreen(this,1920,1080);
		
	}
	
	private void splashScreen(Group root,double width,double height){
		ObservableList observableList = root.getChildren();
		
		// parameters
		final int offset = 50;
		final int radius = 50;
		final int upTime = 1;//3000;
		final int diagonalTime = 1;//1300;
		final double diagonalShiftRight = 1;//300;
		final int pauseTime = 1;//500;
		final int downTime = 1;//1500;
		final String gunShot = "assets/media/gunShot1.wav";
		final int menuFade = 1;//2000;
		final int rotateDuration = 1;//300;
		
		// Images Creating
		Image image1 = null;
		Image image2 = null;
		try {
			image2 = new Image(new FileInputStream("assets/image/duck/red.png"));
			image1 = new Image(new FileInputStream("assets/image/duck/redl.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView imgr = new ImageView(image1);
		ImageView imgl = new ImageView(image2);
		
		// Menu
		VBox parent = new MainMenu();
		
		
		parent.setOpacity(0);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(menuFade),parent);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		parent.setLayoutX(width/2 - 500/2);
		parent.setLayoutY(height/2 - 600/2);
		
		// Media
		Media m1 = new Media(new File(gunShot).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(m1);
		MediaPlayer mediaPlayer2 = new MediaPlayer(m1);
		
		
		// Setting dimensions
		imgr.setFitHeight(radius*2);
		imgr.setFitWidth(radius*2);
		imgl.setFitHeight(radius*2);
		imgl.setFitWidth(radius*2);
		imgl.setX(offset);
		imgl.setY(height-(offset+2*radius));
		imgr.setX(width-(offset+2*radius));
		imgr.setY(height-(offset+2*radius));
		imgl.setRotate(-1*90);
		imgr.setRotate(90);
		
		
		// Adding to screen
		observableList.addAll(parent,imgl,imgr);
		
		// Effects
		
		// Creating
		TranslateTransition translateTransitionLeft = new TranslateTransition(Duration.millis(upTime));
		TranslateTransition translateTransitionRight = new TranslateTransition(Duration.millis(upTime));
		TranslateTransition translateTransitionLeftDiagonal = new TranslateTransition(Duration.millis(diagonalTime));
		TranslateTransition translateTransitionRightDiagonal = new TranslateTransition(Duration.millis(diagonalTime));
		TranslateTransition translateTransitionLeftDown = new TranslateTransition(Duration.millis(downTime));
		TranslateTransition translateTransitionRightDown = new TranslateTransition(Duration.millis(downTime));
		RotateTransition r1 = new RotateTransition(Duration.millis(rotateDuration));
		RotateTransition r2 = new RotateTransition(Duration.millis(rotateDuration));
		RotateTransition r3 = new RotateTransition(Duration.millis(rotateDuration));
		RotateTransition r4 = new RotateTransition(Duration.millis(rotateDuration));
		
		// Setting parameters
		translateTransitionLeft.setByY(-1*( height - 2*(offset+radius)));
		translateTransitionRight.setByY(-1*(height - 2*(offset+radius)));
		translateTransitionLeftDiagonal.setByX(diagonalShiftRight);
		translateTransitionRightDiagonal.setByX(-1*diagonalShiftRight);
		translateTransitionLeftDiagonal.setByY(diagonalShiftRight);
		translateTransitionRightDiagonal.setByY(diagonalShiftRight);
		translateTransitionLeftDown.setByY(((height-(2*offset+radius)) - ((offset+radius) + diagonalShiftRight)));
		translateTransitionRightDown.setByY(((height-(2*offset+radius)) - ((offset+radius) + diagonalShiftRight)));
		r1.setByAngle(135);
		r2.setByAngle(-1*135);
		r3.setByAngle(45);
		r4.setByAngle(-1*45);
		
		// interpolation setting
		translateTransitionLeft.setInterpolator(Interpolator.EASE_IN);
		translateTransitionLeftDiagonal.setInterpolator(Interpolator.EASE_IN);
		translateTransitionLeftDown.setInterpolator(Interpolator.EASE_OUT);
		translateTransitionRight.setInterpolator(Interpolator.EASE_IN);
		translateTransitionRightDiagonal.setInterpolator(Interpolator.EASE_IN);
		translateTransitionRightDown.setInterpolator(Interpolator.EASE_OUT);
		
		// Setting transition end events
		translateTransitionLeft.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("1 end");
			}
		});
		translateTransitionLeftDiagonal.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				mediaPlayer1.play();
			}
		});
		translateTransitionLeftDown.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("3 end");
			}
		});
		translateTransitionRight.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("1 end");
			}
		});
		translateTransitionRightDiagonal.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				mediaPlayer2.play();
			}
		});
		translateTransitionRightDown.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("3 end");
			}
		});
		
		
		// Creating Sequence transition
		SequentialTransition left = new SequentialTransition(imgl,translateTransitionLeft,r1,translateTransitionLeftDiagonal,r3,translateTransitionLeftDown,fadeTransition);
		SequentialTransition right = new SequentialTransition(imgr,translateTransitionRight,r2,translateTransitionRightDiagonal,r4,translateTransitionRightDown);
		
		// Playing
		left.play();
		right.play();
		
		
	}
	
}
