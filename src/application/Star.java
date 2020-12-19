package application;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Star {
	private Random random;
	private Integer posX, posY, height, width, r, g, b;
	private double opacity;
	private GraphicsContext gc;

	public Star(int width, GraphicsContext gc) {
		this.random = new Random();
		
		this.posX = this.random.nextInt(width);
		this.posY = 0;
		this.width = this.random.nextInt(5) + 1;
		this.height = this.random.nextInt(5) + 1;
		this.r = this.random.nextInt(100) + 150;
		this.g = this.random.nextInt(100) + 150;
		this.b = this.random.nextInt(100) + 150;
		this.opacity = this.random.nextInt(5) + 1;

		this.gc = gc;

		if (this.opacity < 0) {
			this.opacity *= -1;
		}
		if (this.opacity > 0.5) {
			this.opacity = 0.5;
		}
	}

	public void draw() {
		if (this.opacity > 0.8)
			this.opacity -= 0.01;
		if (this.opacity < 0.1)
			this.opacity += 0.01;

		this.gc.setFill(Color.rgb(this.r, this.g, this.b, this.opacity));
		this.gc.fillOval(this.posX, this.posY, this.width, this.height);
		this.posY += 20;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getR() {
		return r;
	}

	public void setR(Integer r) {
		this.r = r;
	}

	public Integer getG() {
		return g;
	}

	public void setG(Integer g) {
		this.g = g;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
}
