import java.util.InputMismatchException;
import java.util.Scanner;
import com.fazecast.jSerialComm.*;

public class TemperatureGUI {
	
	
	/**
	 * Main method of the GUI. Main opens the selected serial
	 * port and then loops through and acquires the serial input.
	 * Using the serial input, the data is then sent to the GUI 
	 * to update the temperature and pressure readings
	 * @param args default, not used
	 */
	public static void main(String[] args) 
	{
              
        SerialPort port = getPortFromSystemIn();
        if (port == null) {
        	System.out.println("Invalid port. Please restart the program.");
        	return;
        }
        // enter into an infinite loop that reads from the port and updates the GUI
        Scanner data = new Scanner(port.getInputStream());
        DrawUI gui = new DrawUI();
        gui.initializeUI();
        String input;
        String [] splitData;
        double [] scaleValues = new double[6];
        while(data.hasNextLine()) 
        {
        	input = data.nextLine();
            System.out.println(input);
            splitData = input.split("\\s+");
            if(splitData.length == 6) {
            	for(int i = 0; i<6; i++) {
            		try {
            			scaleValues[i] = scaleVoltage(Integer.parseInt(splitData[i]));
            		}
            		catch(NumberFormatException nfe) {
            			break;
            		}
            	}
            	gui.updateTire(gui.getFrontLeft(), scaleValues[3], scaleValues[4], scaleValues[5]);
            }
        }
        
        data.close();
	}
	/**
	 * Asks the user to select a port from the list of available ports
	 * @return SerialPort selected by the user
	 */
	private static SerialPort getPortFromSystemIn()
	{

	     System.out.println("Select a port:");

	     printPorts();
	     
	     Scanner s = new Scanner(System.in);
	     
	     int chosenPort = 0;
	     
	     while(true)
	     {    
	    	try 
	     	{
	    		 chosenPort = s.nextInt();
	    		 break;
	     	}
	     	catch(InputMismatchException e)
	     	{
	     		System.err.println("Error: Invalid input, try again");
	     	}
	     }
	     
	     s.close();
	     
	     // open and configure the port
	     SerialPort port = SerialPort.getCommPorts()[chosenPort - 1];
	     
	     
	     if(port.openPort())
	     {
	    	 System.err.println("Successfully opened the port.");
	     } 
	     else 
	     {
	    	 System.err.println("Unable to open the port.");
	     }

	     port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
	     
	     return port;
	        
	}
	/**
	 * Prints a list of the available ports that can be read serially
	 */
	private static void printPorts()
	{
		 SerialPort ports[] = SerialPort.getCommPorts();
		 
	     int i = 1;
	     for(SerialPort port : ports) 
	     {
	                System.out.println(i++ + ":\t" + port.getDescriptivePortName());
	     }
	     
	}
	/**
	 * Takes the ADC and scales it to a value between 0 and 1
	 * @param voltageADC ADC from the analog pins
	 * @return value between 0 and 1
	 */
	private static double scaleVoltage(int voltageADC) {
		return voltageADC/1023.0;
	}
}

