import java.awt.Color;
import java.util.Stack;

public class AIPlay1 implements AIInterface {


	private int team;
	private static Cell last;
	private CellFinder cf;
	private GameController gc;
	private int [][] tactic;
	public AIPlay1(CellFinder cf,GameController gc, int size, int team, Cell[][] matrix)
	{
		System.out.println("AI MODE: 1");
		this.gc = gc;
		this.cf = cf;
		this.team = team;
		tactic = new int[size][size];
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				if((i==0 || i==1))
				{
					tactic[i][j] = 75;
				}
				else if(j==0 || j==1)
				{
					tactic[i][j] = 75;
				}
				else if((i==size-1 || i==size-2))
				{
					tactic[i][j] = 75;
				}
				else if((j==size-1 || j==size-2))
				{
					tactic[i][j] = 75;
				}
				else
				{
					tactic[i][j] = 100;
				}
				if((i==0 && j==0)||(i==size-1 &&j==size-1)||(i==0 && j==size-1)||(i==size-1 && j==0))
				{
					tactic[i][j] = 100;
				}
				if((i==2 && j==0)||(i==size-3 &&j==size-1)||(i==2 && j==size-1)||(i==size-1 && j==size-3)||(i==size-1 && j==2)||(i==0 && j==2)||(i==0 && j==size-3)||(i==size-3 && j==0))
				{
					tactic[i][j] = 100;
				}
				if((i==1 && j==1)||(i==size-2 && j==size-2)||(i==1 && j==size-2)||(i==size-2 && j==1) )
				{
					tactic[i][j] = 25;
				}
				if((i==1 && j==0)||(i==0 && j==1)||(i==size-2 && j==size-1)||(i==size-1 && j==size-2)||(i==1 && j==size-1)||(i==size-2 && j==0)||(i==size-1 && j==1)||(i==0 && j==size-2) )
				{
					tactic[i][j] = 50;
				}
				System.out.print(tactic[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
	public boolean getTeam()
	{
		if(team == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int getPriceModifier(int x, int y)
	{
		return tactic[y][x];
	}
	public Cell doJob()
	{
		//gc.changeTeam();
		Stack<Cell> st;
		Cell max;
		int maxPrice;
		
		cf.resetEmpty();
		cf.setPadsVisibility(false);
		cf.recalculateAndMark(this.team);
		st = cf.getCellList();
		if(!st.isEmpty())
		{
			max = st.peek();
			maxPrice = max.getPrice()*this.getPriceModifier(max.getXPos(), max.getYPos());
			for(Cell choice: st)
			{
				if(choice.getPrice()*this.getPriceModifier(choice.getXPos(), choice.getYPos()) > maxPrice)
				{
					maxPrice = choice.getPrice()*this.getPriceModifier(choice.getXPos(), choice.getYPos());
					max = choice;
				}
				System.out.print(choice.getPrice()*this.getPriceModifier(choice.getXPos(), choice.getYPos())+" : ");
			}
			System.out.println("SELECTED: "+max.getPrice()*this.getPriceModifier(max.getXPos(), max.getYPos()));
			System.out.println("DONE");
			gc.placeStone(max);
			//cf.turnStones(max.getXPos(),max.getYPos(),gc.getActualPlayer()); 
			//gc.changeTeam();
			if(AIPlay1.last!=null)
			{
				AIPlay1.last.setBackground(Color.white);
			}
			AIPlay1.last = max;
			max.setBackground(Color.magenta);
			return max;
		}
		else
		{
			System.out.println("EMPTY STACK");
			return new Cell(-1,-1,-1,-1,-1,null);
		}
		//gc.changeTeam();
		//st = cf.getCellList();
		//cf.recalculateAndMark(gc.getTeamID());
	}
}
