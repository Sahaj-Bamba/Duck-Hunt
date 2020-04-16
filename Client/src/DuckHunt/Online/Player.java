package DuckHunt.Online;

import DuckHunt.Request.GameState;
import DuckHunt.Request.NewRound;
import javafx.concurrent.Service;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends GridPane {
	
	private Text score;
	private ImageView imageView;
	private String name;
	private int scr;
	private Service<Image> webCamService;
	private boolean player;
	
	public Player(){
		
		player = false;
		
		setHgap(0);
		setVgap(0);
		setPrefSize(420,490);
		setPadding(new Insets(10));
		getColumnConstraints().addAll(new ColumnConstraints(420));
		getRowConstraints().addAll(new RowConstraints(420),new RowConstraints(50));
		
		score = new Text("0");
		setHalignment(score, HPos.CENTER);
		scr = 0;
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Image i1 = null;
		try {
			i1 = new Image(new FileInputStream("assets/image/default.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		imageView = new ImageView(i1);
		imageView.setFitHeight(400);
		imageView.setFitWidth(400);
		add(imageView,0,0);
		add(score,0,1);
	}
	
	public void setWebCamService(Service<Image> webCamService) {
		this.webCamService = webCamService;
		imageView.imageProperty().unbind();
		imageView.imageProperty().bind(webCamService.valueProperty());
		webCamService.restart();
	}
	
	public void setPlayer(boolean player) {
		this.player = player;
	}
	
	public int getScore() {
		return scr;
	}
	
	public void setScore(int score) {
		this.scr = score;
		updateScore();
	}
	
	private void updateScore() {
		score.setText(""+scr);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void updateImage(Image image){
		imageView.setImage(image);
	}
	
	public void provoke() {
		System.out.println(name + " is provoked.");
	}
}
