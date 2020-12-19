package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {
	private final int EXPLOSION_W = 1460;
	private final int EXPLOSION_H = 1460;
	private final int EXPLOSION_COL = 3;
	private final int EXPLOSION_ROWS = 3;
	private final int EXPLOSION_STEPS = 9;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private final int PLAYER_SIZE = 60;
	private final String imagePath = "file:/home/nehalparsekar/Documents/Sem3Prac/PL306/EclipseJava/SpaceInvaders/Images/";
	private final Image PLAYER_IMG = new Image(imagePath + "player.png");
	private final Image EXPLOSION_IMG = new Image(imagePath  + "exp.png");
	private final int MAX_ENEMY = 10;
	private final int MAX_BULLET = MAX_ENEMY * 2;
	private final Image ENEMY_IMG_LIST[] = {
			new Image(imagePath + "1.png"), new Image(imagePath + "2.png"),
			new Image(imagePath + "3.png"), new Image(imagePath + "4.png"),
			new Image(imagePath + "5.png"), new Image(imagePath + "6.png"),
			new Image(imagePath + "7.png"), new Image(imagePath + "8.png"),
			new Image(imagePath + "9.png"), new Image(imagePath + "10.png"),
			new Image(imagePath + "11.png"), new Image(imagePath + "12.png"),
			new Image(imagePath + "13.png"), new Image(imagePath + "14.png"),
			new Image(imagePath + "15.png"), new Image(imagePath + "16.png")
	};
	private final String HS_FILEPATH = "highScore.txt";
	
	private GraphicsContext gc;
	
	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		Explosion explosion = new Explosion(EXPLOSION_W, EXPLOSION_H, EXPLOSION_COL, EXPLOSION_ROWS, EXPLOSION_STEPS, EXPLOSION_IMG);
		SpaceInvaders game = new SpaceInvaders(WIDTH, HEIGHT, PLAYER_SIZE, MAX_ENEMY, PLAYER_IMG, ENEMY_IMG_LIST, this.gc, explosion, HS_FILEPATH);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> game.run(this.gc)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		canvas.setOnMouseMoved(e -> game.setMouseX(e.getX()));
		canvas.setCursor(Cursor.MOVE);
		canvas.setOnMouseClicked(e -> {
			if ((game.getBulletList().size() < MAX_BULLET) && (!game.isGameOver())) {
				game.getBulletList().add(game.getComrade().shoot());
			}
		});

		game.setup();
		Scene scene = new Scene(new StackPane(canvas));
		scene.setOnKeyPressed(e -> {
			KeyCode code = e.getCode();
			if((code == KeyCode.SPACE) && (game.isGameOver())) {
				game.setGameOver(false);
				game.setup();
			}
		});
		stage.setScene(scene);
		stage.setTitle("Space Invaders");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}