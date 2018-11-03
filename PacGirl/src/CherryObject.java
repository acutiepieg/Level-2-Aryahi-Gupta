import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CherryObject {
	int x;
	int y;
	int width;
	int height;
	Rectangle cBox;

	public CherryObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cBox = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.cherry, x, y, 45, 45, null);
		cBox.setBounds(x, y, width, height);
	}

	public void update() {

	}
}
