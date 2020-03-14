import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.fazecast.jSerialComm.*;

public class TemperatureGUI implements Constants{
	
	
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
        int [] dataFromADC = new int[6];
        int [][] lastValues = new int [5][NUMBER_OF_AVERAGES];
        Arrays.fill(lastValues[0], 0);
        Arrays.fill(lastValues[1], 0);
        Arrays.fill(lastValues[2], 0);
        Arrays.fill(lastValues[3], 0);
        Arrays.fill(lastValues[4], 0);


        int averageIndex = 0;
        while(data.hasNextLine()) 
        {
        	input = data.nextLine();
            System.out.println(input);
            splitData = input.split("\\s+");
            
            if(splitData.length == 6) 
            {
            	for(int i = 0; i<6; i++) 
            	{
            		try 
            		{
            			dataFromADC[i] = Integer.parseInt(splitData[i]);
            		}
            		catch(NumberFormatException nfe) {
            			break;
            		}
            	}
            	
            	lastValues[0][averageIndex % NUMBER_OF_AVERAGES] = dataFromADC[1];
            	lastValues[1][averageIndex % NUMBER_OF_AVERAGES] = dataFromADC[2];
            	lastValues[2][averageIndex % NUMBER_OF_AVERAGES] = dataFromADC[3];
            	lastValues[3][averageIndex % NUMBER_OF_AVERAGES] = dataFromADC[4];
            	lastValues[4][averageIndex % NUMBER_OF_AVERAGES] = dataFromADC[5];         	
            	
            	averageIndex++;
            	if(averageIndex == Integer.MAX_VALUE)
            		averageIndex = 0;
            	
            	int pressure = IntStream.of(lastValues[0]).sum() / NUMBER_OF_AVERAGES;
            	int ambientTemp = IntStream.of(lastValues[1]).sum() / NUMBER_OF_AVERAGES;
            	int leftTemp = IntStream.of(lastValues[2]).sum() / NUMBER_OF_AVERAGES;
            	int centerTemp = IntStream.of(lastValues[3]).sum() / NUMBER_OF_AVERAGES;
            	int rightTemp = IntStream.of(lastValues[4]).sum() / NUMBER_OF_AVERAGES;

            	gui.updateTire(gui.getFrontLeft(), leftTemp, centerTemp, rightTemp);
            	gui.updateText(ValueConverter.convertToPSI(pressure), ValueConverter.convertIRTemperature(ambientTemp, 5), ValueConverter.convertIRTemperature(ambientTemp, centerTemp), ValueConverter.convertIRTemperature(ambientTemp, rightTemp));
            	
            	gui.updateFrame();
            }
        }
        data.close();
        port.closePort();
	}
	/**
	 * Asks the user to select a port from the list of available ports
	 * @return SerialPort selected by the user
	 */
	private static SerialPort getPortFromSystemIn()
	{

	     System.out.println("Select a port:");

	     int numOfPorts = printPorts();
	     int chosenPort = 1;
	     
	     if(numOfPorts > 1) {
	     
	     Scanner s = new Scanner(System.in);
	     
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
	     }
	     
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
	private static int printPorts()
	{
		 SerialPort ports[] = SerialPort.getCommPorts();
		 
	     int i = 1;
	     for(SerialPort port : ports) 
	     {
	                System.out.println(i++ + ":\t" + port.getDescriptivePortName());
	     }
	     return --i;
	}
}

