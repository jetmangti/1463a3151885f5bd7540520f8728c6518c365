import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class Stats extends JFrame implements window{

	private JLayeredPane pane;
	private ImageIcon brand = new ImageIcon(getClass().getResource("data/images/brand.jpg"));
	private ImageIcon footer = new ImageIcon(getClass().getResource("data/images/footer.png"));
	private ImageIcon body = new ImageIcon(getClass().getResource("data/images/credits.png"));
	private ImageIcon bck0 = new ImageIcon(getClass().getResource("data/buttons/exit.png"));
	private ImageIcon bck1 = new ImageIcon(getClass().getResource("data/buttons/exit3.png"));
	private ImageIcon bck2 = new ImageIcon(getClass().getResource("data/buttons/exit2.png"));
	private ImageIcon decoration = new ImageIcon(getClass().getResource("data/buttons/circle.png"));
	private int teamId;
	private int bScore;
	private int wScore; 
	/*private ImageIcon btn1_0 = new ImageIcon(getClass().getResource("data/images/btn4_1.jpg"));
	private ImageIcon btn1_1 = new ImageIcon(getClass().getResource("data/images/btn4_2.jpg"));
	private ImageIcon btn1_2 = new ImageIcon(getClass().getResource("data/images/btn4_3.jpg"));
	private ImageIcon btn2_0 = new ImageIcon(getClass().getResource("data/images/btn5_1.jpg"));
	private ImageIcon btn2_1 = new ImageIcon(getClass().getResource("data/images/btn5_2.jpg"));
	private ImageIcon btn2_2 = new ImageIcon(getClass().getResource("data/images/btn5_3.jpg"));*/
	
	
	public Stats(JFrame frame, WindowManager w, int teamId, int bScore, int wScore) {
		this.teamId=teamId;
		this.wScore=wScore;
		this.bScore=bScore;
		buildGUI(frame,w);
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
		
		
		JLabel winner = new JLabel();
		Font f = new Font("DEFAULTFONT",Font.BOLD, 50);
		winner.setFont(f);
		if(bScore > wScore)
		{
			winner.setText("Black won!");
		}
		else if( bScore < wScore)
		{
			winner.setText("White won!");
		}
		else
		{
			winner.setText("Draw!");
		}
		winner.setBounds(260,130,500,90);
		pane.add(winner, 1);
		
		JLabel status = new JLabel("Black: "+this.bScore+" VS White: "+this.wScore);
		Font f2 = new Font("DEFAULTFONT",Font.BOLD, 20);
		status.setFont(f2);
		status.setBounds(280,220,500,90);
		pane.add(status, 1);
		
		JButton button_back = new RoundButton(bck0);
		button_back.setRolloverIcon(bck1);
		button_back.setPressedIcon(bck2);
		button_back.setBounds(70, form.getHeight()-245, bck0.getIconWidth(), bck0.getIconHeight());
		button_back.setBorderPainted(false);
		button_back.setContentAreaFilled(false);
		button_back.setBorder(BorderFactory.createEmptyBorder());
		button_back.addActionListener(w);
		button_back.setActionCommand("MenuD");
		pane.add(button_back, 1);
		
		JLabel dec = new JLabel(decoration);
		dec.setBounds((button_back.getBounds().x-decoration.getIconWidth()/6),(button_back.getBounds().y-decoration.getIconHeight()/6), decoration.getIconWidth(), decoration.getIconHeight());
		pane.add(dec,2);
	}
	@Override
	public void setFrame(JFrame frame) {
		frame.setContentPane(pane);
	}

}
