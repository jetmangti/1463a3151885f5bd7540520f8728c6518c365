import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Stack;

import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imports.Enum.Calc;
import imports.Enum.Team;

public class Game extends JFrame implements Runnable{

	private JLayeredPane contentPane;
	private static int counter=0;
	private JFrame win;
	private Cell[][] board;
	private Cell[][] undoBoard;
	private int cellWidth=80;
	private boolean widthIsLower = false;
	private gameSetting setting;
	private int offset;
	private int clickk;
	private int topOffset = 80;
	private WindowManager wm;
	private SpriteHolder sprite;
	private ImageIcon img = new ImageIcon(getClass().getResource("data/images/header.png"));
	private ImageIcon btn1_0 = new ImageIcon(getClass().getResource("data/buttons/undo.png"));
	private ImageIcon btn1_1 = new ImageIcon(getClass().getResource("data/buttons/undo2.png"));
	private ImageIcon btn1_2 = new ImageIcon(getClass().getResource("data/buttons/undo3.png"));
	private ImageIcon btn2_0 = new ImageIcon(getClass().getResource("data/buttons/save.png"));
	private ImageIcon btn2_1 = new ImageIcon(getClass().getResource("data/buttons/save2.png"));
	private ImageIcon btn2_2 = new ImageIcon(getClass().getResource("data/buttons/save3.png"));
	private boolean undoEnabled = false;
	private int bRem = 0;
	private int wRem = 0;
	/** 
	 * Launch the application.
	 */
	private int min(int a, int b)
	{
		if(a<b)
		{
			return a;
		}
		else 
		{
			return b;
		}
	}
	private int computeCellSize(int margin)
	{
		int size = 0;
		size = this.min(win.getWidth(), win.getHeight()-37-this.topOffset)/this.setting.getSize();
		if(win.getWidth() < win.getHeight())
		{
			this.widthIsLower = true;
		}
		else
		{
			this.widthIsLower = false;
		}
		return (size-margin);
	}
	public void recordAll()
	{
		for(int i=0; i<setting.getSize(); i++)
		{
			for (int j=0;j<setting.getSize() ;j++)
			{
				//board[i][j].resetHistory();
				board[i][j].recordStatus();
			}
		}
	}
	public Game(gameSetting s, WindowManager w) 
	{
		this.setting = s;
		win = new JFrame();
		Stack<Runnable> undoStck = new Stack<Runnable>();
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/

		//win.setVisible(true);
		this.clickk=0;
		contentPane = new JLayeredPane();
		counter++;
		win.setContentPane(contentPane);
		win.setForeground(Color.WHITE);
		win.setBackground(Color.getHSBColor(0, 0, 0.84f));
		win.setTitle("Othello");
		win.setResizable(false);
		win.setBounds(0, 0,1024, 720);//622 388
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		win.setContentPane(contentPane);
		board = new Cell[this.setting.getSize()][this.setting.getSize()];
		this.cellWidth = this.computeCellSize(0);
		if(!this.widthIsLower)
		{
			this.offset = win.getWidth() - this.cellWidth * this.setting.getSize();
		}
		else
		{
			this.offset = win.getHeight() - this.cellWidth * this.setting.getSize();
		}
		this.offset = this.offset/2;
		this.sprite = new SpriteHolder(cellWidth);
		this.bRem = ((s.getSize()*s.getSize())/2)-2;
		this.wRem = ((s.getSize()*s.getSize())/2)-2;
		Runnable rnbl = new Runnable(){
			GameController gc = new GameController(undoStck);
			CellFinder cf = new CellFinder(setting.getSize(),board);
			JLabel blackScore;
			JLabel whiteScore;
			JLabel blackRemaining;
			JLabel whiteRemaining;
		//	AIPlay1 ai2 = new AIPlay1(cf,gc, setting.getSize(), 1, board);
			AIInterface ai;
			int state = 1;
			JButton undo = new JButton(btn1_0);
			JButton save = new JButton(btn2_0);
			JLabel header = new JLabel(img);
			
			public void toDo(Cell temp, MouseEvent e)
			{
				recordAll();
				clickk++;
				if(state == 1)
				{
					//undoStck.push(this);
					cf.resetEmpty();
					gc.placeStone(temp);
					Cell placedStone =(Cell) e.getSource(); 											 // gets ID of cell where stone was added
					cf.turnStones(placedStone.getXPos(),placedStone.getYPos(),gc.getActualPlayer());   // turns opponents stones and gc.getActualPlayer
					if(setting.getGameMode() == 0)
					{
						cf.setPadsVisibility(false);
					}
					cf.recalculateAndMark(gc.getTeamID());
					if(setting.getGameMode() == 0)
					{
						 state = 2;
						 cf.resetEmpty();
					}
					else
					{
						state = 1;
					}
					if(gc.getTeamID()==0)
					{
						bRem--;
					}
					else
					{
						wRem--;
					}
				}
				else if(setting.getGameMode() != 1)
				{
					state = 2;
					cf.resetEmpty();
					Cell aiStone = ai.doJob();
					cf.turnStones(aiStone.getXPos(),aiStone.getYPos(),gc.getActualPlayer());
					cf.setPadsVisibility(true);
					bRem--;
					cf.recalculateAndMark(gc.getTeamID());
					state = 1;
				}
				actualizeScore(whiteScore,blackScore);
			}
			public void actualizeScore(JLabel whiteLabel, JLabel blackLabel)
			{
				int black=0;
				int white=0;
				for(int i=0; i<setting.getSize(); i++)
				{
					for (int j=0;j<setting.getSize() ;j++)
					{
						if(board[i][j].getTeam() == Team.BLACK)
						{
							black++;
						}
						else if(board[i][j].getTeam() == Team.WHITE)
						{
							white++;
						}
						//board[i][j].recordStatus();
					}
				}
				whiteLabel.setText("White Score:"+white);
				blackLabel.setText("Black Score:"+black);
				whiteRemaining.setText("White Stones Remaining:"+wRem);
				blackRemaining.setText("Black Stones Remaining:"+bRem);
				
			}
			public void run()
			{
				undo.setPressedIcon(btn1_2);
				undo.setRolloverIcon(btn1_1);
				save.setPressedIcon(btn2_2);
				save.setRolloverIcon(btn2_1);
				undo.setBorder(BorderFactory.createEmptyBorder());
				save.setBorder(BorderFactory.createEmptyBorder());
				header.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()-15);
				contentPane.add(header,0);
				blackScore = new JLabel("Black Score:2");
				blackScore.setForeground(Color.getHSBColor(0.05f,1.0f,1.0f));
				blackScore.setBounds(10, 80, 150, 20);
				contentPane.add(blackScore, 1);
				
				whiteScore = new JLabel("White Score:2");
				whiteScore.setForeground(Color.getHSBColor(0.05f,1.0f,1.0f));
				whiteScore.setBounds(10, 100, 150, 20);
				contentPane.add(whiteScore, 1);
				
				blackRemaining = new JLabel("Black Stones Remaining:"+(((s.getSize()*s.getSize())/2)-2));
				blackRemaining.setForeground(Color.getHSBColor(0.05f,1.0f,1.0f));
				blackRemaining.setBounds(10, 140, 250, 20);
				contentPane.add(blackRemaining, 1);
				
				whiteRemaining = new JLabel("White Stones Remaining:"+(((s.getSize()*s.getSize())/2)-2));
				whiteRemaining.setForeground(Color.getHSBColor(0.05f,1.0f,1.0f));
				whiteRemaining.setBounds(10, 160, 250, 20);
				contentPane.add(whiteRemaining, 1);
				
				if(setting.getGameMode()==0)
				{
					if(setting.getAiMode()==1)
					{
						ai = new AIPlay1(cf,gc, setting.getSize(), 1, board);
					}
					else
					{
						ai = new AIPlay2(cf,gc, setting.getSize(), 1, board);
					}
				}
				for(int i=0; i<setting.getSize(); ++i)
				{
					for (int j=0;j<setting.getSize() ;++j)
					{
						if(!widthIsLower)
						{
							board[i][j]=new Cell(cellWidth,i,j,(i*cellWidth)+offset-10,j*cellWidth+topOffset,sprite);
						}
						else
						{
							board[i][j]=new Cell(cellWidth,i,j,i*cellWidth,(j*cellWidth)+offset+topOffset,sprite);
						}
						Cell temp = board[i][j];
						board[i][j].addMouseListener(new MouseAdapter() {
							  @Override
							  public void mousePressed(MouseEvent e) 
							  {
								  if(temp.isEnabled())
								  {
									 toDo(temp,e);
									 if(setting.getGameMode()==0)
									 {
										EventQueue.invokeLater( new Runnable(){ 
											public void run()
											{
												try {
													Thread.sleep(750);
													 toDo(temp,e);
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
										});	
									 }
								  }	
							  }																							
						});
						contentPane.add(board[i][j], 1);
					}
				}
				if(s.stoneFreezing)
				{
					Runnable freezer = new Runnable(){
						public void run()
						{
							System.out.println("Freezer running");
							while(true){
							Random rand = new Random();
							int x = rand.nextInt(s.size-1);
							int y = rand.nextInt(s.size-1);
							int timeToSleep = rand.nextInt(s.getB_var())+s.getI_var();
							System.out.println("Treating ["+x+":"+y+"]");
							if(board[x][y].getTeam() != Team.EMPTY && !board[x][y].getFreezed())
							{
								System.out.println("FREEZING....");
								board[x][y].setFreezed();
								cf.resetEmpty();
								cf.recalculateAndMark(gc.getTeamID());
							}
							try {
								Thread.sleep(timeToSleep*1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(board[x][y].getTeam() != Team.EMPTY && board[x][y].getFreezed())
							{
								System.out.println("UNFREEZING....");
								board[x][y].unsetFreezed();
								cf.resetEmpty();
								cf.recalculateAndMark(gc.getTeamID());
							}}
						}
				};
					for(int i=0; i<s.getMaxFreezed(); i++)
					{
						new Thread(freezer).start();
					}
				}
				undo.setBounds(810, 300, 200, 50);
				undo.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e)
					{
						//if(!undoStck.isEmpty())
						//{
							if(clickk > 0){
								clickk--;
							//undoStck.pop().run();
							for(int i=0; i<setting.getSize(); ++i)
							{
								for (int j=0;j<setting.getSize() ;++j)
								{
									board[i][j].undo();
									//board[i][j].undo();
								}	
							}
							if(gc.getTeamID()==0)
							{
								bRem++;
							}
							else
							{
								wRem++;
							}
							gc.changeTeam();
							if(setting.getGameMode() == 0)
							{
								if(gc.getTeamID()==0)
								{
									bRem++;
								}
								else
								{
									wRem++;
								}
								System.out.println("GER");
								for(int i=0; i<setting.getSize(); ++i)
								{
									for (int j=0;j<setting.getSize() ;++j)
									{
										//board[i][j].undo();
										board[i][j].undo();
									}	
								}
								gc.changeTeam();
								/*cf.recalculateAndMark(gc.getTeamID());
									 state = 2;
									 cf.resetEmpty();
								gc.changeTeam();*/
							}
							cf.setPadsVisibility(true);
							cf.hidePads();
							cf.recalculateAndMark(gc.getTeamID());
							//cf.resetEmptyAll();
							actualizeScore(whiteScore, blackScore);
							//}
					}
				}});
				contentPane.add(undo, 1);
				
				save.setBounds(810, 360 , 200, 50);
				save.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e)
					{
						
					}
				});
				contentPane.add(save, 1);
				
				
				board[setting.getSize()/2][setting.getSize()/2].setWhite();
				board[setting.getSize()/2][setting.getSize()/2-1].setBlack();
				board[setting.getSize()/2-1][setting.getSize()/2-1].setWhite();
				board[setting.getSize()/2-1][setting.getSize()/2].setBlack();
				undoBoard = board;
				//recordAll();
				try {
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				JButton tmp = new JButton();
				tmp.addActionListener(w);
				tmp.setActionCommand("HideMD");
				tmp.doClick();
				
				///////////////********GAME START*********///////////////
				
				//int state = 0;
				cf.recalculateAndMark(gc.getTeamID());
			
			}
		};
		new Thread(rnbl).start();
	}
	@Override
	public void run() {
	}
}
