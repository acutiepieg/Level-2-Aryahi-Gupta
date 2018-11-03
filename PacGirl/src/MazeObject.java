import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MazeObject {

	Random r = new Random();

	int x;
	int y;
	static final int width = PacGirl.fWidth / GamePanel.numCol;
	static final int height =  (PacGirl.fHeight - 60) / GamePanel.numRows;
	int row;
	int col;
	int state;
	Rectangle collisionBox; 
	
	public MazeObject(int row, int col, int state) {
		this.row = row;
		this.col = col;
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
		collisionBox.setBounds(x, y, width, height);
		
	}

}
