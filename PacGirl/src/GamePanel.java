import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	ObjectManager om;
	MazeObject mo;
	
	public GamePanel() {
		System.out.println("game panel constructor");
		om = new ObjectManager();
		mo = new MazeObject();
		om.addMazeObject(mo);
		
	}
	
	public void paintComponent(Graphics g){
	System.out.println("Paint component");
		om.draw(g);
	repaint();
	}

}
