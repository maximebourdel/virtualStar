package maj_meteo;

import java.text.DecimalFormat;

public class MeteoUtils {
	
	/**
	 * permet de convertir une temperature en Celsius en Kelvin et inversement
	 * @param pDegre temperature a convertir
	 * @param pConversion true pour convertir le Celsius en Kelvin et false inversement
	 * @return temperature converties
	 */
	public static double convertCelsiusKelvin(double pDegre, Boolean pConversion){
		DecimalFormat df = new DecimalFormat("0.0");
		if(pConversion){
			pDegre=pDegre+273.15;
		}else{
			pDegre=pDegre-273.15;
		}
		return Double.parseDouble(df.format(pDegre));
	}
}
