import java.awt.Color;
import java.awt.Graphics;

public class GhostObject {
	
	int x;
	int y;
	int row;
	int col;
	
	
	public GhostObject(int row,int col) {
		this.row = row;
		this.col = col;
		x = (PacGirl.fWidth / GamePanel.numCol) * row;
		y = (PacGirl.fHeight / GamePanel.numRows) * col;

	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 40, 40);
	}
	
	public void update() {
		
	}
}
