package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion {
	private Integer expWidth, expHeight, expCols, expRows, expSteps;
	private Image expImage;

	public Explosion(int expWidth, int expHeight, int expCols, int expRows, int expSteps, Image expImage) {
		this.expWidth = expWidth;
		this.expHeight = expHeight;
		this.expCols = expCols;
		this.expRows = expRows;
		this.expSteps = expSteps;
		this.expImage = expImage;
	}
	
	public void drawExplosion(GraphicsContext gc, int currentExpStep, int posX, int posY, int size) {
		gc.drawImage(this.expImage, ((currentExpStep % this.expCols) * this.expWidth),
				((currentExpStep / this.expRows) * this.expHeight), this.expWidth,
				this.expHeight, posX, posY, size, size);
	}

	public Integer getExpWidth() {
		return expWidth;
	}

	public void setExpWidth(Integer expWidth) {
		this.expWidth = expWidth;
	}

	public Integer getExpHeight() {
		return expHeight;
	}

	public void setExpHeight(Integer expHeight) {
		this.expHeight = expHeight;
	}

	public Integer getExpCols() {
		return expCols;
	}

	public void setExpCols(Integer expCols) {
		this.expCols = expCols;
	}

	public Integer getExpRows() {
		return expRows;
	}

	public void setExpRows(Integer expRows) {
		this.expRows = expRows;
	}

	public Integer getExpSteps() {
		return expSteps;
	}

	public void setExpSteps(Integer expSteps) {
		this.expSteps = expSteps;
	}

	public Image getExpImage() {
		return expImage;
	}

	public void setExpImage(Image expImage) {
		this.expImage = expImage;
	}
}
