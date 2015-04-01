package ExtraClass.GUI.LoginDialog;

public interface LoginListener {
	
	/**
	 * Interface con los metodos de un evento de logueo
	 * 
	 * @author David Giordana
	 * 
	 */

	/**
	 * metodo que se activa cuando se intenta loguear al usuario
	 * @param e objeto con la informacion del evento
	 */
	public void loginEvent(LoginEvent e);
	
	/**
	 * metodo que se activa cando se aborta el logueo
	 * @param e objeto con la informacion del evento
	 */
	public void abortEvent(LoginEvent e);
	
	/**
	 * metodo que se ejecuta cuando se intenta registrar un usuario
	 * @param e objeto con la informacion del evento
	 */
	public void registerEvent(LoginEvent e);
	
}
