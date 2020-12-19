package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Player {
	private Integer speed;
	private Integer height;

	public Enemy(int posX, int posY, int size, Image img, int score, int height, GraphicsContext gc, Explosion explosion) {
		super(posX, posY, size, img, gc, explosion);
		this.speed = (score / 5) + 2;
		this.height = height;
	}

	public void update() {
		super.update();
		if (!this.isExploding() && !this.isDestroyed()) {
			this.setPosY(this.getPosY() + speed);
		}
		if (this.getPosY() > height) {
			this.setDestroyed(true);
		}
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}