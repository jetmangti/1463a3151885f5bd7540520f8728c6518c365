import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Cell extends JButton{
	
	private ImageIcon blackStone;
	private ImageIcon whiteStone;
	private ImageIcon iceStone;
	private ImageIcon ghostStone;
	
	@SuppressWarnings("deprecation")
	public Cell(int size, int posX, int posY)
	{
		super();
		this.blackStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_black.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
		this.whiteStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_white.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
		this.iceStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_ice.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
		this.ghostStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_trans.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
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
