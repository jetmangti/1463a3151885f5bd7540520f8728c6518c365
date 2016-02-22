import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imports.Enum.Calc;
import imports.Enum.Team;

public class Game extends JFrame implements Runnable{

	private JLayeredPane contentPane;
	private static int counter=0;
	private JFrame win;
	private Cell[][] board;
	private int cellWidth=80;
	private boolean widthIsLower = false;
	private gameSetting setting;
	private int offset;
	private int topOffset = 80;
	private WindowManager wm;
	private SpriteHolder sprite;
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
	public Game(gameSetting s, WindowManager w) 
	{
		this.setting = s;
		win = new JFrame();
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
		contentPane = new JLayeredPane();
		counter++;
		win.setContentPane(contentPane);
		win.setForeground(Color.WHITE);
		win.setBackground(Color.WHITE);
		win.setTitle("Othello");
		win.setResizable(true);
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
		Runnable rnbl = new Runnable(){
			GameController gc = new GameController();
			CellFinder cf = new CellFinder(setting.getSize(),board);
			public void run()
			{
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
									  cf.resetEmpty();
									  gc.placeStone(temp);
								  }
							  }
						});
						contentPane.add(board[i][j], 0);
					}
				}
				board[setting.getSize()/2][setting.getSize()/2].setWhite();
				board[setting.getSize()/2][setting.getSize()/2-1].setBlack();
				board[setting.getSize()/2-1][setting.getSize()/2-1].setWhite();
				board[setting.getSize()/2-1][setting.getSize()/2].setBlack();
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
				int state = 0;
				while(true)
				{
					/*switch(case)
					{
					
					}*/
				}
			}
		};
		new Thread(rnbl).start();
	}
	@Override
	public void run() {
	}
}
