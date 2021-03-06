import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DrawUI {
	private Tire frontLeft;
	private JFrame frame;
	private JPanel panel;
	private JLabel bottomText;
	/**
	 * Initializes the frame, tires and panel. The GUI is then
	 * displayed.
	 */
	public void initializeUI() {
		frame = new JFrame("Temperature GUI");
		panel = new JPanel();
		frontLeft = new Tire();
		
		bottomText = new JLabel("");

		
		bottomText.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		GradientPaint fill = new GradientPaint(0,0,Color.BLACK,100, 0,Color.BLACK);
		frontLeft.setGradient1(fill);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        bottomText.setLabelFor(frontLeft);
        frame.add(frontLeft, BorderLayout.CENTER);
        frame.add(bottomText, BorderLayout.WEST);
        frame.pack();
        frame.setSize(900, 500);
        frame.setVisible(true);
	}
	/**
	 * Updates the tire gradients based upon the new temperature values.
	 * @param t Tire to be updated
	 * @param leftTempScale 
	 * @param centerTempScale
	 * @param rightTempScale
	 */
	public void updateTire(Tire t, double leftTempScale, double centerTempScale, double rightTempScale) {
		Color colourOne, colourTwo, colourThree;
		colourOne = calculateColor(leftTempScale);
		colourTwo = calculateColor(centerTempScale);
		colourThree = calculateColor(rightTempScale);
		
		GradientPaint fill = new GradientPaint(0,0,colourOne,175, 0,colourTwo);
		t.setGradient1(fill);
		
		fill = new GradientPaint(175,0,colourTwo,350, 0,colourThree);
		t.setGradient2(fill);
	}
	
	
	public void updateText(double pressure, double leftTemp, double centerTemp, double rightTemp)
	{ 
		String text = String.format("Pressure: %.2f PSI<br/><br/>"
				+ "Inner Temp: %.2f C<br/>"
				+ " Middle Temp: %.2f C<br/>"
				+ "Outer Temp: %.2f C<br/>", pressure, leftTemp, centerTemp, rightTemp);
		bottomText.setText("<html><div style='text-align: left;'>" + text + "</div></html>");
	}
	
	public void updateFrame() 
	{
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	/**
	 * Returns a color from blue <----> green <----> red.
	 * The method takes the scale value and assigns a color 
	 * value based upon the above range.
	 * @param scale value between 0 and 1
	 * @return Color scaled to scale
	 */
	private Color calculateColor(double scale) {
		/* Define the MAXIMUM saturation of RED and GREEN shades
		 * Range (0-255)
		 */
		scale = scale/1023.0;
		if(scale > 1023 || scale < 0) {
			scale = 512;
		}
		final int RED_MAX = 255;
		final int BLUE_MAX = 255;
		final double MAX = 0.5;
		Color valColor;

		/* input scale varies from -MAX to MAX (-0.5-0.5)*/

		/* output calculateColor varies from
		 * -MAX = blue
		 *         ^
		 *         |
		 *         v
		 *    0 = green
		 *         ^
		 *         |
		 *         v
		 *  MAX = red
		 */

		/* Normalised normVal varies from -255 to 255 */
		scale-=MAX;
		int normVal = (int) ((scale*255)/MAX);
		//System.out.println(normVal);

		if(scale < 0) {
		    /* Make it blue-ish */
			valColor = new Color(0, 255 + normVal, BLUE_MAX);
		} else if (scale > 0) {
		    /* Make it red-ish */
			valColor = new Color(RED_MAX, 255 - normVal, 0);    
		                          
		} else {
		    /* Absolute Green */
		    valColor = Color.green;
		}
		return valColor;
	}
	public Tire getFrontLeft() {
		return frontLeft;
	}
	public void setFrontLeft(Tire frontLeft) {
		this.frontLeft = frontLeft;
	}
}
