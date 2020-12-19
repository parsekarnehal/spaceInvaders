package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SpaceInvaders {
	private List<Star> starList;
	private List<Bullet> bulletList;
	private List<Enemy> enemyList;
	
	private Comrade comrade;
	private GraphicsContext gc;
	private Random random;
	private boolean gameOver;
	private double mouseX;
	private Explosion explosion;
	private Integer score, width, height, comradeSize, maxEnemy;
	private Image comradeImage;
	private Image[] enemyImageList;
	private HighScore hs;
	private Integer highScore;
	
	public SpaceInvaders(int width, int height, int playerSize, int maxBombs, Image playerImage, Image[] bombImages, GraphicsContext gc, Explosion explosion, String hsFilePath) {
		this.random = new Random();
		this.gameOver = false;
		this.width = width;
		this.height = height;
		this.comradeSize = playerSize;
		this.maxEnemy = maxBombs;
		this.comradeImage = playerImage;
		this.enemyImageList = bombImages;
		this.gc = gc;
		this.explosion = explosion;
		this.hs = new HighScore(hsFilePath);
	
		if(this.hs.getHighScore().equals(-1)) {
			this.highScore = 0;
		} else {
			this.highScore = this.hs.getHighScore();
		}
	}
	
	public void setup() {
		this.starList = new ArrayList<>();
		this.bulletList = new ArrayList<>();
		this.enemyList = new ArrayList<>();
		this.comrade = new Comrade((this.width / 2), (this.height - this.comradeSize), this.comradeSize, comradeImage, this.gc, explosion);
		this.score = 0;
		this.highScore = this.hs.getHighScore().equals(-1) ? 0 : this.hs.getHighScore();
		IntStream.range(0, this.maxEnemy).mapToObj(i -> this.newEnemy()).forEach(this.enemyList::add);
	}
	
	private Enemy newEnemy() {
		return new Enemy(50 + this.random.nextInt(this.width - 100), 0, this.comradeSize, this.enemyImageList[this.random.nextInt(this.enemyImageList.length)], this.score, this.height, this.gc, explosion);
	}

	public void run(GraphicsContext gc) {
		this.gc.setFill(Color.grayRgb(20));
		this.gc.fillRect(0, 0, this.width, this.height);
		this.gc.setTextAlign(TextAlignment.CENTER);
		this.gc.setFont(Font.font(20));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("Score: " + this.score, 60, 20);
		this.gc.fillText("High Score: " + this.highScore, 680, 20);

		if (this.gameOver) {
			this.gc.setFont(Font.font(35));
			this.gc.setFill(Color.YELLOW);
			this.gc.fillText("Game Over \n Your Score is: " + this.score + "\n Click SPACE to play again", (this.width / 2), (this.height / 2.5));
		}

		this.starList.forEach(Star::draw);

		this.comrade.update();
		this.comrade.draw();
		
		if(this.mouseX <= 750.0) {
			this.comrade.setPosX((int) mouseX);
		} else {
			//755 End of the canvas
			this.comrade.setPosX(750);
		}

		this.enemyList.stream().peek(Enemy::update).peek(Enemy::draw).forEach(e -> {
			if (this.comrade.collide(e) && !this.comrade.isExploding()) {
				this.comrade.explode();
				
				if(this.highScore.equals(0)) {
					this.hs.setHighScore(this.score);
				} else {
					if(this.score.compareTo(this.hs.getHighScore()) > 0) {
						this.hs.setHighScore(this.score);
					}
				}
			}
		});

		for (int i = this.bulletList.size() - 1; i >= 0; i--) {
			Bullet shot = this.bulletList.get(i);
			if (shot.getPosY() < 0 || shot.isToRemove()) {
				bulletList.remove(i);
				continue;
			}

			shot.update();
			shot.draw(score);

			for (Enemy bomb : enemyList) {
				if (shot.collide(bomb) && !bomb.isExploding()) {
					score++;
					bomb.explode();
					shot.setToRemove(true);
				}
			}
		}

		for (int i = this.enemyList.size() - 1; i >= 0; i--) {
			if (this.enemyList.get(i).isDestroyed()) {
				this.enemyList.set(i, this.newEnemy());
			}
		}

		this.gameOver = this.comrade.isDestroyed();
		
		if (this.random.nextInt(10) > 2) {
			this.starList.add(new Star(this.width, this.gc));
		}

		for (int i = 0; i < this.starList.size(); i++) {
			if (this.starList.get(i).getPosY() > this.height) {
				this.starList.remove(i);
			}
		}
	}

	public List<Star> getStarList() {
		return starList;
	}

	public void setStarList(List<Star> starList) {
		this.starList = starList;
	}

	public List<Bullet> getBulletList() {
		return bulletList;
	}

	public HighScore getHs() {
		return hs;
	}

	public void setHs(HighScore hs) {
		this.hs = hs;
	}

	public Integer getHighScore() {
		return highScore;
	}

	public void setHighScore(Integer highScore) {
		this.highScore = highScore;
	}

	public void setBulletList(List<Bullet> bulletList) {
		this.bulletList = bulletList;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Comrade getComrade() {
		return comrade;
	}

	public void setComrade(Comrade comrade) {
		this.comrade = comrade;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	public Explosion getExplosion() {
		return explosion;
	}

	public void setExplosion(Explosion explosion) {
		this.explosion = explosion;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getComradeSize() {
		return comradeSize;
	}

	public void setComradeSize(Integer comradeSize) {
		this.comradeSize = comradeSize;
	}

	public Integer getMaxEnemy() {
		return maxEnemy;
	}

	public void setMaxEnemy(Integer maxEnemy) {
		this.maxEnemy = maxEnemy;
	}

	public Image getComradeImage() {
		return comradeImage;
	}

	public void setComradeImage(Image comradeImage) {
		this.comradeImage = comradeImage;
	}

	public Image[] getEnemyImageList() {
		return enemyImageList;
	}

	public void setEnemyImageList(Image[] enemyImageList) {
		this.enemyImageList = enemyImageList;
	}
}