import java.awt.Color;
import java.util.Stack;
import imports.Enum;
import imports.Enum.Team;
/*
 * Martin Hlipala xhlipa00
 * Adam Bak xbakad00
 * All rights reserved
 */
public class CellFinder 
{
	
	private Stack<Cell> st = new Stack<Cell>();	//found possible placements storage
	private Stack<Cell> lastState = new Stack<Cell>(); //last state 
	private int size; //size
	private Cell[][] matrix; //board
	private int priceCounter = 0; 
	private boolean visibility = true;
	private int counter=0;
	
	/**
	 * @param size
	 * @param matrix
	 */
	public CellFinder(int size, Cell[][] matrix) // build the cellfinder object
	{
		this.size = size;
		this.matrix = matrix;
	}
	/**
	 * @param x
	 * @param y
	 * @param dir
	 * @return
	 */
	private Cell getNextCell(int x, int y, int dir) //get neighbour cell in defined direction
	//0=y_up 1=y_down 2=x_right 3=x_left 4=x_left_y_up 5=x_right_y_down 6=x_right_y_up 7=x_left_y_down
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
	/**
	 * @param x
	 * @param y
	 * @param team
	 */
	private void rayCast(int x, int y, int team) //function to find the possible placements using raycast method
	{
		Cell temp;
		this.lastState = (Stack<Cell>) this.st.clone();
		for(int dir=0; dir < 8; dir++) 	//0=y_up 1=y_down 2=x_right 3=x_left 4=x_left_y_up 5=x_right_y_down 6=x_right_y_up 7=x_left_y_down
		{
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
					//implementation of fsm for black player
					switch(state)
					{
						case 0:
						{
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
							if(temp.getTeam() != Team.WHITE && temp.getTeam() != Team.EMPTY)
							{
								state = 4; //black = fail
							}
							else if(temp.getTeam() == Team.EMPTY)
							{
								this.st.push(temp);
								temp.setContentAreaFilled(true);
								success = true;
								if(this.visibility)
								{
									temp.setBackground(Color.getHSBColor(0.08f,1.0f,1.0f));
									temp.setEnabled(true);
								}
								temp.setPrice(this.priceCounter);
								this.counter++;
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
							break;
						}
						default:
						{
							break;
						}
					}
				}
				else
				{ // version for white player
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
							if(temp.getTeam() != Team.BLACK && temp.getTeam() != Team.EMPTY)
							{
								state = 4; //black = fail
							}
							else if(temp.getTeam() == Team.EMPTY)
							{
								success = true;
								this.st.push(temp);
								temp.setContentAreaFilled(true);
								if(this.visibility)
								{
									temp.setBackground(Color.getHSBColor(0.08f,1.0f,1.0f));
									temp.setEnabled(true);
								}
								temp.setPrice(this.priceCounter);
								//this.priceCounter = 0;
								this.counter++;
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
	public Stack<Cell> getCellList() //returns list (stack) of found possible fields
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
	public void hidePads()	//dont show in gui
	{
		for(Cell cont : st)
		{
			//cont.setEnabled(false);
			cont.setContentAreaFilled(false);
			//cont.setPrice(0);
			//cont.setBlank();
		}
	}
	public void resetEmptyAll()	//reset everything
	{
		this.counter=0;
		for(Cell cont : lastState)
		{
			cont.setBlank();
		}
		for(int i=0; i<this.size; i++)
		{
			for(int j=0; j<this.size; j++)
			{
				matrix[i][j].setEnabled(false);
				matrix[i][j].setContentAreaFilled(false);
				//matrix[i][j].undo();
				clearStack();
			}
		}
	}
	/**
	 * @param team
	 * @return
	 */
	public int recalculateAndMark(int team)	//rayCast controller
	{
		this.counter=0;
		for(int i=0; i < this.size; i++)
		{
			for(int j=0; j < this.size; j++)
			{
				if(this.matrix[i][j].team != Team.EMPTY && this.matrix[i][j].team.ordinal() != team && (this.matrix[i][j].team == Team.BLACK || this.matrix[i][j].team == Team.WHITE)){
					rayCast(i,j,matrix[i][j].team.ordinal());
				}
			}
		}
		System.out.println("RECALC..");
		return this.counter;
	}
	
	/**
	 * @param vis
	 */
	public void setPadsVisibility(boolean vis)
	{
		this.visibility = vis;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param team
	 */
	public void turnStones(int x,int y,boolean team) //turn the stones in a line
	{
		int reversedDirection, i=0;
		int currentx = x;
		int currenty = y;
		Cell current;
		
		
		for(int direction=0;direction<8;++direction)   //checks in all 8 directions from the stone that was placed
		{
			i=0;
			while(true){			
				
				i++;
				current=getNextCell(currentx,currenty,direction); // next cell in given direction
				currentx = current.getXPos();
			    currenty = current.getYPos();
				
				if(current.getTeam()==Team.EMPTY || current.getTeam()==Team.WHITE_FR || current.getTeam()==Team.BLACK_FR){  // if the cell is empty, returns to the original
					currentx = x;        			// position and continues in a different direction
					currenty = y;
					break;
				}
				else if((current.getTeam() == Team.BLACK && team==false) || (current.getTeam() == Team.WHITE && team==true) ) // if the next cell checked contains stone of the current player
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
					currentx = current.getXPos();
				    currenty = current.getYPos();
				    
					for(i--;i>0;i--){
						
						//if(i>0)
						current.swapTeam(!team); // change the color of the stone
						current=getNextCell(currentx,currenty,reversedDirection);
						currentx = current.getXPos();
					    currenty = current.getYPos();
						
					}
					break;
				}
			}
		}
	}

	
}
