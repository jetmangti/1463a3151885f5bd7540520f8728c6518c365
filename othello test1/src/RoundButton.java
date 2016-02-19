import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RoundButton extends JButton
{
	private Shape circle;
	private Shape base;
	
	public RoundButton(ImageIcon a)
	{
		super(a);
		makeShape();
	}
	private void makeShape()
	{
		if(!this.getBounds().equals(this.base))
		{
			
			Dimension dim = this.getPreferredSize();
			this.base = this.getBounds();
			this.circle = new Ellipse2D.Float(0,0, dim.width-3,dim.height-3);
		}
	}
	@Override
	public boolean contains(int x, int y)
	{
		makeShape();
		return circle.contains(x, y);
	}
}
