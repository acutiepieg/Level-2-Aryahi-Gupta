import java.awt.Graphics;

public class PacGirlObject {

	int x;
	int y;
	int row;
	int col;

	public PacGirlObject(int row, int col) {
		this.row = row;
		this.col = col;
		x = (PacGirl.fWidth / GamePanel.numCol) * 10;
		y = (PacGirl.fHeight / GamePanel.numRows) * 13;

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pacGirlImg, x, y, null);
	}

	public void update() {
		
	}

}
