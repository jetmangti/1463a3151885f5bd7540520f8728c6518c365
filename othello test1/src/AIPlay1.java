import java.awt.Color;
import java.util.Stack;
/*
 * Martin Hlipala xhlipa00
 * Adam Bak xbakad00
 * All rights reserved
 */
public class AIPlay1 implements AIInterface { //AI1 script


	private int team; //team
	private static Cell last; //saved cell
	private CellFinder cf;	//cell finder
	private GameController gc; //game controller
	private int [][] tactic; //2D array used to calculate tactics
	public AIPlay1(CellFinder cf,GameController gc, int size, int team, Cell[][] matrix)
	{
		System.out.println("AI MODE: 1");	
		this.gc = gc;
		this.cf = cf;
		this.team = team;
		tactic = new int[size][size];
		for(int i=0; i<size; i++) //calculate tactics
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
	public boolean getTeam()	//returns actual team playing
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
	public int getPriceModifier(int x, int y)	//get price for cell at x y
	{
		return tactic[y][x];
	}
	public Cell doJob()	// ai routine
	{
		//gc.changeTeam();
		Stack<Cell> st;
		Cell max;
		int maxPrice;
		
		cf.resetEmpty();
		cf.setPadsVisibility(false);
		if(cf.recalculateAndMark(this.team)==0)
		{
			return null;
		}
		st = cf.getCellList();	
		if(!st.isEmpty())	//if there was any possible placement found, continue
		{
			max = st.peek();
			maxPrice = max.getPrice()*this.getPriceModifier(max.getXPos(), max.getYPos());
			for(Cell choice: st)	//find the best placement possible according to heuristics
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
			if(AIPlay1.last!=null)
			{
				AIPlay1.last.setBackground(Color.white);
			}
			AIPlay1.last = max;
			max.setBackground(Color.magenta);
			return max; //return the best possible turn
		}
		else	//else its game end
		{
			System.out.println("EMPTY STACK");
			return new Cell(-1,-1,-1,-1,-1,null); //no possible turn found
		}
		//gc.changeTeam();
		//st = cf.getCellList();
		//cf.recalculateAndMark(gc.getTeamID());
	}
}
