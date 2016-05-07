import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

/*
 * Martin Hlipala xhlipa00
 * Adam Bak xbakad00
 * All rights reserved
 */

/*
 * This class represents credits window, that is possible to see after credits button press in game menu
 * it contains GUI positioning, widget creating, and placing the widgets to the approp. contentPane..
 */
public class Credits extends JFrame implements window{

	private JLayeredPane pane;
	private ImageIcon brand = new ImageIcon(getClass().getResource("data/images/cr_brand.jpg"));
	private ImageIcon footer = new ImageIcon(getClass().getResource("data/images/footer.png"));
	private ImageIcon body = new ImageIcon(getClass().getResource("data/images/credits.png"));
	private ImageIcon bck0 = new ImageIcon(getClass().getResource("data/buttons/back.png"));
	private ImageIcon bck1 = new ImageIcon(getClass().getResource("data/buttons/back3.png"));
	private ImageIcon bck2 = new ImageIcon(getClass().getResource("data/buttons/back_click.png"));
	private ImageIcon decoration = new ImageIcon(getClass().getResource("data/buttons/circle.png"));
	/*private ImageIcon btn1_0 = new ImageIcon(getClass().getResource("data/images/btn4_1.jpg"));
	private ImageIcon btn1_1 = new ImageIcon(getClass().getResource("data/images/btn4_2.jpg"));
	private ImageIcon btn1_2 = new ImageIcon(getClass().getResource("data/images/btn4_3.jpg"));
	private ImageIcon btn2_0 = new ImageIcon(getClass().getResource("data/images/btn5_1.jpg"));
	private ImageIcon btn2_1 = new ImageIcon(getClass().getResource("data/images/btn5_2.jpg"));
	private ImageIcon btn2_2 = new ImageIcon(getClass().getResource("data/images/btn5_3.jpg"));*/
	
	
	public Credits(JFrame frame, WindowManager w) {
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
		
		JLabel bdy = new JLabel(body);
		bdy.setBounds(Math.round(form.getWidth()/2.5f)-70, (brand.getIconHeight()/4)*5, body.getIconWidth(), body.getIconHeight());
		pane.add(bdy,  0);
		
		
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
