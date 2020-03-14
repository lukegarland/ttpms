
/**
 * @author lukeg
 * @since March 3, 2020
 */
public class ValueConverter implements Constants{


	
	/**
	 * For Infineon KP235 Analog Absolute Pressure Sensor
	 * 
	 * @param ADCValue
	 * @return pressure value in kPa
	 */
	public static double convertToPSI(int ADCValue)
	{
		final double a = 0.01067;
		final double b = -0.32667;
		
		double voltage = ADCToVoltage(ADCValue);
		
		return ((voltage / SYSTEM_VOLTAGE - b) / a) / KPA_TO_PSI;
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
		final double sensitivityConstant = 0.0000000000015; //1.5 * 10^-12
		final int amplifierGain = 400;
		double ambientTemperature = 293; //convertAmbientTemperature(ambientADCValue) + 273; //In Kelvin
		double iRvoltage = ADCToVoltage(iRADCValue)/amplifierGain;
		//V = S*(To^4 - Ta^4) Solving for To^4
		iRvoltage = (iRvoltage/sensitivityConstant) + Math.pow(ambientTemperature, 4);
		//Now solving for To by itself
		iRvoltage = (Math.pow(iRvoltage, 0.25));	
		return iRvoltage-273;
	}
	
	public static double ADCToVoltage(int ADCValue)
	{
		return (ADCValue * SYSTEM_VOLTAGE) / ADC_RESOLUTION;
	}
	
}
