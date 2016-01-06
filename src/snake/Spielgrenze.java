package snake;

import java.awt.Graphics;

/**
 * Die Spielgrenze Klasse ist für die Spielgrenze zuständig.
 * Sie zeichnet die Grenze und überprüft ob die Schlange mit ihr kollidiert. 
 * 
 */
public class Spielgrenze extends SpielElement {
	private int xSize;
	private int ySize;

	/**
	 * 
	 * @param x
	 *            int X-Position of Border
	 * @param y
	 *            int Y-Position of Border
	 * @param xSize
	 *            int width with
	 * 
	 * 
	 */
	public Spielgrenze(int x, int y, int xSize, int ySize) {
		super(x, y);
		this.xSize = xSize * super.UNIT;
		this.ySize = ySize * super.UNIT;
	}

	/**
	 * draws the Border
	 * 
	 * @param g
	 *            Graphics The Java Graphics-Object
	 */
	public void draw(Graphics g) {
		g.drawRect(this.xPosition, this.yPosition, this.xSize, this.ySize);
	}

	public void move(String direction) {
	}

	public void kollision(Schlange schlange) {
		SchlangenTeil schlangenTeil = schlange.getSchlangnTeile().lastElement();
		if (schlangenTeil.xPosition < this.xPosition || schlangenTeil.xPosition > this.xSize
				|| schlangenTeil.yPosition < this.yPosition || schlangenTeil.yPosition > this.ySize) {
			schlange.setAlive(false);
		}
	}

}
