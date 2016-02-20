import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Cell extends JButton{
	
	private ImageIcon blackStone;
	private ImageIcon whiteStone;
	private ImageIcon iceStone;
	private ImageIcon ghostStone;
	
	@SuppressWarnings("deprecation")
	public Cell(int size, int posX, int posY, SpriteHolder sprites)
	{
		super();
		this.blackStone = sprites.getBlackStone();
		this.whiteStone = sprites.getWhiteStone();
		this.iceStone = sprites.getIceStone();
		this.ghostStone = sprites.getGhostStone();
		/*blackStone = new ImageIcon(getClass().getResource("data/sprites/black.png"));
		whiteStone = new ImageIcon(getClass().getResource("data/sprites/white.png"));
		freezedStone = new ImageIcon(getClass().getResource("data/sprites/freezed.png"));
		blank = new ImageIcon(getClass().getResource("data/sprites/blank.png"));*/
		this.setIcon(this.whiteStone);
		this.setContentAreaFilled(false);
		//this.enable(false);
		this.setBounds(posX, posY, size, size);
	}

}
