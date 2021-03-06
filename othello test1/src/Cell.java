import javax.swing.JButton;

import imports.Enum;
import imports.Enum.Team;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Stack;

/*
 * Martin Hlipala xhlipa00
 * Adam Bak xbakad00
 * All rights reserved
 */
public class Cell extends JButton{
	
	private ImageIcon blackStone;
	private ImageIcon whiteStone;
	private ImageIcon iceStone;
	private ImageIcon ghostStone;
	private boolean isFreezed;
	private Stack<Runnable> history = new Stack<Runnable>();
	private int x;
	private int y;
	private int price=0;
	Team lastTeam;
	Team team;
	//private boolean team; //false = black, true = white
	
	/**
	 * @param size
	 * @param x
	 * @param y
	 * @param posX
	 * @param posY
	 * @param sprites
	 */
	@SuppressWarnings("deprecation")
	public Cell(int size,int x, int y, int posX, int posY, SpriteHolder sprites) //build cell object
	{
		super();
		this.isFreezed = false;
		if(sprites != null){
		this.blackStone = sprites.getBlackStone();
		this.whiteStone = sprites.getWhiteStone();
		this.iceStone = sprites.getIceStone();
		this.ghostStone = sprites.getGhostStone();
		}
		this.x = x;
		this.y = y;
		this.setContentAreaFilled(false);
		//this.enable(false);
		this.setEnabled(false);
		this.team = Team.EMPTY;
		this.setBounds(posX, posY, size, size);
		//history.push(new Runnable(){public void run(){setBlank();}});
	}
	public void printAll(){
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("Cell "+x+" "+y+":");
		System.out.println("Team: "+team+" Lastteam: "+ lastTeam);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");	
	}
	public void setHistory(){//...
		this.history = new Stack<Runnable>();
	}
	
	public void resetHistory() //...
	{
		this.history.removeAllElements();
	}
	public void recordStatus() //record actual state and save it to the stack
	{
		if(this.team == Team.BLACK)
			history.push(new Runnable(){public void run(){setBlack();}});
		else if(this.team == Team.WHITE)
			history.push(new Runnable(){public void run(){setWhite();}});
		else
			history.push(new Runnable(){public void run(){setBlank();}});
	}
	public void setWhite() //set white stone
	{
		
		this.setIcon(this.whiteStone);
		this.setDisabledIcon(this.whiteStone);
		this.team = Team.WHITE;
		this.setEnabled(false);
		//history.push(new Runnable(){public void run(){setWhite();}});
	}
	public void setBlack() //set black stone
	{

		this.setIcon(this.blackStone);
		this.setDisabledIcon(this.blackStone);
		this.team = Team.BLACK;
		this.setEnabled(false);
	}
	public void setFreezed() //freeze stone
	{
		this.isFreezed=true;
		this.setIcon(this.iceStone);
		this.setDisabledIcon(this.iceStone);
		if(this.team == Team.BLACK)
		{
			this.team=Team.BLACK_FR;
		}
		else
		{
			this.team=Team.WHITE_FR;
		}
	}
	public boolean getFreezed() //is stone freezed ?
	{
		return this.isFreezed;
	}
	public void unsetFreezed() //defreeze stone
	{
		this.isFreezed=false;
		if(this.team == Team.BLACK_FR)
		{
			this.setBlack();
		}
		else
		{
			this.setWhite();
		}
	}
	public void setGhost() //not used
	{
		this.setIcon(this.ghostStone);
	}
	public void setBlank() //set cell as empty
	{
		this.setIcon(null);
		this.setContentAreaFilled(false);
		this.setEnabled(false);
		this.team = Team.EMPTY;
		//history.push(new Runnable(){public void run(){setBlank();}});
	}
	public void undo()	//undo, pop the last state and execute it
	{
		if(!this.history.isEmpty())
		{
			this.history.pop().run();
		}
		//history.pop();
	}
	/**
	 * @param team
	 */
	public void swapTeam(boolean team)
	{
		if(team)
		{
			this.setBlack();
		}
		else
		{
			this.setWhite();
		}
	}
	public Team getTeam()
	{
		return this.team;
	}
	public boolean getTeamBool()
	{
		if(this.team==Team.WHITE){
			return true;
		}
		else 
		{
			return false;
		}
	}
	public int getXPos()
	{
		return this.x;
	}
	public int getYPos()
	{
		return this.y;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public int getPrice()
	{
		return this.price;
	}
}
