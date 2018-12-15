import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GhostObject {

	int x;
	int y;
	int row;
	int col;
	int direction;
	int speed = 5;
	BufferedImage ghostImage;

	static final int up = 0;
	static final int down = 1;
	static final int right = 2;
	static final int left = 3;

	public GhostObject(int row, int col, int direction, BufferedImage ghostImage) {
		this.row = row;
		this.col = col;
		this.direction = direction;
		this.ghostImage = ghostImage;
		x = (PacGirl.fWidth / GamePanel.numCol) * col;
		y = (PacGirl.fHeight / GamePanel.numRows) * row;
	}

	public void draw(Graphics g) {
		g.drawImage(ghostImage, x, y, 40, 40, null);
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
