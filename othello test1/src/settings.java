import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class settings extends JFrame implements window 
{

	private gameSetting set ;
	private JLayeredPane pane;
	private int margin = 30;
	private int margin_left = 280;
	private int margin_top = 140;
	private int margin_left_sub = 30;
	private int spacing = 200;
	private ImageIcon brand = new ImageIcon(getClass().getResource("data/images/st_brand.jpg"));
	private ImageIcon footer = new ImageIcon(getClass().getResource("data/images/footer.png"));
	private JSpinner f_B;
	private JSpinner f_I;
	private JSpinner f_stones;
	private JLabel max_stones;
	private JLabel max_B;
	private JLabel max_I;
	private JLabel backLabel;
	private boolean stat = false;
	private JSpinner inst;
	private JSpinner spinner;
	private int mode;
	
	private ImageIcon play0 = new ImageIcon(getClass().getResource("data/buttons/p_1.png"));
	private ImageIcon play1 = new ImageIcon(getClass().getResource("data/buttons/p_0.png"));
	private ImageIcon play2 = new ImageIcon(getClass().getResource("data/buttons/p_2.png"));
	private ImageIcon bck_0 = new ImageIcon(getClass().getResource("data/buttons/circle.png"));
	private ImageIcon bck_2 = new ImageIcon(getClass().getResource("data/buttons/6_1.png"));
	private ImageIcon bck_1 = new ImageIcon(getClass().getResource("data/buttons/6_2.png"));
	private ImageIcon backLabelIcon = new ImageIcon(getClass().getResource("data/buttons/bck_lbl.png"));
	/*private ImageIcon body = new ImageIcon(getClass().getResource("data/images/ai_human.png"));
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
	private JLabel pwh_label;*/
	
	public settings(int mode, JFrame frame, WindowManager w, gameSetting setting)
	{
		this.mode = mode;
		this.set = setting;
		buildGUI(frame, w);
	}
	
	public void setFreezing(boolean a)
	{
		this.max_B.setEnabled(a);
		this.max_I.setEnabled(a);
		this.max_stones.setEnabled(a);
		this.f_B.setEnabled(a);
		this.f_I.setEnabled(a);
		this.f_stones.setEnabled(a);
	}
	public void buildGUI(JFrame form, WindowManager w)
	{
		pane = new JLayeredPane();
		JLabel label = new JLabel(brand);
		label.setBounds(0, 0, brand.getIconWidth(), brand.getIconHeight());
		pane.add(label, 0);
		
		JLabel foot = new JLabel(footer);
		foot.setBounds(0, form.getHeight() - footer.getIconHeight(), footer.getIconWidth(), footer.getIconHeight());
		pane.add(foot,  0);
		
		/*JLabel bdy = new JLabel(body);
		bdy.setBounds(Math.round(form.getWidth()/1.5f), (brand.getIconHeight()/4)*5, body.getIconWidth(), body.getIconHeight());
		pane.add(bdy,  0);*/
		JLabel size_label = new JLabel("Size of map: ");
		size_label.setBounds(margin_left,margin_top,size_label.getPreferredSize().width,size_label.getPreferredSize().height);
		pane.add(size_label, 0);
		
		List<Integer> values = new ArrayList<Integer>();
		values.add(6);
		values.add(8);
		values.add(10);
		values.add(12);
		spinner = new JSpinner(new SpinnerListModel(values));
		//spinner.getNextValue();
		spinner.setValue(8);
		
		spinner.setBounds(size_label.getBounds().x+spacing, size_label.getBounds().y, 40, 20);
		pane.add(spinner, 0);
		
		
		JLabel linst = new JLabel("Num of game instancies: ");
		linst.setBounds(margin_left,size_label.getBounds().y+this.margin,linst.getPreferredSize().width,linst.getPreferredSize().height);
		pane.add(linst, 0);
		
		/*NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(1);
		JFormattedTextField inst = new JFormattedTextField(nf);
		inst.setText("1");
		inst.setBounds(linst.getBounds().x+spacing, linst.getBounds().y, 70, inst.getPreferredSize().height);
		pane.add(inst,0);*/
		
		
		JLabel lfreeze = new JLabel("Freezing pawns: ");
		lfreeze.setBounds(margin_left,linst.getBounds().y+this.margin,lfreeze.getPreferredSize().width,lfreeze.getPreferredSize().height);
		pane.add(lfreeze, 0);
		
		JCheckBox freeze = new JCheckBox();
		freeze.addActionListener( new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				stat = !stat;
				setFreezing(stat);
			}
		
		}
		);
		freeze.setSelected(false);
		freeze.setContentAreaFilled(false);
		freeze.setBounds(lfreeze.getBounds().x+spacing, lfreeze.getBounds().y, freeze.getPreferredSize().width, freeze.getPreferredSize().height);
		pane.add(freeze,0);
		
		max_stones = new JLabel("Max freezed stones: ");
		max_stones.setBounds(margin_left+margin_left_sub,lfreeze.getBounds().y+this.margin,max_stones.getPreferredSize().width,max_stones.getPreferredSize().height);
		pane.add(max_stones, 0);
		
		SpinnerNumberModel mod1 = new SpinnerNumberModel(),mod2= new SpinnerNumberModel(),mod3 = new SpinnerNumberModel(),mod4 = new SpinnerNumberModel();
		mod1.setMinimum(0);
		mod2.setMinimum(0);
		mod3.setMinimum(0);
		mod4.setMinimum(1);
		f_stones = new JSpinner(mod1);
		f_stones.setValue(0);
		f_stones.setBounds(max_stones.getBounds().x+spacing, max_stones.getBounds().y, 70, f_stones.getPreferredSize().height);
		pane.add(f_stones,0);
		
		max_I = new JLabel("Max time to wait for freeze (I): ");
		max_I.setBounds(margin_left+margin_left_sub,max_stones.getBounds().y+this.margin,max_I.getPreferredSize().width,max_I.getPreferredSize().height);
		pane.add(max_I, 0);
		
		f_I = new JSpinner(mod2);
		f_I.setValue(0);
		f_I.setBounds(max_I.getBounds().x+spacing, max_I.getBounds().y, 70, f_I.getPreferredSize().height);
		pane.add(f_I,0);
		
		max_B = new JLabel("Max time to be stone freezed (B): ");
		max_B.setBounds(margin_left+margin_left_sub,max_I.getBounds().y+this.margin,max_B.getPreferredSize().width,max_B.getPreferredSize().height);
		pane.add(max_B, 0);
		
		f_B = new JSpinner(mod3);
		f_B.setValue(0);
		f_B.setBounds(max_B.getBounds().x+spacing, max_B.getBounds().y, 70, f_B.getPreferredSize().height);
		pane.add(f_B,0);
		
		inst = new JSpinner(mod4);
		inst.setValue(1);
		inst.setBounds(linst.getBounds().x+spacing, linst.getBounds().y, 70, inst.getPreferredSize().height);
		pane.add(inst,0);
		setFreezing(false);
		
		JButton button_play = new RoundButton(play0);
		button_play.setRolloverIcon(play1);
		button_play.setPressedIcon(play2);
		button_play.setBounds(70, form.getHeight()-245, play0.getIconWidth(), play0.getIconHeight());
		button_play.setBorderPainted(false);
		button_play.setContentAreaFilled(false);
		button_play.setBorder(BorderFactory.createEmptyBorder());
		button_play.addActionListener(w);
		button_play.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				set.setGameMode(mode);
				set.setB_var((int) f_B.getValue());
				set.setI_var((int) f_I.getValue());
				set.setStoneFreezing(freeze.isSelected());
				set.setSize((int)spinner.getValue());
				set.setInstancies((int)inst.getValue());
			}
			/*@Override
			public void mouseClicked(MouseEvent e)
			{
				set.setGameMode(mode);
				set.setB_var((int) f_B.getValue());
				set.setI_var((int) f_I.getValue());
				set.setStoneFreezing(freeze.isSelected());
				set.setSize((int)spinner.getValue());
				set.setInstancies((int)inst.getValue());
			}*/
		}
		);
		button_play.setActionCommand("GameD");
		pane.add(button_play, 1);
		
		JButton dec = new JButton(bck_0);
		dec.setRolloverIcon(bck_1);
		dec.setPressedIcon(bck_2);
		dec.setBorderPainted(false);
		dec.setContentAreaFilled(false);
		dec.setBorder(BorderFactory.createEmptyBorder());
		dec.addActionListener(w);
		dec.setActionCommand("NewD");
		dec.addMouseListener(new MouseAdapter() {
			  //@Override
			 /* public void mouseClicked(MouseEvent e) 
			  {
				  //form.dispose();
			  }*/
			  @Override
			  public void mouseEntered(MouseEvent e)
			  {
				  backLabel.setVisible(true);
			  }
			  @Override
			  public void mouseExited(MouseEvent e)
			  {
				  backLabel.setVisible(false);
			  }
			});
		dec.setBounds((button_play.getBounds().x-bck_0.getIconWidth()/6),(button_play.getBounds().y-bck_0.getIconHeight()/6), bck_0.getIconWidth(),bck_0.getIconHeight());
		pane.add(dec,2);
		
		backLabel = new JLabel(backLabelIcon);
		backLabel.setBounds(dec.bounds().x+70, dec.bounds().y-40, backLabelIcon.getIconWidth(), backLabelIcon.getIconHeight());
		backLabel.setVisible(false);
		pane.add(backLabel, 1);
	}
	@Override
	public void setFrame(JFrame frame) {
		frame.setContentPane(pane);
		
	}

}
