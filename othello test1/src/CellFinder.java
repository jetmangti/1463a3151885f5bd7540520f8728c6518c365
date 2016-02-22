import java.awt.Color;
import java.util.Stack;
import imports.Enum;
import imports.Enum.Team;
public class CellFinder 
{
	
	private Stack<Cell> st = new Stack<Cell>();
	private int size;
	private Cell[][] matrix;
	
	public CellFinder(int size, Cell[][] matrix)
	{
		this.size = size;
		this.matrix = matrix;
	}
	private Cell getNextCell(int x, int y, int dir)
	{
		if(x-1 < 0 || x+1 >= this.size || y-1 < 0 || y+1 >= this.size)
		{
			return new Cell(-1,-1,0,0,0,null);
		}
		switch(dir)
		{
			case 0:
			{
				return matrix[x][y-1];
			}
			case 1:
			{
				return matrix[x][y+1];
			}
			case 2:
			{
				return matrix[x+1][y];
			}
			case 3:
			{
				return matrix[x-1][y];
			}
			case 4:
			{
				return matrix[x-1][y-1];
			}
			case 5:
			{
				return matrix[x+1][y+1];
			}
			case 6:
			{
				return matrix[x+1][y-1];
			}
			case 7:
			{
				return matrix[x-1][y+1];
			}
			default:
			{
				return matrix[x][y];
			}
		}
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
		for(int dir=0; dir < 8; ++dir) 	//0=y_up 1=y_down 2=x_right 3=x_left 4=x_left_y_up 5=x_right_y_down 6=x_right_y_up 7=x_left_y_down
		{
			//System.out.println(dir);
			int state = 0;
			int tempx = x;
			int tempy = y;
			boolean fail = false;
			boolean success = false;
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
								state = 3;
							}
							else
							{
								state = 1;
								break;
							}
						}
						case 3:	//empty - success
						{
							System.out.println("C");
							this.st.push(temp);
							//temp.setGhost();
							temp.setContentAreaFilled(true);
							success = true;
							temp.setBackground(Color.green);
							temp.setEnabled(true);
							//System.out.println(" SUCC");
							break;
						}
						case 4://fail
						{
							System.out.println("D");
							fail = true;
							//System.out.println(" FAIL");
							break;
						}
						default:
						{
							System.out.println("E");
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
							System.out.println("F");
							if(temp.getTeam() == Team.BLACK)
							{
								state = 1; // white continue;
							}
							else
							{
								state = 4; // black = fail
							}
							break;
						}
						case 1: //white
						{
							System.out.println("G");
							if(temp.getTeam() == Team.WHITE)
							{
								state = 4; //black = fail
							}
							else if(temp.getTeam() == Team.EMPTY)
							{
								state = 3;
							}
						}
						case 3:	//empty - success
						{
							System.out.println("H");
							success = true;
							temp.setEnabled(true);
							this.st.push(temp);
							temp.setContentAreaFilled(true);
							temp.setBackground(Color.green);
							break;
						}
						case 4://fail
						{
							System.out.println("I");
							fail = true;
							break;
						}
						default:
						{
							System.out.println("J");
							break;
						}
					}
				}
				/*if(team == Team.BLACK)
				{
					switch(state)
					{
						case 0:
						{
							System.out.println("A");
							if(temp.getTeam() == Team.WHITE)
							{
								state = 1; // white continue;
							}
							else
							{
								state = 4; // black = fail
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
								state = 3;
							}
						}
						case 3:	//empty - success
						{
							System.out.println("C");
							this.st.push(temp);
							//temp.setGhost();
							temp.setContentAreaFilled(true);
							success = true;
							//System.out.println(" SUCC");
							break;
						}
						case 4://fail
						{
							System.out.println("D");
							fail = true;
							//System.out.println(" FAIL");
							break;
						}
						default:
						{
							System.out.println("E");
							break;
						}
					}
				}
				else
				{
					switch(state)
					{
						case 0:
						{
							System.out.println("F");
							if(temp.getTeam() == Team.BLACK)
							{
								state = 1; // white continue;
							}
							else
							{
								state = 4; // black = fail
							}
							break;
						}
						case 1: //white
						{
							System.out.println("G");
							if(temp.getTeam() == Team.WHITE)
							{
								state = 4; //black = fail
							}
							else if(temp.getTeam() == Team.EMPTY)
							{
								state = 3;
							}
						}
						case 3:	//empty - success
						{
							System.out.println("H");
							success = true;
							this.st.push(temp);
							temp.setContentAreaFilled(true);
							break;
						}
						case 4://fail
						{
							System.out.println("I");
							fail = true;
							break;
						}
						default:
						{
							System.out.println("J");
							break;
						}
					}
				}*/
			}
			while(!fail && !success);		
		}
		
	}
	public void resetEmpty()
	{
		for (Cell cont : st)
		{
		    cont.setEnabled(false);
		    cont.setContentAreaFilled(false);
		}
	}
	public void recalculateAndMark(int team)
	{
		if(team == Team.BLACK.ordinal())
		{
			System.out.println("EE BLACK");
		}
		for(int i=0; i < this.size; ++i)
		{
			for(int j=0; j < this.size; ++j)
			{
				if(this.matrix[i][j].team != Team.EMPTY && this.matrix[i][j].team.ordinal() != team){
					rayCast(i,j,matrix[i][j].team.ordinal());
				}
			}
		}
	}
}
