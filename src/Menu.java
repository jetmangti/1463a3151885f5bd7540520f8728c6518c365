import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu implements window{

	 private ImageIcon exitImg = null;
		private ImageIcon exitImg_click = null;
		private ImageIcon exitImg_over = null;
		private ImageIcon btn1_0 = null;
		private ImageIcon btn1_1 = null;
		private ImageIcon btn1_2 = null;
		private ImageIcon btn2_0 = null;
		private ImageIcon btn2_1 = null;
		private ImageIcon btn2_2 = null;
		private ImageIcon btn3_0 = null;
		private ImageIcon btn3_1 = null;
		private ImageIcon btn3_2 = null;
		private ImageIcon ng_lab = null;
		private ImageIcon lg_lab = null;
		private ImageIcon cr_lab = null;
		private ImageIcon img = new ImageIcon(getClass().getResource("data/images/brand.jpg"));
		private ImageIcon imgFooter = null;
		private ImageIcon imgbody = null;
		private int menuXAxisTrim = -2;
		private int menuYAxisTrim = -7;
		public JLabel lg_label = null;
		public JLabel cr_label = null;
		public JLabel ng_label = null;
		//private JPanel contentPane;
		private JLayeredPane pane;

	/**
	 * Create the frame.
	 */
	
	public Menu(JFrame form, WindowManager w) {
		buildMenu(form,w);
	}
	
	public void buildMenu(JFrame form, WindowManager w)
	{
		//frmOthello.getContentPane().setBackground(Color.WHITE);
		//frmOthello.getContentPane().setLayout(new BorderLayout(0, 0));
		pane = new JLayeredPane();
		//form.setContentPane(pane);
		//img = new ImageIcon(getClass().getResource("/resources/images/brand.jpg"));
		
		JLabel brandComp = new JLabel(img);
		brandComp.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		pane.add(brandComp,0);
		
	imgFooter = new ImageIcon(getClass().getResource("data/images/footer.png"));
		
		JLabel footerComp = new JLabel(imgFooter);
		footerComp.setBounds(0, form.getHeight() - imgFooter.getIconHeight(), imgFooter.getIconWidth(), imgFooter.getIconHeight());
		pane.add(footerComp,1);
		
		
		//JLabel lblCreditsAaaaAaa = new JLabel("Credits: aaaa aaa, aaaa aaa");
		//panel_1.add(lblCreditsAaaaAaa);
		
		/*JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pane.add(panel_2,1);*/
		
	imgbody = new ImageIcon(getClass().getResource("data/images/body.jpg"));
		
		JLabel bodyComp = new JLabel(imgbody);
		bodyComp.setBounds(Math.round(form.getWidth()/2.5f), (img.getIconHeight()/4)*5, imgbody.getIconWidth(), imgbody.getIconHeight());
		pane.add(bodyComp,3);
		
		/*JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		pane.add(panel_3,30);
		panel_3.setLayout(null);*/
		
		exitImg = new ImageIcon(getClass().getResource("data/buttons/exit3.png"));
		exitImg_click = new ImageIcon(getClass().getResource("data/buttons/exit2.png"));
		exitImg_over = new ImageIcon(getClass().getResource("data/buttons/exit.png"));
		btn1_0 = new ImageIcon(getClass().getResource("data/buttons/1_0.png"));
		btn1_1 = new ImageIcon(getClass().getResource("data/buttons/1_1.png"));
		btn1_2 = new ImageIcon(getClass().getResource("data/buttons/1_2.png"));
		btn2_0 = new ImageIcon(getClass().getResource("data/buttons/2_0.png"));
		btn2_1 = new ImageIcon(getClass().getResource("data/buttons/2_1.png"));
		btn2_2 = new ImageIcon(getClass().getResource("data/buttons/2_2.png"));
		btn3_0 = new ImageIcon(getClass().getResource("data/buttons/3_0.png"));
		btn3_1 = new ImageIcon(getClass().getResource("data/buttons/3_1.png"));
		btn3_2 = new ImageIcon(getClass().getResource("data/buttons/3_2.png"));
		ng_lab = new ImageIcon(getClass().getResource("data/buttons/ng_btn.png"));
		lg_lab = new ImageIcon(getClass().getResource("data/buttons/lg_btn.png"));
		cr_lab = new ImageIcon(getClass().getResource("data/buttons/cr_btn.png"));
		
		
		JButton exit_btn = new RoundButton(exitImg);
		exit_btn.setBounds(70, form.getHeight()-245, exit_btn.getIcon().getIconWidth(), exit_btn.getIcon().getIconHeight());
		exit_btn.setBorder(BorderFactory.createEmptyBorder());
		exit_btn.setContentAreaFilled(false);
		exit_btn.setHorizontalAlignment(SwingConstants.CENTER);
		exit_btn.setPressedIcon(exitImg_click);
		exit_btn.setRolloverIcon(exitImg_over);
		exit_btn.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) 
			  {
				  form.dispose();
			  }
			});
		pane.add(exit_btn,0);
		
		JButton btn_newgame = new JButton(btn3_0);
		btn_newgame.setBounds(exit_btn.bounds().x-31+menuXAxisTrim, exit_btn.bounds().y-13+menuYAxisTrim, btn_newgame.getIcon().getIconWidth(), btn_newgame.getIcon().getIconHeight());
		btn_newgame.setBorder(BorderFactory.createEmptyBorder());
		btn_newgame.setContentAreaFilled(false);
		btn_newgame.setPressedIcon(btn3_2);
		btn_newgame.setRolloverIcon(btn3_1);
		btn_newgame.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) 
			  {
				  form.dispose();
			  }
			  @Override
			  public void mouseEntered(MouseEvent e)
			  {
				  ng_label.setVisible(true);
			  }
			  @Override
			  public void mouseExited(MouseEvent e)
			  {
				  ng_label.setVisible(false);
			  }
			});
		btn_newgame.addActionListener(w);
		btn_newgame.setActionCommand("NewD");
		pane.add(btn_newgame,1);
		
		JButton btn_loadgame = new JButton(btn1_0);
		btn_loadgame.setBounds(exit_btn.bounds().x+21+menuXAxisTrim, exit_btn.bounds().y-24+menuYAxisTrim, btn_loadgame.getIcon().getIconWidth(),btn_loadgame.getIcon().getIconHeight());
		btn_loadgame.setBorder(BorderFactory.createEmptyBorder());
		btn_loadgame.setContentAreaFilled(false);
		btn_loadgame.setPressedIcon(btn1_2);
		btn_loadgame.setRolloverIcon(btn1_1);
		btn_loadgame.addActionListener(w);
		btn_loadgame.setActionCommand("LoadD");
		btn_loadgame.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) 
			  {
				  form.dispose();
			  }
			  @Override
			  public void mouseEntered(MouseEvent e)
			  {
				  lg_label.setVisible(true);
			  }
			  @Override
			  public void mouseExited(MouseEvent e)
			  {
				  lg_label.setVisible(false);
			  }
			});
		pane.add(btn_loadgame,1);
		
		JButton btn_credits = new JButton(btn2_0);
		btn_credits.setBounds(exit_btn.bounds().x-3+menuXAxisTrim, exit_btn.bounds().y+73+menuYAxisTrim, btn_credits.getIcon().getIconWidth(),btn_credits.getIcon().getIconHeight());
		btn_credits.setBorder(BorderFactory.createEmptyBorder());
		btn_credits.setContentAreaFilled(false);
		btn_credits.setPressedIcon(btn2_2);
		btn_credits.setRolloverIcon(btn2_1);
		btn_credits.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) 
			  {
				  form.dispose();
			  }
			  @Override
			  public void mouseEntered(MouseEvent e)
			  {
				  cr_label.setVisible(true);
			  }
			  @Override
			  public void mouseExited(MouseEvent e)
			  {
				  cr_label.setVisible(false);
			  }
			});
		btn_credits.addActionListener(w);
		btn_credits.setActionCommand("CreditsD");
		pane.add(btn_credits,1);
		
		ng_label = new JLabel(ng_lab);
		ng_label.setBounds(exit_btn.bounds().x-55+menuXAxisTrim, exit_btn.bounds().y-45+menuYAxisTrim, ng_lab.getIconWidth(), ng_lab.getIconHeight());
		ng_label.setVisible(false);
		pane.add(ng_label, 1);
		
		lg_label = new JLabel(lg_lab);
		lg_label.setBounds(exit_btn.bounds().x+110+menuXAxisTrim, exit_btn.bounds().y-47+menuYAxisTrim, lg_lab.getIconWidth(), lg_lab.getIconHeight());
		lg_label.setVisible(false);
		pane.add(lg_label, 1);
		
		cr_label = new JLabel(cr_lab);
		cr_label.setBounds(exit_btn.bounds().x+120+menuXAxisTrim, exit_btn.bounds().y+150+menuYAxisTrim, cr_lab.getIconWidth(), cr_lab.getIconHeight());
		cr_label.setVisible(false);
		pane.add(cr_label, 1);

	}

	@Override
	public void setFrame(JFrame form) 
	{	//form.removeAll();
		form.setContentPane(this.pane);
	/*	form.setUndecorated(true);
		form.setShape(new RoundRectangle2D.Double(0,0,622,388,100,100));*/
		form.setTitle("Othello");
		/*form.setResizable(true);
		form.setBounds(100, 100,622, 430);//622 388
		form.setLocationRelativeTo(null);
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		form.setForeground(Color.WHITE);
		form.setBackground(Color.WHITE);
		/*form.repaint();
		form.revalidate();
		form.getContentPane().repaint();
		form.getContentPane().revalidate();*/
		
	}
}
