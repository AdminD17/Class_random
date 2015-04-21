package ExtraClass.GUI.DnDFilePanel;

import java.io.File;
import java.util.EventObject;
import java.util.List;

/**
 * Clase que extiende de @see EventObject que almacena la
 * informacin de los archivos arrastrados al panel de entrada
 * 
 * @author David Giordana
 *
 */
public class DnDFileEvent extends EventObject{

	private static final long serialVersionUID = -2263627191995615477L;
	
	/**
	 * --- ATTRIBUTES
	 */
	
	//lista de archivos arrastrados al panel de entrada
	private List<File> files;
	
	/**
	 * ---- CONSTRUCTOR
	 */
	
	/**
	 * Constructor de la clase
	 * @param source componente que genero el evento
	 * @param files lista de archivos que generaron el evento
	 */
	public DnDFileEvent(Object source , List<File> files) {
		super(source);
		this.files = files;
	}

	/**
	 * ---- GETTERS & SETTERS
	 */
	
	/**
	 * Retorna la lista de archivos que indujo el evento
	 * @return lista de archivos
	 */
	public List<File> getFiles(){
		return files;
	}
	
}
