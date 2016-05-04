import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class LoadingScreen extends JFrame implements window {
	
	private JLayeredPane pane;
	private ImageIcon brand = new ImageIcon(getClass().getResource("data/images/brand.jpg"));
	private ImageIcon anim = new ImageIcon(getClass().getResource("data/images/loading.gif"));
	private ImageIcon footer = new ImageIcon(getClass().getResource("data/images/footer.png"));
	private ImageIcon body = new ImageIcon(getClass().getResource("data/images/loading_message.png"));
	private WindowManager wm;

	private Game[] game;
	
	public LoadingScreen(JFrame frame, WindowManager w) 
	{
		this.wm = w;
		buildGUI(frame,w);
		EventQueue.invokeLater(new Runnable(){
			public void run()
			{
				buildGame(w.getSettings());
			}
		});
		//new Thread(load2).start();
	}
	
	public LoadingScreen(JFrame frame, WindowManager w, gameSetting setting, Cell[][] board) 
	{
		this.wm = w;
		buildGUI(frame,w);
		EventQueue.invokeLater(new Runnable(){
			public void run(){
			
				loadGame(setting,board);
			}
			
		});
	}

	private void buildGUI(JFrame form, WindowManager w)
	{
		pane = new JLayeredPane();
		JLabel label = new JLabel(brand);
		label.setBounds(0, 0, brand.getIconWidth(), brand.getIconHeight());
		pane.add(label, 0);
		
		JLabel foot = new JLabel(footer);
		foot.setBounds(0, form.getHeight() - footer.getIconHeight(), footer.getIconWidth(), footer.getIconHeight());
		pane.add(foot,  0);
		
		JLabel circle = new JLabel(anim);
		circle.setBounds(25, form.getHeight()-290, anim.getIconWidth(), anim.getIconHeight());
		circle.setDoubleBuffered(true);
		pane.add(circle,0);
			
		JLabel bdy = new JLabel(body);
		bdy.setBounds(Math.round(form.getWidth()/2.5f)-50, (brand.getIconHeight()/4)*5, body.getIconWidth(), body.getIconHeight());
		pane.add(bdy,  0);
		
	}
	private void buildGame(gameSetting setting)
	{
		
		game = new Game[setting.getInstancies()];
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				for(int i=0; i<setting.getInstancies(); ++i)
				{
					Runnable rnbl = new Runnable(){
					public void run(){
						 new Game(setting,wm);
						}
					};
					new Thread(rnbl).start();
				}
			}
		});
	}
	
	private void loadGame(gameSetting setting, Cell[][] board)
	{
		System.out.println("Loading game");
		
		game = new Game[setting.getInstancies()];
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				
					Runnable rnbl = new Runnable(){
					public void run(){
						 new Game(setting,wm,board);
						}
					};
					new Thread(rnbl).start();
				
			}
		});

	}
	
	@Override
	public void setFrame(JFrame frame) {
		frame.setContentPane(pane);
	}

}
