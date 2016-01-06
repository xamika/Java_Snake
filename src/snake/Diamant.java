package snake;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * Die Diamant Klasse ist für alle Interaktionen der Diamanten zuständig. 
 * 
 */
public class Diamant extends SpielElement {
	private int value;
	private int x1, x2, x3, x4;
	private int y1, y2, y3, y4;

	public Diamant(int x, int y, int value) {
		super(x, y);
		this.value = value;
		createPlygon(x, y);
	}

	private void createPlygon(int x, int y) {
		x1 = this.xPosition + super.UNIT / 2;
		x2 = this.xPosition + super.UNIT;
		x3 = this.xPosition + super.UNIT / 2;
		x4 = this.xPosition;
		y1 = this.yPosition;
		y2 = this.yPosition + super.UNIT / 2;
		y3 = this.yPosition + super.UNIT;
		y4 = this.yPosition + super.UNIT / 2;	
	}

	public void draw(Graphics g) {
		int[] xPositions = new int[] { x1, x2, x3, x4 };
		int[] yPositions = new int[] { y1, y2, y3, y4 };
		g.drawPolygon(xPositions, yPositions, 4);
		g.drawRect(this.xPosition, this.yPosition, super.UNIT, super.UNIT);
		g.drawString(Integer.toString(value), this.xPosition, this.yPosition);
	}
	
	
	/**
	 * Verschiebt den Diamanten sobalt er gefressen wurde
	 * 
	 * @param schlange
	 *            das Schlangenobjakt
	 */
	private void move(Schlange schlange) {
		xPosition = UNIT * (Zufallsgenerator.zufallszahl(Konstanten.minX, Konstanten.maxX));
		yPosition = UNIT * (Zufallsgenerator.zufallszahl(Konstanten.minY, Konstanten.maxY));
		position = new Rectangle(xPosition, yPosition, UNIT, UNIT);
		createPlygon(xPosition, yPosition);
		this.value = Zufallsgenerator.zufallszahl(1, 9);
		checkDiamantInSchlange(schlange);
	}


	public void kollision(Schlange schlange) {
		Vector<SchlangenTeil> schlangenTeile = schlange.getSchlangnTeile();
		if (schlangenTeile.lastElement().position.intersects(this.position)) {
			schlange.addElement(value);
			schlange.setScore(this.value);
			move(schlange);
			schlange.setSize(schlange.getSize() + 1);
		}
	}

	/**
	 * überprüft ob der Diamant auf einem schlangenteil ist
	 * falls ja wird der Diamant verschoben
	 * 
	 * @param schlange
	 *            das Schlangenobjakt
	 */
	private void checkDiamantInSchlange(Schlange schlange) {
		for (SchlangenTeil schlangenteil : schlange.getSchlangnTeile()) {
			if (schlangenteil.position.intersects(this.position)) {
				move(schlange);
			}
		}
	}
}
