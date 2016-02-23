import java.awt.Color;
import java.util.Stack;
import imports.Enum;
import imports.Enum.Team;
public class CellFinder 
{
	
	private Stack<Cell> st = new Stack<Cell>();
	private int size;
	private Cell[][] matrix;
	private int priceCounter = 0;
	
	public CellFinder(int size, Cell[][] matrix)
	{
		this.size = size;
		this.matrix = matrix;
	}
	private Cell getNextCell(int x, int y, int dir)
	{
		switch(dir)
		{
			case 0:
			{
				if(x>=0 && x<this.size && y-1>=0 && y-1 < this.size)
					return matrix[x][y-1];
				break;
			}
			case 1:
			{
				if(x>=0 && x<this.size && y+1>=0 && y+1 < this.size)
					return matrix[x][y+1];
				break;
			}
			case 2:
			{
				if(x+1>=0 && x+1<this.size && y>=0 && y < this.size)
					return matrix[x+1][y];
				break;
			}
			case 3:
			{
				if(x-1>=0 && x-1<this.size && y>=0 && y < this.size)
					return matrix[x-1][y];
				break;
			}
			case 4:
			{
				if(x-1>=0 && x-1<this.size && y-1>=0 && y-1 < this.size)
					return matrix[x-1][y-1];
				break;
			}
			case 5:
			{
				if(x+1>=0 && x+1<this.size && y+1>=0 && y+1 < this.size)
					return matrix[x+1][y+1];
				break;
			}
			case 6:
			{
				if(x+1>=0 && x+1<this.size && y-1>=0 && y-1 < this.size)
					return matrix[x+1][y-1];
				break;
			}
			case 7:
			{
				if(x-1>=0 && x-1<this.size && y+1>=0 && y+1 < this.size)
					return matrix[x-1][y+1];
				break;
			}
			default:
			{
				return matrix[x][y];
			}
		}
		return new Cell(-1,-1,0,0,0,null);
	}
	private void rayCast(int x, int y, int team)
	{
		if(team == Team.BLACK.ordinal())
		{
			System.out.println("BLACK");
		}
		else
		{
			System.out.println("WHITE");
		}
		Cell temp;
		for(int dir=0; dir < 8; dir++) 	//0=y_up 1=y_down 2=x_right 3=x_left 4=x_left_y_up 5=x_right_y_down 6=x_right_y_up 7=x_left_y_down
		{
			//System.out.println(dir);
			int state = 0;
			int tempx = x;
			int tempy = y;
			boolean fail = false;
			boolean success = false;
			this.priceCounter = 0;
			do
			{
				temp = getNextCell(tempx,tempy,dir);
				tempx = temp.getXPos();
				tempy = temp.getYPos();
				if(tempx < 0 || tempx >= this.size || tempy < 0 || tempy >= this.size)
				{
					fail = true;
					break;
				}
				if(team == Team.BLACK.ordinal())
				{
					switch(state)
					{
						case 0:
						{
							System.out.println("A");
							if(temp.getTeam() == Team.WHITE)
							{
								state = 1; // white continue;
								this.priceCounter++;
							}
							else
							{
								state = 4; // empty,black = fail
							}
							break;
						}
						case 1: //white
						{
							System.out.println("B");
							if(temp.getTeam() == Team.BLACK)
							{
								state = 4; //black = fail
							}
							else if(temp.getTeam() == Team.EMPTY)
							{
								System.out.println("C");
								this.st.push(temp);
								temp.setContentAreaFilled(true);
								success = true;
								temp.setBackground(Color.green);
								temp.setEnabled(true);
								temp.setPrice(this.priceCounter);
								break;
							}
							else
							{
								state = 1;
								this.priceCounter++;
								break;
							}
						}
						case 4://fail
						{
							fail = true;
							this.priceCounter = 0;
							//temp.setBackground(Color.RED);
							//temp.setContentAreaFilled(true);
							//temp.setEnabled(true);
							break;
						}
						default:
						{
							break;
						}
					}
				}
				else
				{
					System.out.println("WHITE");
					switch(state)
					{
						case 0:
						{
							if(temp.getTeam() == Team.BLACK)
							{
								state = 1; // white continue;
								this.priceCounter++;
							}
							else
							{
								state = 4; // black = fail
							}
							break;
						}
						case 1: //white
						{
							if(temp.getTeam() == Team.WHITE)
							{
								state = 4; //black = fail
							}
							else if(temp.getTeam() == Team.EMPTY)
							{
								success = true;
								temp.setEnabled(true);
								this.st.push(temp);
								temp.setContentAreaFilled(true);
								temp.setBackground(Color.green);
								temp.setPrice(this.priceCounter);
								//this.priceCounter = 0;
								break;
							}
							else
							{
								state = 1;
								this.priceCounter++;
								break;
							}
						}

						case 4://fail
						{
							fail = true;
							this.priceCounter = 0;
							//temp.setBackground(Color.RED);
							//temp.setContentAreaFilled(true);
							//temp.setEnabled(true);
							break;
						}
						default:
						{
							break;
						}
					}
				}
			}
			while(!fail && !success);		
		}
		
	}
	public void clearStack()
	{
		this.st.removeAllElements();
	}
	public Stack<Cell> getCellList()
	{
		//resetEmpty();
		for (Cell cont : st)
		{
		    cont.setEnabled(false);
		    cont.setContentAreaFilled(false);
		}
		return this.st;
	}
	public void resetEmpty()
	{
		for (Cell cont : st)
		{
		    cont.setEnabled(false);
		    cont.setContentAreaFilled(false);
		}
		clearStack();
	}
	public void recalculateAndMark(int team)
	{
		for(int i=0; i < this.size; i++)
		{
			for(int j=0; j < this.size; j++)
			{
				if(this.matrix[i][j].team != Team.EMPTY && this.matrix[i][j].team.ordinal() != team){
					rayCast(i,j,matrix[i][j].team.ordinal());
				}
			}
		}
		System.out.println("RECALC..");
	}
	
	
	public void turnStones(int x,int y,boolean team)
	{
		int reversedDirection, i=0;
		int currentx = x;
		int currenty = y;
		Cell current;
		
		
		for(int direction=0;direction<7;++direction)   //checks in all 8 directions from the stone that was placed
		{
			i=0;
			while(true){			
				
				i++;
				current=getNextCell(currentx,currenty,direction); // next cell in given direction
				currentx = current.getXPos();
			    currenty = current.getYPos();
				
				if(current.getTeam()==Team.EMPTY){  // if the cell is empty, returns to the original
					currentx = x;        			// position and continues in a different direction
					currenty = y;
					break;
				}
				else if(current.getTeamBool()==team) // if the next cell checked contains stone of the current player
				{
					
					if((direction % 2)==1)			//  return back in opposite direction
					{
						reversedDirection=direction-1;		
					}
					else
					{
						reversedDirection=direction+1;
					}
					
					current=getNextCell(currentx,currenty,reversedDirection);
					
					for(i--;i>0;i--){
						
						if(i>0)
							current.swapTeam(!team); // change the color of the stone
						
						current=getNextCell(currentx,currenty,reversedDirection);
						
					}
					break;
				}
			}
		}
	}

	
}
