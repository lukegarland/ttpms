
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Tire extends JComponent {
	private static final long serialVersionUID = 1L;
	private double xCenter;
	private double yCenter;
	private GradientPaint gradient;
	public static double width = 250;
	public static double height = 300;
	
	@Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(gradient);
        g2.setStroke(new BasicStroke(2.0f));

        xCenter = 50;
        yCenter = 50;
        double w = xCenter + width;
        double h = yCenter + height;
        g2.draw(new RoundRectangle2D.Double(xCenter, yCenter, w, h, 50, 50));
        g2.fill(new RoundRectangle2D.Double(xCenter, yCenter, w, h, 50, 50));
    }

	public GradientPaint getGradient() {
		return gradient;
	}

	public void setGradient(GradientPaint fill) {
		this.gradient = fill;
	}
	
}
