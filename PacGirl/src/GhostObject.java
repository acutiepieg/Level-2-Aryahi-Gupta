import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GhostObject {
	
	int x;
	int y;
	int row;
	int col;
	boolean upOrDown;
	int direction = -7;
	
	
	public GhostObject(int row,int col, boolean upOrDown) {
		this.row = row;
		this.col = col;
		this.upOrDown = upOrDown;
		x = (PacGirl.fWidth / GamePanel.numCol) * row;
		y = (PacGirl.fHeight / GamePanel.numRows) * col;
		

	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 40, 40);
	}
	
	public void update() {
		if(upOrDown == true) {
			y = y + direction;
		}
		else {
			x = x + direction;
		}
	}
	
	
	}

