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

	public void draw(Graphics g) {
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
			ghostFutureBox = getGhostFutureRect(g);
			int r2 = r.nextInt(101);
			System.out.println(r2);
			if (checkMazeCollision(ghostFutureBox) == true) {
				changeDirection(g);
			}
			g.update();
			
			if (r2 > 99) {
				changeDirection(g);
			}
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

	public Rectangle getGhostFutureRect(GhostObject ghost) {
		Rectangle ghostFutureRect = null;
		if (ghost.direction == ghost.up) {
			ghostFutureRect = new Rectangle(ghost.x, ghost.y - ghost.speed, MazeObject.width, MazeObject.height);
		} else if (ghost.direction == ghost.down) {
			ghostFutureRect = new Rectangle(ghost.x, ghost.y + ghost.speed, MazeObject.width, MazeObject.height);
		} else if (ghost.direction == ghost.right) {
			ghostFutureRect = new Rectangle(ghost.x + ghost.speed, ghost.y, MazeObject.width, MazeObject.height);
		} else if (ghost.direction == ghost.left) {
			ghostFutureRect = new Rectangle(ghost.x - ghost.speed, ghost.y, MazeObject.width, MazeObject.height);
		}

		return ghostFutureRect;
	}

	public void changeDirection(GhostObject g) {
		g.direction = r.nextInt(4);
	}	
	
}
