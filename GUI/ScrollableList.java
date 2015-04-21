package ExtraClass.GUI;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;


public class ScrollableList extends JPanel{

	/**
	 * Lista de Strings con barra de desplazamiento
	 * 
	 * @author David Giordana
	 */

	private static final long serialVersionUID = 1L;

	//etiqueta de la lista
	private JLabel label;

	// lista de strings
	private JList<String> list;

	// modelo de la lista
	private DefaultListModel<String> model;
	
	//scroll para lista
	private JScrollPane scroll;

	/**
	 * ---- CONSTRUCTORS
	 */
	
	/**
	 * Contructor de la clase
	 * @param label etiqueta de la lista
	 */
	public ScrollableList(String label){
		this.label = new JLabel(label);
		this.model = new DefaultListModel<String>();
		this.list = new JList<String>(model);
		this.scroll = new JScrollPane(list);
		//setea valores predeterminados de la clase
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		this.setLayout(new GridBagLayout());
		//agrega los componentes al panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.CENTER;
		this.add(this.label , gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill= GridBagConstraints.BOTH;
		this.add(scroll , gbc);
	}
	
	/**
	 * Constructor de la clase
	 */
	public ScrollableList(){
		this("");
	}

	/**
	 * ---- METHODS
	 */
	
	/**
	 * agrega un elemento a la lista
	 * @param element elemento a agregar
	 */
	public void add(String element){
		model.addElement(element);
	}

	/**
	 * limpia el contenido de la lista
	 */
	public void clear(){
		model.clear();
	}

	/**
	 * obtiene la cantidad de elementos de la lista
	 */
	public int getListSize(){
		return model.getSize();
	}

	/**
	 * agrega un elemento a la lista en una locacion especificada
	 * mediante el indice
	 * @param element elemento a agregar a la lista
	 * @param index indice del elemento a agregar
	 */
	public void addAt(String element , int index){
		model.insertElementAt(element, index);
	}

	/**
	 * modifica un item de la lista
	 * @param newElement nuevo valor del item de la lista
	 * @param index indice del elemento a modificar
	 */
	public void setElementAt(String newElement , int index){
		model.set(index, newElement);
	}

	/**
	 * retorna true si el elemento pasado pertenece a la lista
	 * @param element elemento a comprobar
	 * @return true si el elemento pertenece a la lista, false en caso contrario
	 */
	public boolean contains(String element){
		return model.contains(element);
	}

	/**
	 * obtiene un elemento especifico de la lista
	 * @param index indice del elemento a obtener
	 * @return elemento en la posicion solicitada
	 */
	public String get(int index){
		return model.getElementAt(index);
	}

	/**
	 * obtiene una ArrayList con todos los elementos en la lista  
	 * @return lista con los elementos añadidos
	 */
	public ArrayList<String> getAll(){
		return Collections.list(model.elements());
	}

	/**
	 * remueve un elemento de la lista
	 * @param element elemento a eliminar
	 */
	public void removeElement(String element){
		model.removeElement(element);
	}

	/**
	 * elimina un elemento de la lista segun su indice
	 * @param index indice del elemento a eliminar
	 * @return elemento eliminado
	 */
	public String removeIndex(int index){
		return model.remove(index);
	}

	/**
	 * obitiene el elemento seleccionado en la lista
	 * @return elemento seleccionado en la lista
	 */
	public String getSelectedValue(){
		return list.getSelectedValue();
	}

	/**
	 * obtiene el indice del elemento seleccionado en la lista
	 * @return indice del elemento seleccionado, retorna -1 en 
	 * caso de no estar ningun elemento seleccionado
	 */
	public int getSelectedIndex(){
		return list.getSelectedIndex();
	}

	/**
	 * añade un listener de seleccion a la lista
	 * @param listener listener de seleccion para lista
	 */
	public void addSelectionListener(ListSelectionListener listener){
		list.addListSelectionListener(listener);
	}

	/**
	 * setea la cantidad de filas visibles
	 * @param cant cantidad de filas visibles
	 */
	public void setVisibleRows(int cant){
		list.setVisibleRowCount(cant);
	}
	
	/**
	 * setea la etiqueta de la lista
	 * @param label etiqueta de la lista
	 */
	public void setLabel(String label){
		this.label.setText(label);
	}
	
	/**
	 * retorna la etiqueta de la lista
	 * @return etiqueta de la lista
	 */
	public String getLabel(){
		return this.label.getText();
	}

}
