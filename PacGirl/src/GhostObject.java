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
		x = (PacGirl.fWidth / GamePanel.numCol) * 12;
		y = (PacGirl.fHeight / GamePanel.numRows) * 13;

	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(x, y, 50, 50);
	}
	
	public void update() {
		
	}
}
