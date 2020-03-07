
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Tire extends JComponent {
	//Ignore
	private static final long serialVersionUID = 1L;
	/**
	 * Start of the tire, x coordinate
	 */
	private double xStart;
	/**
	 * Start of the tire, y coordinate
	 */
	private double yStart;
	/**
	 * The two gradients on the tire. The Tire is split into two ranges
	 * and each range is given a color range.
	 */
	private GradientPaint gradient1, gradient2;
	public static double width = 250;
	public static double height = 300;
	
	@Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double w = xStart + width;
        double h = yStart + height;
		xStart = 50;
        yStart = 50;
        g2.setPaint(gradient1);
        g2.setStroke(new BasicStroke(2.0f));
        RoundRectangle2D shape = new RoundRectangle2D.Double(xStart, yStart, w, h, 50, 50);
        g2.draw(shape);
        g2.fill(shape);
        
        Rectangle rect = new Rectangle();
        g2.setClip(shape);
        rect.setRect(xStart+width/2, yStart, w, h);
        g2.clip(rect);
        g2.setPaint(gradient2);
        g2.draw(shape);
        g2.fill(shape);
    }

	public GradientPaint getGradient1() {
		return gradient1;
	}

	public void setGradient1(GradientPaint fill) {
		this.gradient1 = fill;
	}
	public GradientPaint getGradient2() {
		return gradient2;
	}

	public void setGradient2(GradientPaint fill) {
		this.gradient2 = fill;
	}
}
