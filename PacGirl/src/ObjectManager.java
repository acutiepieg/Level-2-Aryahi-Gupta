 import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {

	ArrayList<MazeObject> mazes;
	PacGirlObject pacGirl;
	
	public ObjectManager(PacGirlObject pacGirl) {
		mazes = new ArrayList<MazeObject>();
		this.pacGirl = pacGirl;
	}

	public void addMazeObject(MazeObject maze) {
		mazes.add(maze);
	}

	public void draw(Graphics g) {
		for (MazeObject m : mazes) {
			m.draw(g);
		}
		pacGirl.draw(g);
		System.out.println("draw PacGirl");
		
	}

}
