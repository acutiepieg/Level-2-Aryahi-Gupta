import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	ObjectManager om;
	MazeObject mo;
	PacGirlObject pgo;
	
	static int wall = 1;
	static int empty = 0;
	static int pacG = 2;
	
	public static BufferedImage pacGirlImg;
	final static int numRows = 20;
	final static int numCol = 21;
	int [][] states = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					   {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1},
					   {1,0,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,0,1},
					   {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
					   {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
					   {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1},
					   {1,0,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,1},
					   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					   {1,0,0,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1},
					   {1,0,0,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1},
					   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					   {1,0,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,1},
					   {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1},
					   {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
					   {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
					   {1,0,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,0,1},
					   {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1},
					   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}   
	};
	public GamePanel() {
		om = new ObjectManager();
		pgo = new PacGirlObject();
		try {
			pacGirlImg = ImageIO.read(this.getClass().getResourceAsStream("Mp1bpihs_400x400.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCol; j++) {
				mo = new MazeObject(i, j, PacGirl.fWidth / numCol, (PacGirl.fHeight - 120)/ numRows, states[i][j]);
				om.addMazeObject(mo);

			}
		}
	}

	public void paintComponent(Graphics g) {
		om.draw(g);
		repaint();
	}

}
