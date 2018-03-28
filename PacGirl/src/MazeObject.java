import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class MazeObject {

	Random r = new Random();

	int x;
	int y;
	int width;
	int height;
	int row;
	int col;

	public MazeObject(int row, int col, int width, int height) {
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
		this.x = row * width;
		this.y = col * height;
	}

	public void draw(Graphics g) {
		System.out.println("maze object draw");
		g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);
	}

}
