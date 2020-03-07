import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DrawUI {
	private Tire frontLeft;
	JFrame frame;
	JPanel panel;
	public void initializeUI() {
		frame = new JFrame("Temperature GUI");
		panel = new JPanel();
		frontLeft = new Tire();
		GradientPaint fill = new GradientPaint(0,0,Color.BLACK,100, 0,Color.BLACK);
		frontLeft.setGradient1(fill);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.getContentPane().add(frontLeft, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
	}
	public void updateTire(Tire t, double outerTempScale, double centerTempScale, double innerTempScale) {
		GradientPaint fill = new GradientPaint(0,0,Color.BLUE,175, 0,Color.YELLOW);
		t.setGradient1(fill);
		frame.getContentPane().add(t, BorderLayout.CENTER);
		fill = new GradientPaint(175,0,Color.YELLOW,350, 0,Color.RED);
		t.setGradient2(fill);
		frame.getContentPane().add(t, BorderLayout.CENTER);
	}
	public Tire getFrontLeft() {
		return frontLeft;
	}
	public void setFrontLeft(Tire frontLeft) {
		this.frontLeft = frontLeft;
	}
}
