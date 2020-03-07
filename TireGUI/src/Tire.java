
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Tire extends JComponent {
	private static final long serialVersionUID = 1L;
	private double xCenter;
	private double yCenter;
	private GradientPaint gradient1, gradient2;
	public static double width = 250;
	public static double height = 300;
	
	@Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double w = xCenter + width;
        double h = yCenter + height;
		xCenter = 50;
        yCenter = 50;
        g2.setPaint(gradient1);
        g2.setStroke(new BasicStroke(2.0f));
        RoundRectangle2D shape = new RoundRectangle2D.Double(xCenter, yCenter, w, h, 50, 50);
        g2.draw(shape);
        g2.fill(shape);
        
        Rectangle rect = new Rectangle();
        g2.setClip(shape);
        rect.setRect(xCenter+width/2, yCenter, width, height);
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
