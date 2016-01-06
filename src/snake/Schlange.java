package snake;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * Die Schlange Klasse ist für alle Interaktionen der Schlange zuständig. Sie
 * bewegt die Einzelnen Schlangenteile in die richtige Richtung, speichert den
 * Speed der Schlange und weiss wie lang die Schlange ist
 */
public class Schlange extends SpielElement {
	private int size, score, xEndposition, yEndposition, teileLeft;
	private char direction;
	private boolean isAlive = true;
	private Vector<SchlangenTeil> body = new Vector<SchlangenTeil>();

	/**
	 * Sezt die Supervariablen und die Eigenen
	 * 
	 * @param x
	 *            int X-Coordinate
	 * @param y
	 *            int Y-Coordinate
	 * @param xEnd
	 *            int X-Endcoordinate
	 * @param yEnd
	 *            int Y-Endcoordinate
	 * @param size
	 *            int Grösse der Schlange
	 * @param isAlive
	 *            boolean lebt die Schlange noch
	 * @param teileLeft
	 *            int wie viele Teile noch angefügt werden müssen
	 * 
	 */
	public Schlange(int x, int y, int xEnd, int yEnd, int size, char direction, boolean alive, SchlangenTeil head) {
		super(x, y);
		this.size = size * super.UNIT;
		this.xEndposition = xEnd * super.UNIT;
		this.yEndposition = yEnd * super.UNIT;
		this.isAlive = alive;
		this.body.add(head);

		for (int i = 0; i < size; i++) {
			SchlangenTeil schlangenTeil = new SchlangenTeil(x + i, y);
			this.body.add(schlangenTeil);
		}
	}

	/**
	 * Zeichnet die Schlange
	 * 
	 * @param g
	 *            Graphics-Object
	 */
	public void draw(Graphics g) {
		for (int i = this.body.indexOf(this.body.firstElement()); i < this.body
				.lastIndexOf(this.body.lastElement()); i++) {
			int x = this.body.get(i).xPosition;
			int y = this.body.get(i).yPosition;
			if (i == this.body.lastIndexOf(this.body.lastElement()) - 1) {
				drawHead(g, x, y);
			} else {
				g.fillRect(x, y, super.UNIT, super.UNIT);
				g.drawRect(x, y, super.UNIT, super.UNIT);
			}
		}
		this.xEndposition = this.body.lastElement().xPosition / super.UNIT;
		this.yEndposition = this.body.lastElement().yPosition / super.UNIT;
	}

	/**
	 * Zeichnet den Kopf der Schlange als Oval
	 * 
	 * @param g
	 *            Graphics-Object
	 * @param x
	 *            int x Position vom Kopf
	 * @param y
	 *            int y Position vom Kopf
	 */
	private void drawHead(Graphics g, int x, int y) {
		g.fillOval(x, y, super.UNIT, super.UNIT);
		g.drawOval(x, y, super.UNIT, super.UNIT);
		switch (direction) {
		case 'w':
			g.fillRect(x, y + super.UNIT / 2, super.UNIT, super.UNIT / 2);
			g.drawRect(x, y + super.UNIT / 2, super.UNIT, super.UNIT / 2);
			break;
		case 'a':
			g.fillRect(x + super.UNIT / 2, y, super.UNIT / 2, super.UNIT);
			g.drawRect(x + super.UNIT / 2, y, super.UNIT / 2, super.UNIT);
			break;
		case 's':
			g.fillRect(x, y, super.UNIT, super.UNIT / 2);
			g.drawRect(x, y, super.UNIT, super.UNIT / 2);
			break;
		case 'd':
			g.fillRect(x, y, super.UNIT / 2, super.UNIT);
			g.drawRect(x, y, super.UNIT / 2, super.UNIT);
			break;
		}
	}

	/**
	 * bewegt die Schlange in die richtige Richtung
	 * 
	 * @param direction
	 *            direction ein Buchstabe mit der richtung
	 */
	public void move(char direction) {
		switch (direction) {
		case 's':
			this.body.add(new SchlangenTeil(this.xEndposition, this.yEndposition + 1));
			break;
		case 'd':
			this.body.add(new SchlangenTeil(this.xEndposition + 1, this.yEndposition));
			break;
		case 'a':
			this.body.add(new SchlangenTeil(this.xEndposition - 1, this.yEndposition));
			break;
		case 'w':
			this.body.add(new SchlangenTeil(this.xEndposition, this.yEndposition - 1));
			break;
		}
		if (teileLeft > 0) {
			teileLeft--;
		} else {
			this.body.remove(this.body.firstElement());
		}
		this.direction = direction;
	}

	/**
	 * Aktualisiert die Grösse
	 * 
	 * @param size
	 *            size new size
	 */
	public void setSize(int size) {
		this.size = size / super.UNIT;
	}

	/**
	 * Gibt den Wert der size Variable zurück
	 * 
	 * @return int value of private size Variable
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Gibt zurück ob die Schlange noch am leben ist.
	 * 
	 * @return boolean Wert der private Variable isAlive
	 */
	public boolean getAlive() {
		return this.isAlive;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public void setAlive(Boolean alive) {
		this.isAlive = alive;
	}

	public Vector<SchlangenTeil> getSchlangnTeile() {
		return this.body;
	}

	public void kollision(Schlange schlange) {
		Rectangle schlangenKopf = body.lastElement().position;
		for (int i = 0; i < body.size() - 1; i++) {
			if (schlangenKopf.intersects(body.get(i).position)) {
				isAlive = false;
			}
		}
	}

	public void addElement(int value) {
		teileLeft += value;
	}

}
