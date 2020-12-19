package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Comrade extends Player{
	private Bullet bullet;
	private Integer currentExpStep = 0;

	public Comrade(int posX, int posY, int size, Image comradeImage, GraphicsContext gc, Explosion explosion) {
		super(posX, posY, size, comradeImage, gc, explosion);
	}
	
	private int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
	
	public boolean collide(Enemy rocket) {
		int d = distance(this.getPosX() + this.getSize() / 2, this.getPosY() + this.getSize() / 2, rocket.getPosX() + rocket.getSize() / 2,
				rocket.getPosY() + rocket.getSize() / 2);
		return d < (rocket.getSize() / 2 + this.getSize() / 2);
	}

	public Bullet shoot() {
		this.bullet = new Bullet(this.getPosX() + this.getSize() / 2 - Bullet.size / 2, this.getPosY() - Bullet.size, this.getGc());
		return bullet;
	}

	public Integer getCurrentExpStep() {
		return currentExpStep;
	}

	public void setCurrentExpStep(Integer currentExpStep) {
		this.currentExpStep = currentExpStep;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
}