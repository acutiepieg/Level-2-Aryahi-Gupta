import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	ObjectManager om;
	MazeObject mo;
	PacGirlObject pgo;

	static int menuState = 1;
	static final int start = 1;
	static final int game = 2;
	static final int lost = 3;
	static final int win = 4;

	static Integer score = 10000;
	int count = 0;

	GhostObject g1;
	GhostObject g2;
	GhostObject g3;
	GhostObject g4;
	GhostObject g5;
	GhostObject g6;
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
	public static BufferedImage cyanGhost;
	public static BufferedImage blueGhost;
	public static BufferedImage darkPinkGhost;
	public static BufferedImage orangeGhost;
	public static BufferedImage pinkGhost;
	public static BufferedImage redGhost;
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
			cyanGhost = ImageIO.read(this.getClass().getResourceAsStream("cyanGhost.png"));
			blueGhost = ImageIO.read(this.getClass().getResourceAsStream("blueGhost.png"));
			darkPinkGhost = ImageIO.read(this.getClass().getResourceAsStream("darkPinkGhost.png"));
			orangeGhost = ImageIO.read(this.getClass().getResourceAsStream("orangeGhost.png"));
			pinkGhost = ImageIO.read(this.getClass().getResourceAsStream("pinkGhost.png"));
			redGhost = ImageIO.read(this.getClass().getResourceAsStream("redGhost.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		makePacGirl();
		om = new ObjectManager(pgo);
		resetGhosts();

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCol; j++) {
				mo = new MazeObject(i, j, states[i][j]);
				om.addMazeObject(mo);

			}
		}

		fps = 60;

		Timer timer = new Timer(1000 / fps, this);
		timer.start();

	}

	public void resetGhosts() {
		g1 = new GhostObject(12, 5, GhostObject.down, cyanGhost);
		g2 = new GhostObject(1, 16, GhostObject.right, orangeGhost);
		g3 = new GhostObject(5, 12, GhostObject.left, pinkGhost);
		g4 = new GhostObject(11, 5, GhostObject.up, redGhost);
		g5 = new GhostObject(2, 18, GhostObject.right, darkPinkGhost);
		g6 = new GhostObject(1, 1, GhostObject.down, blueGhost);

		om.addGhostObject(g1);
		om.addGhostObject(g2);
		om.addGhostObject(g3);
		om.addGhostObject(g4);
		om.addGhostObject(g5);
		om.addGhostObject(g6);
	}

	public void makePacGirl() {
		pgo = new PacGirlObject(10, 13);
	}

	public void paintComponent(Graphics g) {

		if (menuState == start) {
			om.drawStartState(g);
		}

		if (menuState == game) {
			if (moveUp == true) {
				Rectangle colBox = new Rectangle(pgo.getX(), pgo.getY() - speed, PacGirlObject.width,
						PacGirlObject.height);
				if (om.checkMazeCollision(colBox) == false) {
					pgo.y -= speed;
				} else {

				}
			} else if (moveDown == true) {
				Rectangle colBox = new Rectangle(pgo.getX(), pgo.getY() + speed, PacGirlObject.width,
						PacGirlObject.height);
				if (om.checkMazeCollision(colBox) == false) {
					pgo.y += speed;
				}
			} else if (moveRight == true) {
				Rectangle colBox = new Rectangle(pgo.getX() + speed, pgo.getY(), PacGirlObject.width,
						PacGirlObject.height);
				if (om.checkMazeCollision(colBox) == false) {
					pgo.x += speed;
				}
			} else if (moveLeft == true) {
				Rectangle colBox = new Rectangle(pgo.getX() - speed, pgo.getY(), PacGirlObject.width,
						PacGirlObject.height);
				if (om.checkMazeCollision(colBox) == false) {
					pgo.x -= speed;
				}
			}
			om.ghostCollision();
			om.drawGameState(g);
			checkGhostCollision(pgo.cBox);
		}

		if (menuState == lost) {
			om.drawEndState(g);
		}
		
		if(menuState == win) {
			
		}

		repaint();

	}

	public void checkGhostCollision(Rectangle colBox) {
		for (GhostObject g : om.ghosts) {
			if (colBox.intersects(om.getGhostFutureRect(g, g.direction))) {
				menuState = lost;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (menuState == start) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				menuState = game;
				e.consume();
			}
		}

		if (menuState == game) {
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
		}

		if (menuState == lost) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				makePacGirl();
				om.reset(pgo);
				resetGhosts();
				menuState = game;
			}
		}

		if (menuState == win) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				makePacGirl();
				om.reset(pgo);
				resetGhosts();
				menuState =game;
			}
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
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
		if (menuState == game) {
			count++;
			if (count >= 5) {
				count = 0;
				score = score - 1;
				System.out.println(score);
				// score decreases by 12 points/second
			}
		if(score == 0) {
			menuState = lost;
		}
		
		}
	}

}
