import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	
	ArrayList<MazeObject> mazes;
	
	public ObjectManager() {
		mazes = new ArrayList<MazeObject>();
	}
	
	public void addMazeObject(MazeObject maze) {
		System.out.println("add maze");
		mazes.add(maze);
	}

	public void draw(Graphics g) {
		for(MazeObject m: mazes) {
			System.out.println("draw MazeObject");
			m.draw(g);
		}
	}

}
