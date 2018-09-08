import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GhostObject {

	int x;
	int y;
	int row;
	int col;
	int direction;
	int speed = 1;
	Color color;
	
	static final int up = 0;
	static final int down = 1;
	static final int right = 2;
	static final int left = 3;

	public GhostObject(int row, int col, int direction, Color color) {
		this.row = row;
		this.col = col;
		this.direction = direction;
		this.color = color;
		x = (PacGirl.fWidth / GamePanel.numCol) * row;
		y = (PacGirl.fHeight / GamePanel.numRows) * col;
		System.out.println(x);
		System.out.println(y);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 40, 40);
	}

	public void update() {
		if (direction == up) {
			y = y - speed;
		} else if (direction == down) {
			y = y + speed;
		} else if (direction == right) {
			x = x + speed;
		} else if (direction == left) {
			x = x - speed;
		}
		
	}
	
}
