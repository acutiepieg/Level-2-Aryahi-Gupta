import java.awt.Color;
import java.awt.Graphics;

public class MazeObject {

	public MazeObject() {
		
	}
	
	public void draw(Graphics g) {
		System.out.println("maze object draw");
		g.setColor(Color.CYAN);
		g.drawRect(PacGirl.fWidth/2, PacGirl.fHeight/2, 70, 100);
		g.fillRect(PacGirl.fWidth/2, PacGirl.fHeight/2, 70, 100);
	}
	
}
