
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PacGirl implements KeyListener {

	public static final int fWidth = 987;
	public static final int fHeight = 1080;
	GamePanel gp;

	public PacGirl() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(fWidth, fHeight);

		gp = new GamePanel();

		frame.add(gp);
		frame.setVisible(true);
		frame.addKeyListener(this);
	}

	public static void main(String[] args) {
		PacGirl pg = new PacGirl();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
			PacGirlObject.x++;
			System.out.println("click right arrow");
		}
		if (e.getKeyCode() == KeyEvent.VK_KP_LEFT) {
			PacGirlObject.x--;
		}
		if (e.getKeyCode() == KeyEvent.VK_KP_UP) {
			PacGirlObject.y--;
		}
		if (e.getKeyCode() == KeyEvent.VK_KP_DOWN) {
			PacGirlObject.y++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
