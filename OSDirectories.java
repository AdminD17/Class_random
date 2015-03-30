

public class OSDirectories {
	
	/**
	 * Clase para obtener directorios del sistema operativo
	 * 
	 * @author David Giordana
	 * 
	 */
	
	/**
	 * indice del sistema operativo windows
	 */
	public static final short WINDOWS_OS = 0;
	
	/**
	 * indice del sistema operativo mac os
	 */
	public static final short MAC_OS = 1;
	
	/**
	 * indice del sistema operativo unix
	 */
	public static final short UNIX_OS = 2;
	
	/**
	 * indice de sistema operativo no reconocido
	 * ocurre en caso de no tratarse de sistema operativo
	 * windows , mac o unix
	 */
	public static final short OTHER_OS = -1;
	
	/**
	 * almacena el indice del sistema operativo
	 */
	private short os;
	
	/**
	 * contiene la instancia de la clase
	 */
	public static OSDirectories INS;
	
	/**
	 * devuelve una instancia unica de la clase
	 * @return instancia unica de la clase
	 */
	public static OSDirectories getInstance(){
		if(INS == null){
			INS = new OSDirectories();
		}
		return INS;
	}
	
	/**
	 * Constructor de la clase
	 */
	private OSDirectories(){
		String so = System.getProperty("os.name").toLowerCase();
		if (so.indexOf("win") >= 0){
			os = WINDOWS_OS;
		}
		else if (so.indexOf("mac") >= 0){
			os = MAC_OS;
		}
		else if ((so.indexOf("nix") >= 0) || (so.indexOf("nux") >= 0) || (so.indexOf("aix") >= 0)){
			os = UNIX_OS;
		}
		else{
			os = OTHER_OS;
		}
	}

	/**
	 * devuelve true si el sistma operativo es windows
	 * @return true si se esta utilizando el sistema operativo windows
	 */
	public boolean isWindows(){
		return os == WINDOWS_OS;
	}

	/**
	 * devuelve true si el sistma operativo es Mac osx
	 * @return true si se esta utilizando el sistema operativo Max osx
	 */
	public boolean isMac(){
		return os == MAC_OS;
	}

	/**
	 * devuelve true si el sistma operativo es Unix
	 * @return true si se esta utilizando el sistema operativo unix
	 */
	public boolean isUnix() {
		return os == UNIX_OS;
	}
	
	/**
	 * devuelve true si el sistema operativo no es windows, mac o linux
	 * @return true si el sistema operativo no es windows, mac o linux
	 */
	public boolean isOtherOS(){
		return os == OTHER_OS;
	}
	
	/**
	 * retorna la carpeta de trabajo
	 * @return carpeta de trabajo
	 */
	public String getFolderWork(){
		if(isWindows()){
			return System.getenv("AppData");
		}
		else if (isMac()){
			return System.getProperty("user.home") + "/Library/Application Support";
		}
		else if(isUnix()){
			return System.getProperty("user.home");
		}
		else
			return "";
	}

}
