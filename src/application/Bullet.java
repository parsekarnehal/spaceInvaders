package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
	private boolean toRemove;
	private GraphicsContext gc;
	private Integer posX, posY, speed;
	static final Integer size = 6;

	public Bullet(int posX, int posY, GraphicsContext gc) {
		this.posX = posX;
		this.posY = posY;
		this.speed = 10;
		this.gc = gc;
	}

	public void update() {
		this.posY -= speed;
	}

	public void draw(int score) {
		gc.setFill(Color.RED);
		if (score >= 50 && score <= 70 || score >= 120) {
			this.gc.setFill(Color.YELLOWGREEN);
			this.speed = 50;
			this.gc.fillRoundRect((posX - 5), (posY - 10), (size + 5), (size + 20), (posY - 5), (size+ 20) / 4);
		} else {
			this.gc.fillOval(posX, posY, size, size);
		}
	}

	private int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	public boolean collide(Enemy comrade) {
		int distance = distance((this.posX + size / 2), (this.posY + size / 2), (comrade.getPosX() + comrade.getSize() / 2), (comrade.getPosY() + comrade.getSize() / 2));
		return distance < (comrade.getSize() / 2 + size / 2);
	}

	public boolean isToRemove() {
		return toRemove;
	}

	public void setToRemove(boolean toRemove) {
		this.toRemove = toRemove;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public static Integer getSize() {
		return size;
	}
}
