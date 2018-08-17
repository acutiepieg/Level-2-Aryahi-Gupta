import java.awt.Graphics;
import java.awt.Rectangle;

public class PacGirlObject {

	int x;
	int y;
	int row;
	int col;
	static int width = MazeObject.width - 20;
	static int height = MazeObject.height - 20;
	Rectangle cBox;

	public PacGirlObject(int row, int col) {
		this.row = row;
		this.col = col;
		x = (PacGirl.fWidth / GamePanel.numCol) * row;
		y = (PacGirl.fHeight / GamePanel.numRows) * col;
		cBox = new Rectangle(x, y, width, height);

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pacGirlImg, x, y, width, height, null);
		cBox.setBounds(x, y, width, height);
	}

	public void update() {

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
