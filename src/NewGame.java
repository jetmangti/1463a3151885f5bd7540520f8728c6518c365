import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class NewGame extends JFrame implements window{

	private JLayeredPane pane;
	private ImageIcon brand = new ImageIcon(getClass().getResource("data/images/brand.jpg"));
	private ImageIcon footer = new ImageIcon(getClass().getResource("data/images/footer.png"));
	private ImageIcon body = new ImageIcon(getClass().getResource("data/images/ai_human.png"));
	private ImageIcon bck0 = new ImageIcon(getClass().getResource("data/buttons/back.png"));
	private ImageIcon bck1 = new ImageIcon(getClass().getResource("data/buttons/back3.png"));
	private ImageIcon bck2 = new ImageIcon(getClass().getResource("data/buttons/back_click.png"));
	//private ImageIcon decoration = new ImageIcon(getClass().getResource("data/buttons/circle.png"));
	private ImageIcon btn1_0 = new ImageIcon(getClass().getResource("data/buttons/4_0.png"));
	private ImageIcon btn1_1 = new ImageIcon(getClass().getResource("data/buttons/4_1.png"));
	private ImageIcon btn1_2 = new ImageIcon(getClass().getResource("data/buttons/4_2.png"));
	private ImageIcon btn2_0 = new ImageIcon(getClass().getResource("data/buttons/5_0.png"));
	private ImageIcon btn2_1 = new ImageIcon(getClass().getResource("data/buttons/5_1.png"));
	private ImageIcon btn2_2 = new ImageIcon(getClass().getResource("data/buttons/5_2.png"));
	private ImageIcon labelAI = new ImageIcon(getClass().getResource("data/buttons/pwa_btn.png"));
	private ImageIcon labelHM = new ImageIcon(getClass().getResource("data/buttons/pwf_btn.png"));
	private JLabel pwa_label;
	private JLabel pwh_label;
	
	public NewGame(JFrame frame, WindowManager w)
	{
		buildGUI(frame, w);
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
		bdy.setBounds(Math.round(form.getWidth()/1.5f), (brand.getIconHeight()/4)*5, body.getIconWidth(), body.getIconHeight());
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
		
		
		JButton button_human = new JButton(btn1_0);
		button_human.setRolloverIcon(btn1_1);
		button_human.setPressedIcon(btn1_2);
		button_human.setBounds(36, form.getHeight()-280, btn1_0.getIconWidth(), btn1_0.getIconHeight());
		button_human.setBorderPainted(false);
		button_human.setContentAreaFilled(false);
		button_human.setBorder(BorderFactory.createEmptyBorder());
		button_human.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseEntered(MouseEvent e)
			  {
				  pwh_label.setVisible(true);
			  }
			  @Override
			  public void mouseExited(MouseEvent e)
			  {
				  pwh_label.setVisible(false);
			  }
			});
		button_human.addActionListener(w);
		button_human.setActionCommand("SetHD");
		pane.add(button_human, 2);
		
		JButton button_ai = new JButton(btn2_0);
		button_ai.setRolloverIcon(btn2_1);
		button_ai.setPressedIcon(btn2_2);
		button_ai.setBounds(36, form.getHeight()-180, btn1_0.getIconWidth(), btn1_0.getIconHeight());
		button_ai.setBorderPainted(false);
		button_ai.setContentAreaFilled(false);
		button_ai.setBorder(BorderFactory.createEmptyBorder());
		button_ai.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseEntered(MouseEvent e)
			  {
				  pwa_label.setVisible(true);
			  }
			  @Override
			  public void mouseExited(MouseEvent e)
			  {
				  pwa_label.setVisible(false);
			  }
			});
		button_ai.addActionListener(w);
		button_ai.setActionCommand("SetAD");
		pane.add(button_ai, 2);
		
		pwa_label = new JLabel(labelAI);
		pwa_label.setBounds(button_ai.bounds().x+170, button_ai.bounds().y+60, labelAI.getIconWidth(), labelAI.getIconHeight());
		pwa_label.setVisible(false);
		pane.add(pwa_label, 1);
		
		pwh_label = new JLabel(labelHM);
		pwh_label.setBounds(button_ai.bounds().x+170, button_ai.bounds().y-120, labelHM.getIconWidth(), labelHM.getIconHeight());
		pwh_label.setVisible(false);
		pane.add(pwh_label, 1);
	}
	@Override
	public void setFrame(JFrame frame) {
		frame.setContentPane(pane);
		
	}

}
