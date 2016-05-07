import javax.swing.ImageIcon;

public class SpriteHolder 
{
	private ImageIcon blackStone;
	private ImageIcon whiteStone;
	private ImageIcon iceStone;
	private ImageIcon ghostStone;
	
	public SpriteHolder(int size)
	{
		this.blackStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_black.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
		this.whiteStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_white.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
		this.iceStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_ice.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
		this.ghostStone = new ImageIcon(new ImageIcon(getClass().getResource("data/sprites/s_trans.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
	}
	
	public ImageIcon getBlackStone() {
		return blackStone;
	}

	public ImageIcon getWhiteStone() {
		return whiteStone;
	}

	public ImageIcon getIceStone() {
		return iceStone;
	}

	public ImageIcon getGhostStone() {
		return ghostStone;
	}
}
