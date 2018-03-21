import javax.swing.JFrame;

public class PacGirl {
	
	public static final int fWidth = 1000;
	public static final int fHeight = 1100;
	GamePanel gp;
	
	public PacGirl() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(fWidth, fHeight);
		
		gp = new GamePanel();
		
		frame.add(gp);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		PacGirl pg = new PacGirl();
	}
}
