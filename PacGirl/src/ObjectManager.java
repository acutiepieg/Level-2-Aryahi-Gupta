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
	GhostObject ghost1;
	GhostObject ghost2;
	Rectangle ghostFutureBox1;
	Rectangle ghostFutureBox2;
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
		ghostFutureBox1 = new Rectangle(ghost1.x, ghost1.y + ghost1.speed, MazeObject.width, MazeObject.height);
		checkMazeCollision(ghostFutureBox1);
		if (checkMazeCollision(ghostFutureBox1) == true) {
			ghost1.speed = ghost1.speed * -1;
		}
		ghost1.update();
		// ghost2
		ghostFutureBox2= new Rectangle(ghost2.x + ghost2.speed, ghost2.y, MazeObject.width, MazeObject.height);
		checkMazeCollision(ghostFutureBox2);
		if (checkMazeCollision(ghostFutureBox2) == true) {
			ghost2.speed = ghost2.speed * -1;
		}
		ghost2.update();
	}

	public boolean checkMazeCollision(Rectangle colBox) {
		for (MazeObject m : mazes) {
			if (m.state == GamePanel.wall && colBox.intersects(m.collisionBox)) {
				return true;
			} 
		}
		return false;
	}
	
	public void checkGhostCollision(Rectangle colBox) {
		if(colBox.intersects(ghost1.ghostCollision)) {
			JOptionPane.showMessageDialog(null, "You have collided with a ghost. Game Over");
			System.exit(0);
		}
		else if(colBox.intersects(ghost2.ghostCollision)) {
			JOptionPane.showMessageDialog(null, "You have collided with a ghost. Game Over");
			System.exit(0);
		}
	}
}

