import java.util.Stack;

import imports.Enum.Team;

public class GameController 
{
	private boolean player; //false = black true = white;
	private boolean human;
	private int scoreBlack;
	private int scoreWhite;
	private Stack<Runnable> undo;
	
	public GameController(Stack<Runnable> undo)
	{
		this.undo = undo;
		this.player = true;
		this.scoreBlack = 2;
		this.scoreWhite = 2;
	}
	public boolean getActualPlayer()
	{
		boolean player;
		player = this.player;
		this.player = !this.player;
		return player;
	}
	public int getTeamID()
	{
		if(this.player)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	public void changeTeam()
	{
		this.player = !this.player;
	}
	public void removeStone(Cell btn)
	{
	//	btn.setIcon(null);
		btn.setBlank();
	}
	public void placeStone(Cell btn)
	{
		this.undo.push(
				new Runnable()
				{
					public void run()
					{
						removeStone(btn);
					}
				}
				);
		if(!player)
		{
			btn.setBlack();
		}
		else
		{
			btn.setWhite();
		}
	}
}
