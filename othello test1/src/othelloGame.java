import java.awt.Color;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

/*
 * Martin Hlipala xhlipa00
 * Adam Bak xbakad00
 * All rights reserved
 */
/*
 * This is the main class, bootstrap of the whole program...
 * 
 */
public class othelloGame {
 
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
	private JFrame frmOthello;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					othelloGame window = new othelloGame();
					window.frmOthello.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public othelloGame() {
		initialize();
	}
	public void setWindowShape(Shape shp)
	{
		frmOthello.setShape(shp);
	}
	public void loadPane(Container contentPane)
	{
		frmOthello.setContentPane(contentPane);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frmOthello = new JFrame();
		frmOthello.setForeground(Color.WHITE);
		frmOthello.setBackground(Color.WHITE);
		//frmOthello.getContentPane().setBackground(Color.WHITE);
		frmOthello.setUndecorated(true);
		frmOthello.setShape(new RoundRectangle2D.Double(0,0,622,388,100,100));
		frmOthello.setTitle("Othello");
		frmOthello.setResizable(true);
		frmOthello.setBounds(100, 100,622, 430);//622 388
		frmOthello.setLocationRelativeTo(null);
		frmOthello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WindowManager wm = new WindowManager(frmOthello);		
	}
}
