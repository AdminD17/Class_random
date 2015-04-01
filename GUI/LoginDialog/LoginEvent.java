package ExtraClass.GUI.LoginDialog;

import java.util.EventObject;

public class LoginEvent extends EventObject {

	/**
	 * Clase que extiende de un objeto evento.
	 * Almacena el estado actual de los campos de entrada
	 * 
	 * @author David Giordana
	 * 
	 */
	
	private static final long serialVersionUID = 2862971543752652818L;

	/**
	 * usuario actual ingresado
	 */
	private String user;
	
	/**
	 * contraseña actual ingresada
	 */
	private String password;
	
	/**
	 * Constructor de la clase
	 * @param source objeto que ejecuta el evento
	 * @param usr usuario actual
	 * @param pass contraseña actual
	 */
	public LoginEvent(Object source , String usr , char [] pass) {
		super(source);
		user = usr;
		password = new String(pass);
	}
	
	/**
	 * retorna el usuario actual
	 * @return usuario actual
	 */
	public String getUser(){
		return user;
	}

	/**
	 * retorna la contraseña actual
	 * @return contraseña actual
	 */
	public String getPassword(){
		return password;
	}
	
}
