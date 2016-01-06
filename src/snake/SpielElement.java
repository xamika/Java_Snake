package snake;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SpielElement {
	final int UNIT = Konstanten.unit;
	protected int xPosition, yPosition;
	protected Rectangle position;
	
	
	/**
	 * Creates a GameElement-Object
	 * @param x int X-Position of the GameElement
	 * @param y int Y-Position of the GameElement
	 */
	public SpielElement(int x, int y) {
		xPosition = x * this.UNIT;
		yPosition = y * this.UNIT;
		position = new Rectangle(xPosition, yPosition, UNIT, UNIT);
	}
	
	/**
	 * To draw the GameElement
	 * @param g Graphics Java Graphics-Object
	 */
	public abstract void draw(Graphics g);
	
	public abstract void kollision(Schlange schlange); 
}
