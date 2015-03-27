package ListPanel;

public interface ListPanelListener {
	
	/**
	 * Interface que se implementara a la hora de
	 * producirse un evento de ListPanel
	 * 
	 * @author David Giordana
	 * 
	 */
	
	/**
	 * se ejecuta cuando se agrega un item a la lista
	 * @param e objeto con la informacion del evento
	 */
	public void addItemEvent(ListPanelEvent e);
	
	/**
	 * se ejecuta cuando se remueve un item de la lista
	 * @param e objeto con la informacion del evento
	 */
	public void removeItemEvent(ListPanelEvent e);
	
	/**
	 * se ejecuta cuando se edita un item de la lista
	 * @param e objeto con la informacion del evento
	 */
	public void editItemEvent(ListPanelEvent e);
	
	/**
	 * se ejecuta cuando se selecciona un item de la lista
	 * @param e objeto con la informacion del evento
	 */
	public void selectElementEvent(ListPanelEvent e);

}
