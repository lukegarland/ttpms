import java.util.InputMismatchException;
import java.util.Scanner;
import com.fazecast.jSerialComm.*;

public class TemperatureGUI {
	
	
	
	public static void main(String[] args) 
	{
              
        SerialPort port = getPortFromSystemIn();
        // enter into an infinite loop that reads from the port and updates the GUI
        Scanner data = new Scanner(port.getInputStream());
        
        
        while(data.hasNextLine()) 
        {
                System.out.println(data.nextLine());
        }
        
        data.close();
	}
	
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
	
	private static void printPorts()
	{
		 SerialPort ports[] = SerialPort.getCommPorts();
		 
	     int i = 1;
	     for(SerialPort port : ports) 
	     {
	                System.out.println(i++ + ":\t" + port.getDescriptivePortName());
	     }
	     
	}
}

