package snake;

import java.util.Vector;

import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends KeyAdapter {
	private static Vector<SpielElement> spielElemente;
	private static GUI gui;
	private static Schlange schlange;
	private static char pressedKey;
	private static char oldPressedKey = 'd';
	private static int schlangenGroesse = 5;
	private static int speed = 100;

	Game(Vector<SpielElement> spielElemente) {
		Game.gui = new GUI(this);
		Game.spielElemente = spielElemente;
		moveing();
	}

	public void addElement(SpielElement gameElement) {
		Game.spielElemente.add(gameElement);
	}

	public void draw(Graphics g) {
		for (SpielElement e : Game.spielElemente) {
			e.draw(g);
		}
	}

	public void keyPressed(KeyEvent e) {
		char pressedKey = e.getKeyChar();
		if (pressedKey == 'w' || pressedKey == 'a' || pressedKey == 's' || pressedKey == 'd') {
			Game.pressedKey = pressedKey;
		}
	}

	public static void main(String[] args) {
		newGame();
	}

	private static void newGame() {
		pressedKey = 'd';
		oldPressedKey = 'd';
		generateSpieleElemente();
		new Game(spielElemente);
	}

	private static void generateSpieleElemente() {
		spielElemente = new Vector<SpielElement>();

		Diamant diamant = new Diamant(Zufallsgenerator.zufallszahl(Konstanten.minX, Konstanten.maxX), 
				Zufallsgenerator.zufallszahl(Konstanten.minY, Konstanten.maxY),
				Zufallsgenerator.zufallszahl(1, 9));
		schlange = new Schlange(5, 2, 1, 1, schlangenGroesse, 'd', true, new SchlangenTeil(1, 1));
		spielElemente.add(schlange);
		Spielgrenze spielgrenze = new Spielgrenze(Konstanten.minX, Konstanten.minY, Konstanten.maxX, Konstanten.maxY);
		spielElemente.add(spielgrenze);
		spielElemente.add(diamant);
	}

	private static void moveing() {
		while (schlange.getAlive()) {
			try {
				Thread.sleep(speed);
				if ((pressedKey == 'a' && oldPressedKey != 'd') || (pressedKey == 's' && oldPressedKey != 'w')
						|| (pressedKey == 'd' && oldPressedKey != 'a') || (pressedKey == 'w' && oldPressedKey != 's')) {
					schlange.move(pressedKey);
					oldPressedKey = pressedKey;
				} else {
					pressedKey = oldPressedKey;
					schlange.move(pressedKey);
				}

				Game.gui.repaint();
				for (SpielElement spielElement : spielElemente) {
					spielElement.kollision(schlange);
				}
				if (!schlange.getAlive()) {
					JOptionPane.showMessageDialog(null, "GameOver\nScore: " + Integer.toString(schlange.getScore()));
					gui.dispose();
					newGame();
				}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
}
