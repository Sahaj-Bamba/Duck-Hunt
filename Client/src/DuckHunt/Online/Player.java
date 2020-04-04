package DuckHunt.Online;

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
	
	public Player(){
		
		setHgap(0);
		setVgap(0);
		setPrefSize(420,490);
		setPadding(new Insets(10));
		getColumnConstraints().addAll(new ColumnConstraints(420));
		getRowConstraints().addAll(new RowConstraints(420),new RowConstraints(70));
		
		score = new Text("0");
		scr = 0;
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Image i1 = null;
		try {
			i1 = new Image(new FileInputStream("assets/image/default.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imageView = new ImageView(i1);
	
		add(imageView,0,0);
		add(score,0,1);
		
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
	
}
