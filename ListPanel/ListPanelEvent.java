package ListPanel;

import java.util.EventObject;

public class ListPanelEvent extends EventObject{
	
	/**
	 * Clase que extiende de EventObject utilizada 
	 * a la hora de implementar el ListPanelListener
	 * 
	 * @author David Giordana
	 * 
	 */
	
	private static final long serialVersionUID = -9080568480903086376L;

	/**
	 * indice del item en lista afectado
	 */
	private int index;
	
	/**
	 * elemento del item de la lista afectado
	 */
	private String element;

	/**
	 * Constructor del Evento
	 * @param source objeto que genero el evento
	 * @param index indice del item agfectado
	 * @param element elemento del item afectado
	 */
	public ListPanelEvent(Object source , int index , String element) {
		super(source);
		this.index = index;
		this.element = element;
	}
	
	/**
	 * retorna el indice del item afectado
	 * @return indice del item afectado
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * retorna el elemento del item afectado
	 * @return elemento del item afectado
	 */
	public String getElement(){
		return element;
	}

}
