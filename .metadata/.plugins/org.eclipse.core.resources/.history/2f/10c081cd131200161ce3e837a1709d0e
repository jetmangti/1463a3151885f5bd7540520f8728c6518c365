import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowManager implements ActionListener
{
	private Menu main;
	private Credits credit;
	private Stats stat;
	private NewGame ngame;
	private JFrame frame;
	private settings set;
	private gameSetting setting;
	private LoadingScreen loading;
	private Game[] game;
	private int counter= 0;
	private boolean state = false;
	private JButton dummy;
	private WindowManager wm;
	
	public WindowManager(JFrame frame) 
	{
		this.dummy = new JButton();
		this.dummy.addActionListener(this);
		this.dummy.setActionCommand("GameD");
		this.setting = new gameSetting();
		this.frame = frame;
		this.main = new Menu(this.frame,this);
		/*credit = new Credits(this.frame);*/
		main.setFrame(this.frame);
		wm = this;
	}

	public gameSetting getSettings()
	{
		return this.setting;
	}
	public void endGame(int teamId, int bStat, int wStat)
	{
		this.frame.setVisible(true);
		this.frame.getContentPane().removeAll();
		this.stat = new Stats(this.frame,this,teamId,bStat,wStat);
		this.stat.setFrame(this.frame);
		this.frame.getContentPane().revalidate();
		this.frame.getContentPane().repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getActionCommand() == "MenuD")
		{
			this.frame.getContentPane().removeAll();
			this.main = new Menu(this.frame,this);
			this.main.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "CreditsD")
		{
			this.frame.getContentPane().removeAll();
			this.credit = new Credits(this.frame,this);
			this.credit.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "NewD")
		{
			this.frame.getContentPane().removeAll();
			this.ngame = new NewGame(this.frame,this);
			this.ngame.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "SetAD")
		{
			this.frame.getContentPane().removeAll();
			this.set = new settings(0, this.frame, this,this.setting);
			this.set.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "SetHD")
		{
			this.frame.getContentPane().removeAll();
			this.set = new settings(1, this.frame, this,this.setting);
			this.set.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "chck_1")
		{
			this.state = !this.state;
			
		}
		else if(e.getActionCommand() == "HideMD")
		{
			this.frame.setVisible(false);
			this.frame.getContentPane().removeAll();
			this.main = new Menu(this.frame,this);
			this.main.setFrame(this.frame);
		}
		if(e.getActionCommand() == "LoadingD")
		{
			this.loading = new LoadingScreen(frame, wm);
			this.loading.setFrame(frame);
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
		//	EventQueue.invokeLater( new Runnable(){
		//		public void run(){
				/*LoadingScreen ls = new LoadingScreen(frame, wm);
				ls.setFrame(frame);*/
		//		frame.getContentPane().revalidate();
		//		frame.getContentPane().repaint();
		//		}
		//	});
			//new Thread(a).start();
			//LoadingScreen(this.frame, this);
			//this.loading.setFrame(this.frame);
			//this.frame.getContentPane().revalidate();
			//this.frame.getContentPane().repaint();
		//}

		///else if(e.getActionCommand() == "GameD")
		///{
			///this.set.setLoading();
			///game = new Game[this.setting.getInstancies()];
			
				///	for(int i=0; i<setting.getInstancies(); ++i)
				///	{
				///		game[i] = new Game(setting);
				///		new Thread(game[i]).start();
				///	}
				//}
		//	};
			//this.main = new Menu(this.frame,this);
			//this.main.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "LoadD")
		{
			
		}
		else if(e.getActionCommand() == "SaveD")
		{
		
		}
		else if(e.getActionCommand() == "setAi")
		{
			
		}
		else if(e.getActionCommand() == "setHum")
		{
			
		}
		this.frame.getContentPane().revalidate();
		this.frame.getContentPane().repaint();
		// TODO Auto-generated method stub
		
	}
}
