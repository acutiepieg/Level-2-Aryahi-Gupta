import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MazeObject {

	Random r = new Random();

	int x;
	int y;
	int width;
	int height;
	int row;
	int col;
	int state;
	Rectangle collisionBox; 
	
	public MazeObject(int row, int col, int width, int height, int state) {
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
		this.x = col * width;
		this.y = row * height;
		this.state = state;
		collisionBox = new Rectangle(x, y, width, height);
		
	}

	public void draw(Graphics g) {
		if(state == GamePanel.wall) {
			g.setColor(Color.BLUE);
		}
		
		else if (state == GamePanel.empty) {
			g.setColor(Color.black);
		}
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);
		
	}

}
