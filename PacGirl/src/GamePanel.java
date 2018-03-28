import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	ObjectManager om;
	MazeObject mo;
	final static int numRows = 10;
	final static int numCol = 10;
	
	public GamePanel() {
		System.out.println("game panel constructor");
		om = new ObjectManager();
		
		for(int i = 0; i < numRows; i++ ) {
			for(int j = 0; j < numCol; j ++) {
			mo = new MazeObject(i, j, PacGirl.fWidth/numCol, (PacGirl.fHeight - 170)/numRows );
		om.addMazeObject(mo);
		}	
		}
	}
	
	public void paintComponent(Graphics g){
	System.out.println("Paint component");
		om.draw(g);
	repaint();
	}

}
