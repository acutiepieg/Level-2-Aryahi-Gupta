import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	ObjectManager om;
	MazeObject mo;
	PacGirlObject pgo;
	GhostObject g1;
	GhostObject g2;
	GhostObject g3;
	GhostObject g4;
	GhostObject g5;
	int fps;

	int speed = 1;
	boolean moveUp = false;
	boolean moveDown = false;
	boolean moveRight = false;
	boolean moveLeft = false;

	static int wall = 1;
	static int empty = 0;
	static int pacG = 2;
	static int numGhosts = 3;

	public static BufferedImage pacGirlImg;
	final static int numRows = 20;
	final static int numCol = 21;
	int[][] states = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public GamePanel() {

		try {
			pacGirlImg = ImageIO.read(this.getClass().getResourceAsStream("Mp1bpihs_400x400.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		pgo = new PacGirlObject(10, 13);
		g1 = new GhostObject(12, 4, GhostObject.down);
		g2 = new GhostObject(1, 16, GhostObject.right);
		g3 = new GhostObject(5, 12, GhostObject.left);
		g4 = new GhostObject(11, 4, GhostObject.up);
		g5 = new GhostObject(2, 20, GhostObject.down);
		om = new ObjectManager(pgo);
		fps = 60;
		
		om.addGhostObject(g1);
		om.addGhostObject(g2);
		om.addGhostObject(g3);
		om.addGhostObject(g4);

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCol; j++) {
				mo = new MazeObject(i, j, states[i][j]);
				om.addMazeObject(mo);

			}
		}

		Timer timer = new Timer(1000 / fps, this);
		timer.start();

	}

	public void paintComponent(Graphics g) {

		if (moveUp == true) {
			Rectangle colBox = new Rectangle(pgo.getX(), pgo.getY() - 5, PacGirlObject.width, PacGirlObject.height);
			if (om.checkMazeCollision(colBox) == false) {
				pgo.y -= speed;
			} else {

			}
		} else if (moveDown == true) {
			Rectangle colBox = new Rectangle(pgo.getX(), pgo.getY() + 5, PacGirlObject.width, PacGirlObject.height);
			if (om.checkMazeCollision(colBox) == false) {
				pgo.y += speed;
			}
		} else if (moveRight == true) {
			Rectangle colBox = new Rectangle(pgo.getX() + 5, pgo.getY(), PacGirlObject.width, PacGirlObject.height);
			if (om.checkMazeCollision(colBox) == false) {
				pgo.x += speed;
			}
		} else if (moveLeft == true) {
			Rectangle colBox = new Rectangle(pgo.getX() - 5, pgo.getY(), PacGirlObject.width, PacGirlObject.height);
			if (om.checkMazeCollision(colBox) == false) {
				pgo.x -= speed;
			}
		}
		om.ghostCollision();
		om.draw(g);
		checkGhostCollision(pgo.cBox);

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = true;

		} 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = true;

		} 
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = true;
		}

		repaint();
	}
	
	public void checkGhostCollision(Rectangle colBox) {	
		for(GhostObject g : om.ghosts) {
		if(colBox.intersects(om.getGhostFutureRect(g))) {
			JOptionPane.showMessageDialog(this, "You have collided with a ghost. Game Over");
			System.exit(0);
		}
	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("release right");
			moveRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = false;
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
