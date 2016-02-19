import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame {

	private JLayeredPane contentPane;
	private static int counter=0;
	private JFrame win;
	private Cell[][] board;
	private int cellWidth=80;
	private boolean widthIsLower = false;
	private gameSetting setting;
	private int offset;
	private int topOffset = 80;
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
	public Game(gameSetting s) 
	{
		this.setting = s;
		win = new JFrame();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		for(int i=0; i<this.setting.getSize(); ++i)
		{
			for (int j=0;j<this.setting.getSize() ;++j)
			{
				if(!this.widthIsLower)
					board[i][j]=new Cell(this.cellWidth,(i*this.cellWidth)+this.offset-10,j*this.cellWidth+this.topOffset);
				else
					board[i][j]=new Cell(this.cellWidth,i*this.cellWidth,(j*this.cellWidth)+this.offset+this.topOffset);
				contentPane.add(board[i][j], 0);
			}
		}
	}
}
