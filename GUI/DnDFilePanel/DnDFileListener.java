package ExtraClass.GUI.DnDFilePanel;

/**
 * Interfaz con los métodos que se deben ejecutar al arrastrar archivos a un
 * panel de entrada
 * 
 * @author David Giordana
 *
 */
public interface DnDFileListener {

	/**
	 * Se ejecuta en el momento en que se arrstra un archivo sobre
	 * un panel de entrada
	 * @param e objeto que contiene la informacion del evento
	 */
	public void filesDropped(DnDFileEvent e);
		
}
