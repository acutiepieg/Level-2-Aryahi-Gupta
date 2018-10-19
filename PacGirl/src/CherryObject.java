import java.awt.Color;
import java.awt.Graphics;

public class CherryObject {
	int x;
	int y;
	int width;
	int height;

	public CherryObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

	public void update() {

	}
}
