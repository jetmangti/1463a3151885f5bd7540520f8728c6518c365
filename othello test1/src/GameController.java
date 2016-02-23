import imports.Enum.Team;

public class GameController 
{
	private boolean player; //false = black true = white;
	private boolean human;
	private int scoreBlack;
	private int scoreWhite;
	
	public GameController()
	{
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
	public void placeStone(Cell btn)
	{
		
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
