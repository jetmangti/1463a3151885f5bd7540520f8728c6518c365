import java.awt.Color;
import java.util.Collections;
import java.util.Stack;

import imports.Enum.Team;

/*
 * Martin Hlipala xhlipa00
 * Adam Bak xbakad00
 * All rights reserved
 */
public class AIPlay2 implements AIInterface	//AI2 script
{
	private int team;
	private static Cell last;
	private CellFinder cf;
	private GameController gc;
	/**
	 * @param cf
	 * @param gc
	 * @param size
	 * @param team
	 * @param matrix
	 */
	public AIPlay2(CellFinder cf,GameController gc, int size, int team, Cell[][] matrix)
	{
		System.out.println("AI MODE: 2z");
		this.gc = gc;
		this.cf = cf;
		this.team = team;
	}
	/**
	 * @return
	 */
	public boolean getTeam()	//get team
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
	/* (non-Javadoc)
	 * @see AIInterface#doJob()
	 */
	public Cell doJob() //AI routine
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
		if(!st.isEmpty())	//if there is possible cell placement continue else return blank cell
		{
			max = st.peek();
			maxPrice = max.getPrice();
			for(Cell choice: st)	//find the best solution according to heuristics
			{
				if(choice.getPrice() > maxPrice)
				{
					maxPrice = choice.getPrice();
					max = choice;
				}
				System.out.print(choice.getPrice()+" : ");
			}
			System.out.println("DONE");
			gc.placeStone(max);
			//cf.turnStones(max.getXPos(),max.getYPos(),gc.getActualPlayer()); 
			//gc.changeTeam();
			if(AIPlay2.last!=null)
			{
				AIPlay2.last.setBackground(Color.white);
			}
			AIPlay2.last = max;
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
