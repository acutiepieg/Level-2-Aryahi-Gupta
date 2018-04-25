import java.awt.Graphics;

public class PacGirlObject {
	

	
	public PacGirlObject() {
		
	}

public void draw(Graphics g) {
	System.out.println("PacGirl image");
	System.out.println(PacGirl.fWidth/2 + " x");
	System.out.println(PacGirl.fHeight - PacGirl.fHeight/21 + " y");
	
	g.drawImage(GamePanel.pacGirlImg, PacGirl.fWidth/2, PacGirl.fHeight - PacGirl.fHeight/21, null);
}

}
