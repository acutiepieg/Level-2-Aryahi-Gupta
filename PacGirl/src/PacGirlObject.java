import java.awt.Graphics;
import java.awt.Rectangle;

public class PacGirlObject {

	int x;
	int y;
	int row;
	int col;
	static int width =  MazeObject.width - 20;
	static int height =  MazeObject.height- 20;

	public PacGirlObject(int row, int col) {
		this.row = row;
		this.col = col;
		x = (PacGirl.fWidth / GamePanel.numCol) * row;
		y = (PacGirl.fHeight / GamePanel.numRows) * col;
		//cBox = new Rectangle(x, y, GamePanel.pacGirlImg.getWidth(), GamePanel.pacGirlImg.getHeight());

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pacGirlImg, x, y, width, height, null);
		//cBox.setBounds(x, y, MazeObject.height - 50, MazeObject.width - 50);
	}

	public void update() {
		
	}

}
