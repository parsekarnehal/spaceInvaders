package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Player {
	private Integer posX, posY, size;
	private Image img;
	private GraphicsContext gc;
	private Explosion explosion;
	private boolean exploding, destroyed;
	private Integer currentExpStep = 0;
	
	public Player(int posX, int posY, int size, Image img, GraphicsContext gc, Explosion explosion) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.img = img;
		this.gc = gc;
		this.explosion = explosion;
	}
	
	public void update() {
		if (this.exploding) {
			this.currentExpStep++;
		}
		this.destroyed = this.currentExpStep > explosion.getExpSteps();
	}
	
	public void explode() {
		this.exploding = true;
		this.currentExpStep = -1; 
	}
	
	public void draw() {
		if (this.exploding) {
			this.getExplosion().drawExplosion(this.getGc(), this.currentExpStep, this.getPosX(), this.getPosY(), this.getSize());
		} else {
			this.getGc().drawImage(this.getImg(), this.getPosX(), this.getPosY(), this.getSize(), this.getSize());
		}
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public Explosion getExplosion() {
		return explosion;
	}

	public void setExplosion(Explosion explosion) {
		this.explosion = explosion;
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public Integer getCurrentExpStep() {
		return currentExpStep;
	}

	public void setCurrentExpStep(Integer currentExpStep) {
		this.currentExpStep = currentExpStep;
	}
}
