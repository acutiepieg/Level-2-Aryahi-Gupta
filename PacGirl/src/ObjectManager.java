import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {

	ArrayList<MazeObject> mazes;
	ArrayList<GhostObject> ghosts;
	PacGirlObject pacGirl;
	GhostObject ghost1;
	GhostObject ghost2;
	Rectangle ghostFutureBox1;
	Rectangle ghostFutureBox2;
	Boolean collision;
	Random r = new Random();

	public ObjectManager(PacGirlObject pacGirl, GhostObject ghost1, GhostObject ghost2) {
		mazes = new ArrayList<MazeObject>();
		this.pacGirl = pacGirl;
		this.ghost1 = ghost1;
		this.ghost2 = ghost2;
	}

	public void addMazeObject(MazeObject maze) {
		mazes.add(maze);
	}

	public void draw(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}
		pacGirl.draw(g);
		ghost1.draw(g);
		ghost2.draw(g);

	}

	public void ghostCollision() {
		// ghost1
		Rectangle ghostFutureBox1 = new Rectangle(ghost1.x, ghost1.y + ghost1.direction, MazeObject.width,
				MazeObject.height);
		checkCollision(ghostFutureBox1);
		if ( collision == true) {
			ghost1.direction = ghost1.direction * -1;
		}
		ghost1.update();
		// ghost2
		ghostFutureBox2 = new Rectangle(ghost2.x + ghost2.direction, ghost2.y, MazeObject.width, MazeObject.height);
		checkCollision(ghostFutureBox2);
		if ( collision == true) {
			int r1 = r.nextInt(2);	
			if (r1 == 0) {
					ghost2.upOrDown = false;
				} else if (r1 == 1) {
					ghost2.upOrDown = true;
				}
				else {
					ghost2.direction = ghost2.direction * -1;
				}
			}	
		
		ghost2.update();
	}

	public void checkCollision(Rectangle colBox) {
		for (MazeObject m : mazes) {
			if (m.state == GamePanel.wall && colBox.intersects(m.collisionBox)) {
				collision = true;
			} else {
				collision = false;
			}

		}

	}
}
