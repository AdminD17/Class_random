package ExtraClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase con metodos y atributos para el calculo de fechas y cosas relacionadas
 * 
 * 
 * @author David Giordana
 *
 */
public class DateCalculator {

	/**
	 * ---- CONSTANTS
	 */

	// meses del año
	public static final String [] MONTH_SPANISH = {
		"Enero" ,
		"Febrero" ,
		"Marzo" ,
		"Abril" ,
		"Mayo" ,
		"Junio" ,
		"Julio" ,
		"Agosto" ,
		"Septiembre" ,
		"Octubre" ,
		"Noviembre" ,
		"Diciembre"
	};

	// dias de la semana en español
	public static final String [] DAYS_OF_WEEK_SPANISH = {
		"Domingo" ,
		"Lunes" ,
		"Martes" ,
		"Miercoles" ,
		"Jueves" ,
		"Viernes" ,
		"Sabado"
	};
	
	//patron de fecha 1
	public static final String DATE_FORMAT_1 = "yyyy-MM-dd-HH-mm-ss";

	/**
	 * ---- METHODS
	 */

	/**
	 * toma dos fechas y devuelve la cantidad de dias entre ellas
	 * @param date1 fecha 1
	 * @param date2 fecha 2
	 * @return dias entre las dos fechas
	 */
	public static int daysBetween(Calendar date1, Calendar date2){
		long diferencia = Math.abs(date1.getTimeInMillis() - date2.getTimeInMillis());
		return (int)(diferencia / (3600000 * 24));
	}

	/**
	 * toma fecha menor y fecha mayor devolviendo la cantidad de años entre las fechas
	 * @param fechaNacimiento fecha de nacimiento
	 * @param fechaActual fecha de referencia para comprobar
	 * @return
	 */
	public static int age(Calendar fechaNacimiento, Calendar fechaActual){
		int dif_anios = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		int dif_meses = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
		int dif_dias = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);
		if(dif_meses < 0 || (dif_meses == 0 && dif_dias < 0)){
			dif_anios--;
		}
		return dif_anios;
	}

	/**
	 * comprueba si el indice solicitado pertenece a un arreglo, en caso de no serlo
	 * se lanza una excepcion
	 * @param array arreglo a comprobar
	 * @param index indice a comprobar
	 */
	private static void checkRange(String [] array , int index){
		if(index < 0 || index != array.length){
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+array.length);
		}
	}

	/**
	 * convierte un indice en el nombre del mes segun el orden establecido
	 * por la clase @see Calendar
	 * @param month numero del mes segun calendar (0 - 11)
	 * @return nombre del mes correspondiente
	 */
	public static String getMonth(int month){
		checkRange(MONTH_SPANISH , month);
		return MONTH_SPANISH[month];
	}

	/**
	 * convierte un indice en el nomvre de un dia de la semana en orden 
	 * definido por la clase @see Calendar 
	 * @param day - indice del dia solicitado
	 * @return nombre del dia solicitado
	 */
	public static String getDayOfWeek(int day){
		checkRange(DAYS_OF_WEEK_SPANISH , day - 1);
		return DAYS_OF_WEEK_SPANISH[day - 1];
	}

	/**
	 * convierte una cadena de texto a una fecha
	 * @param str cadena a analizar
	 * @param pattern pratron para el analisis
	 * @return objeto calendario obtenido de la conversion
	 */
	public static Calendar stringToCalendar(String str , String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar cal = new GregorianCalendar();
		try {
			cal.setTime(format.parse(str));
		} catch (ParseException e) {}
		return cal;
	}
	
	/**
	 * convierte un objeto calendario a una cadena de texto
	 * @param cal objeto calendario
	 * @param pattern patron para la conversion
	 * @return cadena de texto resultante
	 */
	public static String calendarToString(Calendar cal , String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}
	
	/**
	 * convierte una cadena de texto a una fecha
	 * @param str cadena a analizar
	 * @param pattern indice del pratron para el analisis
	 * @return objeto calendario obtenido de la conversion
	 */
	public static Calendar stringToCalendar(String str, int pattern){
		if(pattern == 1){
			return stringToCalendar(str , DATE_FORMAT_1);
		}
		else{
			throw new IllegalArgumentException("Patrón \"" + pattern + "\" inváido");
		}
	}
	
	/**
	 * convierte un objeto calendario a una cadena de texto
	 * @param cal objeto calendario
	 * @param pattern indice del patron para la conversion
	 * @return cadena de texto resultante
	 */
	public static String calendarToString(Calendar cal , int pattern){
		if(pattern == 1){
			return calendarToString(cal , DATE_FORMAT_1);
		}
		else{
			throw new IllegalArgumentException("Patrón \"" + pattern + "\" inváido");
		}
	}



}
