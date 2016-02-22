import javax.swing.JButton;

import imports.Enum;
import imports.Enum.Team;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Cell extends JButton{
	
	private ImageIcon blackStone;
	private ImageIcon whiteStone;
	private ImageIcon iceStone;
	private ImageIcon ghostStone;
	private int x;
	private int y;
	Team team;
	//private boolean team; //false = black, true = white
	
	@SuppressWarnings("deprecation")
	public Cell(int size,int x, int y, int posX, int posY, SpriteHolder sprites)
	{
		super();
		if(sprites != null){
		this.blackStone = sprites.getBlackStone();
		this.whiteStone = sprites.getWhiteStone();
		this.iceStone = sprites.getIceStone();
		this.ghostStone = sprites.getGhostStone();
		}
		this.x = x;
		this.y = y;
		/*blackStone = new ImageIcon(getClass().getResource("data/sprites/black.png"));
		whiteStone = new ImageIcon(getClass().getResource("data/sprites/white.png"));
		freezedStone = new ImageIcon(getClass().getResource("data/sprites/freezed.png"));
		blank = new ImageIcon(getClass().getResource("data/sprites/blank.png"));*/
		//this.setIcon(this.whiteStone);
		this.setContentAreaFilled(false);
		//this.enable(false);
		this.setEnabled(false);
		this.team = Team.EMPTY;
		this.setBounds(posX, posY, size, size);
	}
	public void setWhite()
	{
		this.setIcon(this.whiteStone);
		this.setDisabledIcon(this.whiteStone);
		this.team = Team.WHITE;
		this.setEnabled(false);
	}
	public void setBlack()
	{
		this.setIcon(this.blackStone);
		this.setDisabledIcon(this.blackStone);
		this.team = Team.BLACK;
		this.setEnabled(false);
	}
	public void setFreezed()
	{
		this.setIcon(this.iceStone);
	}
	public void setGhost()
	{
		this.setIcon(this.ghostStone);
	}
	public void setBlank()
	{
		this.setIcon(null);
		this.setContentAreaFilled(false);
		this.setEnabled(false);
	}
	public Team getTeam()
	{
		return this.team;
	}
	public int getXPos()
	{
		return this.x;
	}
	public int getYPos()
	{
		return this.y;
	}
}
