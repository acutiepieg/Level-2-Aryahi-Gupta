import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {

	ArrayList<MazeObject> mazes;
	ArrayList<GhostObject> ghosts;

	PacGirlObject pacGirl;
	CherryObject co;
	Rectangle ghostFutureBox;

	Font phosphate = new Font("Phosphate", Font.PLAIN, 80);
	Font phosphateSmall = new Font("Phosphate", Font.PLAIN, 70);
	Font text = new Font("SignPainter", Font.PLAIN, 30);
	Font textBig = new Font("SignPainter", Font.PLAIN, 40);
	Font textEvenBigger = new Font("SignPainter", Font.PLAIN, 60);

	Random r = new Random();
	int numWins = 0;

	public ObjectManager(PacGirlObject pacGirl, CherryObject co) {
		mazes = new ArrayList<MazeObject>();
		ghosts = new ArrayList<GhostObject>();
		this.pacGirl = pacGirl;
		this.co = co;
	}

	public void reset(PacGirlObject pgo) {
		ghosts.clear();
		GamePanel.score = 10000;
		this.pacGirl = pgo;

	}

	public void addMazeObject(MazeObject maze) {
		mazes.add(maze);
	}

	public void addGhostObject(GhostObject ghost) {
		ghosts.add(ghost);
	}

	public void drawStartState(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}

		g.setColor(Color.white);
		g.fillRect(295, 285, 400, 400);
		g.setColor(Color.BLUE);
		g.setFont(phosphate);
		g.drawString("PacGirl", 340, 345);
		g.setFont(text);
		g.drawString("Use the arrow keys to avoid the ghosts.", 305, 410);
		g.drawString("Get the cherry before the score runs out.", 305, 460);
		g.setColor(Color.BLACK);
		g.setFont(textBig);
		g.drawString("Press ENTER to start", 360, 560);

	}

	public void drawEndState(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}

		g.setColor(Color.RED);
		g.fillRect(295, 285, 400, 400);
		g.setColor(Color.BLACK);
		g.setFont(phosphateSmall);
		g.drawString("Game Over", 312, 345);
		g.setFont(textEvenBigger);
		g.drawString("You Lost", 410, 440);
		g.setFont(textBig);
		g.drawString("Press ENTER to play again", 330, 560);
	}

	public void drawGameState(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}
		pacGirl.draw(g);
		co.draw(g);
		for (GhostObject ghost : ghosts) {
			ghost.draw(g);
		}
		g.setColor(Color.WHITE);
		g.setFont(textBig);
		g.drawString("Score - " + GamePanel.score, 790, 40);
	}

	public void drawWinState(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}

		g.setColor(Color.GREEN);
		g.fillRect(295, 285, 400, 400);
		g.setColor(Color.BLACK);
		g.setFont(phosphateSmall);
		g.drawString("You Won", 340, 345);
		g.setFont(textBig);
		g.drawString("Your score was " + GamePanel.score, 360, 440);
		g.setFont(textBig);
		g.drawString("Press ENTER to play again", 330, 560);

	}

	public void ghostCollision() {
		for (GhostObject g : ghosts) {
			ghostFutureBox = getGhostFutureRect(g, g.direction);
			int r2 = r.nextInt(201);
			if (checkMazeCollision(ghostFutureBox) == true || r2 > 199) {
				while (true) {
					int r3 = r.nextInt(4);
					ghostFutureBox = getGhostFutureRect(g, r3);
					if (checkMazeCollision(ghostFutureBox) == false) {
						g.direction = r3;
						break;
					}
				}
			}
			g.update();
		}
	}

	public void cherryCollision() {
		if (pacGirl.cBox.intersects(co.cBox)) {
			GamePanel.menuState = GamePanel.win;
			numWins++;
			checkWins();
		}
	}

	public void checkWins() {
		if (numWins % 2 == 0) {
			addGhostObject(new GhostObject(17, 18, GhostObject.up, GamePanel.deadGhost));
		}
	}

	public boolean checkMazeCollision(Rectangle colBox) {
		for (MazeObject m : mazes) {
			if (m.state == GamePanel.wall && colBox.intersects(m.collisionBox)) {
				return true;
			}
		}
		return false;
	}

	public Rectangle getGhostFutureRect(GhostObject ghost, int dir) {
		Rectangle ghostFutureRect = null;
		if (dir == GhostObject.up) {
			ghostFutureRect = new Rectangle(ghost.x, ghost.y - ghost.speed, MazeObject.width, MazeObject.height);
		} else if (dir == GhostObject.down) {
			ghostFutureRect = new Rectangle(ghost.x, ghost.y + ghost.speed, MazeObject.width, MazeObject.height);
		} else if (dir == GhostObject.right) {
			ghostFutureRect = new Rectangle(ghost.x + ghost.speed, ghost.y, MazeObject.width, MazeObject.height);
		} else if (dir == GhostObject.left) {
			ghostFutureRect = new Rectangle(ghost.x - ghost.speed, ghost.y, MazeObject.width, MazeObject.height);
		}

		return ghostFutureRect;
	}

}
