
/**
 * @author lukeg
 * @since March 3, 2020
 */
public class ValueConverter {

	final static double SYSTEM_VOLTAGE = 5.0;
	final static int ADC_RESOLUTION = 1023;
	
	/**
	 * For Infineon KP235 Analog Absolute Pressure Sensor
	 * 
	 * @param ADCValue
	 * @return pressure value in kPa
	 */
	public static double convertTokPa(int ADCValue)
	{
		final double a = 0.01067;
		final double b = -0.32667;
		
		double voltage = ADCToVoltage(ADCValue);
		
		return (voltage / SYSTEM_VOLTAGE - b) / a ;
	}
	
	
	/**
	 * Based on voltage divider design
	 * @param ADCValue
	 * @return Ambient temperature in degrees C
	 */
	public static double convertAmbientTemperature(int ADCValue)
	{
		final int rBalance = (int)1e5;
		
		double measuredVoltage = ADCToVoltage(ADCValue);
		
		double resistance = (SYSTEM_VOLTAGE - measuredVoltage)*rBalance/measuredVoltage;
		
		double temperature = -(1/0.041)*Math.log( (resistance/1000.0) / 311.15);
		
		return temperature;
	}
	
	public static double convertIRTemperature(int ambientADCValue, int iRADCValue)
	{
		
		//TODO
		double ambientVoltage = ADCToVoltage(ambientADCValue);
		double iRvoltage = ADCToVoltage(iRADCValue);
	
		
		return 0.0;
	}
	
	public static double ADCToVoltage(int ADCValue)
	{
		return (ADCValue * SYSTEM_VOLTAGE) / ADC_RESOLUTION;
	}
	
}
