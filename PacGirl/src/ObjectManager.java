import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ObjectManager {

	ArrayList<MazeObject> mazes;
	PacGirlObject pacGirl;
	GhostObject ghost1;
	Rectangle ghostFutureBox1;

	public ObjectManager(PacGirlObject pacGirl, GhostObject ghost1) {
		mazes = new ArrayList<MazeObject>();
		this.pacGirl = pacGirl;
		this.ghost1 = ghost1;
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

	}
	
	public void ghosts() {
		Rectangle ghostFutureBox1 = new Rectangle(ghost1.x, ghost1.y + ghost1.direction, MazeObject.width, MazeObject.height);
		if(checkCollision(ghostFutureBox1) == true) {
			ghost1.direction = ghost1.direction*-1;
			
			
		}
		ghost1.update();
		System.out.println(MazeObject.width + " " + MazeObject.height);
		
	}

	public boolean checkCollision(Rectangle colBox){
		for(MazeObject m: mazes) {
			if(m.state == GamePanel.wall) {
				System.out.println("wall");
				}
			if(m.state == GamePanel.wall && colBox.intersects(m.collisionBox)) {
				return true;
			}
			

		}
		return false;
	}

}
