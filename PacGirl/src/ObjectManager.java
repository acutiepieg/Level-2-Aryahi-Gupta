import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class ObjectManager {

	ArrayList<MazeObject> mazes;
	ArrayList<GhostObject> ghosts;
	PacGirlObject pacGirl;
	Rectangle ghostFutureBox;
	Font font = new Font ("Arial", Font.PLAIN, 80);

	Random r = new Random();

	public ObjectManager(PacGirlObject pacGirl) {
		mazes = new ArrayList<MazeObject>();
		ghosts = new ArrayList<GhostObject>();
		this.pacGirl = pacGirl;
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
		g.setFont(font);
		g.drawString("PacGirl", 295, 285);
	}
	
	public void drawGameState(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}
		pacGirl.draw(g);
		for (GhostObject ghost : ghosts) {
			ghost.draw(g);
		}

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
		if (dir == ghost.up) {
			ghostFutureRect = new Rectangle(ghost.x, ghost.y - ghost.speed, MazeObject.width, MazeObject.height);
		} else if (dir == ghost.down) {
			ghostFutureRect = new Rectangle(ghost.x, ghost.y + ghost.speed, MazeObject.width, MazeObject.height);
		} else if (dir == ghost.right) {
			ghostFutureRect = new Rectangle(ghost.x + ghost.speed, ghost.y, MazeObject.width, MazeObject.height);
		} else if (dir == ghost.left) {
			ghostFutureRect = new Rectangle(ghost.x - ghost.speed, ghost.y, MazeObject.width, MazeObject.height);
		}

		return ghostFutureRect;
	}

}
