import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowManager implements ActionListener
{
	private Menu main;
	private Credits credit;
	private NewGame ngame;
	private JFrame frame;
	private settings set;
	private gameSetting setting;
	private Game[] game;
	private int counter= 0;
	private boolean state = false;
	
	public WindowManager(JFrame frame) 
	{
		this.setting = new gameSetting();
		this.frame = frame;
		this.main = new Menu(this.frame,this);
		/*credit = new Credits(this.frame);*/
		main.setFrame(this.frame);
	}

	public settings getSettings()
	{
		return this.set;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.frame.getContentPane().removeAll();
		if(e.getActionCommand() == "MenuD")
		{
			this.main = new Menu(this.frame,this);
			this.main.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "CreditsD")
		{
			this.credit = new Credits(this.frame,this);
			this.credit.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "NewD")
		{
			this.ngame = new NewGame(this.frame,this);
			this.ngame.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "SetAD")
		{
			this.set = new settings(0, this.frame, this,this.setting);
			this.set.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "SetHD")
		{
			this.set = new settings(1, this.frame, this,this.setting);
			this.set.setFrame(this.frame);
		}
		else if(e.getActionCommand() == "chck_1")
		{
			this.state = !this.state;
			
		}
		this.frame.getContentPane().revalidate();
		this.frame.getContentPane().repaint();
		if(e.getActionCommand() == "GameD")
		{
			game = new Game[this.setting.getInstancies()];
			for(int i=0; i<this.setting.getInstancies(); ++i)
			{
				game[i] = new Game(this.setting);
			}
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
		// TODO Auto-generated method stub
		
	}
}
