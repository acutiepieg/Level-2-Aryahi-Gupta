
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PacGirl {

	public static final int fWidth = 987;
	public static final int fHeight = 960;
	GamePanel gp;

	public PacGirl() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(fWidth, fHeight + 120);
		gp = new GamePanel();

		frame.add(gp);
		frame.setVisible(true);
		frame.addKeyListener(gp);
	}

	public static void main(String[] args) {
		PacGirl pg = new PacGirl();
	}

	
}
