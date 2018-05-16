import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {

	ArrayList<MazeObject> mazes;
	PacGirlObject pacGirl;
	GhostObject ghost1;

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
		//ghost1.draw(g);

	}

}
