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
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	ObjectManager om;
	MazeObject mo;
	PacGirlObject pgo;
	GhostObject g1;
	GhostObject g2;
	int fps;
	double speed = 0.35;
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
		g1 = new GhostObject(12, 4, true);
		g2 = new GhostObject(1, 16, false);
		om = new ObjectManager(pgo, g1, g2);
		fps = 60;

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
				if(om.checkCollision(colBox) == false) {
				pgo.y -= speed;
				}
				else {
					
				}
			} else if (moveDown == true) {
				Rectangle colBox = new Rectangle(pgo.getX(), pgo.getY() + 5, PacGirlObject.width, PacGirlObject.height);
				if (om.checkCollision(colBox) == false) {
					pgo.y += speed;
				}
			} else if (moveRight == true) {
				Rectangle colBox = new Rectangle(pgo.getX() + 5, pgo.getY(), PacGirlObject.width, PacGirlObject.height);
				if(om.checkCollision(colBox) == false) {
				pgo.x += speed;
				}
			} else if (moveLeft == true) {
				Rectangle colBox = new Rectangle(pgo.getX() - 5, pgo.getY(), PacGirlObject.width, PacGirlObject.height);
				if(om.checkCollision(colBox) == false) {
				pgo.x -= speed;
				}
			}
		
	
		om.ghostCollision();
		om.draw(g);

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

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = true;

		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = true;

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = true;
		}

		repaint();
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
