package ExtraClass.GUI.ListPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ExtraClass.GUI.JTextFieldHint;
import ExtraClass.GUI.ScrollableList;


public class ListPanel extends JPanel {

	/**
	 * Este panel contiene una lista con scroll,
	 * con la capacidad de incorporar , elimintar y
	 * editar items
	 *
	 * @author David Giordana
	 *
	 */

	private static final long serialVersionUID = -2323273332935655093L;

	/**
	 * texto por defecto para boton añadir item
	 */
	public static final String ADD_TEXT = "+";

	/**
	 * texto por defecto para boton eliminar item
	 */
	public static final String REMOVE_TEXT = "-";

	/**
	 * texto por defecto para boton editar item
	 */
	public static final String EDIT_TEXT = "Editar";

	/**
	 * texto por defecto para el boton aceptar
	 */
	public static final String OK_TEXT = "OK";

	/**
	 * texto marcador para campo de texto
	 */
	public static final String HINT = "Item :";

	/**
	 * lista con scroll
	 */
	private ScrollableList list;

	/**
	 * campo de texto con indicador
	 */
	private JTextFieldHint textField;

	/**
	 * boton para añadir item
	 */
	private JButton addItem;

	/**
	 * boton para eliminar item
	 */
	private JButton removeItem;

	/**
	 * boton para editar campo
	 */
	private JButton editItem;

	/**
	 * boton de aceptar
	 */
	private JButton acept;

	/**
	 * listener de la clase
	 */
	private ListPanelListener listener;

	/**
	 * listener por defecto
	 */
	private ListPanelListener defListener;

	/**
	 * variable de control que marca si se esta creando un nuevo item
	 */
	private boolean isCreating;

	/**
	 * variable de control que marca si se esta editando un item
	 */
	private boolean isEditing;

	/**
	 * limitador para el layout
	 */
	private GridBagConstraints gbc;

	/**
	 * Constructor de la clase
	 * @param addText texto para boton añadir
	 * @param removeText texto para boton eliminar
	 * @param editText texto para boton editar
	 * @param hint texto indicador para campo de texto
	 * @param okBut texto del boton de aceptar
	 */
	public ListPanel(String addText , String removeText , String editText , String hint , String okBut){
		//intasncia los componentes de la clase
		gbc = new GridBagConstraints();
		defListener = new ListPanelListener(){
			@Override
			public void addItemEvent(ListPanelEvent e) {}
			@Override
			public void removeItemEvent(ListPanelEvent e) {}
			@Override
			public void editItemEvent(ListPanelEvent e) {}
			@Override
			public void selectElementEvent(ListPanelEvent e) {}
		};
		listener = defListener;
		setTextIn(hint , okBut);
		setScrollableList();
		setAddButton(addText);
		setRemoveButton(removeText);
		setEditButton(editText);
		setInEnabled(false);
		//agrega los componentes al panel
		this.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill= GridBagConstraints.BOTH;
		this.add(list , gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.add(editItem , gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.add(removeItem , gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.add(addItem , gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.add(textField , gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.add(acept , gbc);
	}

	/**
	 * Constructor de la clase
	 */
	public ListPanel(){
		this(ADD_TEXT, REMOVE_TEXT, EDIT_TEXT , HINT , OK_TEXT);
	}

	/**
	 * setea el texto del boton aceptar
	 * @param str texto a setear
	 */
	public void setOkButtonText(String str){
		acept.setText(str);
	}

	/**
	 * setea el marcador del campo de texto
	 * @param str texto a setear
	 */
	public void setHint(String str){
		textField.setHint(str);
	}

	/**
	 * activa o desactiva el ingreso via el campo de texto
	 * para el usuario
	 * @param b true si se desea permitir ingresar informacion,
	 * false en caso contrario
	 */
	public void setInEnabled(boolean b){
		textField.setEnabled(b);
		acept.setEnabled(b);
		addItem.setEnabled(!b);
		removeItem.setEnabled(!b);
		editItem.setEnabled(!b);
	}

	/**
	 * Setea el campo de texto
	 * @param hint texto indicador
	 * @param okBut texto para el boton aceptar
	 */
	private void setTextIn(String hint , String okBut){
		//instancia el objeto
		textField = new JTextFieldHint();
		if(hint != null)
			textField.setHint(hint);
		acept = new JButton(okBut);
		//agrega los listener
		ActionListener l = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getList().getSelectedIndex();
				String str = textField.getContent();
				ListPanelEvent event = new ListPanelEvent(
						this ,
						index ,
						str);
				//si se esta creando un item
				if(isCreating){
					list.getModel().addElement(str);
					isCreating = false;
					//activa el listener
					listener.addItemEvent(event);
					textField.clearField();
					setInEnabled(false);
				}
				//si se esta editando un item
				else if(isEditing && index >= 0){
					list.getModel().setElementAt(str, index);
					isEditing = false;
					//activa el listener
					listener.editItemEvent(event);
					textField.clearField();
					setInEnabled(false);
				}
			}
		};
		textField.addActionListener(l);
		acept.addActionListener(l);
	}

	/**
	 * setea la lista scrollable
	 */
	private void setScrollableList(){
		list = new ScrollableList();
		list.getList().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!isCreating && !isEditing){
					ListPanelEvent event = new ListPanelEvent(
							this ,
							list.getList().getSelectedIndex() ,
							list.getList().getSelectedValue());
					listener.selectElementEvent(event);
				}
			}
		});
	}

	/**
	 * setea el boton agregar item
	 * @param txt texto del boton
	 */
	private void setAddButton(String txt){
		addItem = new JButton(txt);
		addItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				isCreating = true;
				setInEnabled(true);
			}
		});
	}

	/**
	 * setea el boton de eliminar item
	 * @param txt texto del boton
	 */
	private void setRemoveButton(String txt){
		removeItem = new JButton(txt);
		removeItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getList().getSelectedIndex();
				if(index >= 0){
					ListPanelEvent event = new ListPanelEvent(
							this ,
							index ,
							list.getList().getSelectedValue());
					list.getModel().remove(index);
					listener.removeItemEvent(event);
				}

			}
		});
	}

	/**
	 * setea el boton editar item
	 * @param txt texto del boton
	 */
	private void setEditButton(String txt){
		editItem = new JButton(txt);
		editItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getList().getSelectedIndex() >= 0){
					textField.setContent(list.getList().getSelectedValue());
					isEditing = true;
					setInEnabled(true);
				}
			}
		});
	}

	/**
	 * agrega un listener a la clase
	 * @param l listener a setear
	 */
	public void addListPanelListener(ListPanelListener l){
		listener = l;
	}

	/**
	 * elimina el listener de la clase
	 */
	public void removeListPanelListener(){
		listener = defListener;
	}

	/**
	 * obtiene la lista con scroll del panel
	 * @return lista con scroll
	 */
	public ScrollableList getScrollableList(){
		return list;
	}

	/**
	 * obtiene el contenido de la entrada en caso de ser posible
	 * @return contenido de campo de entrada, "" si no esta en modo edicion
	 */
	public String getIn(){
		if(isEditing){
			return textField.getContent();
		}
		return null;
	}

	/**
	 * setea el contenido de la entrada de datos en caso de ser posible
	 * @param str texto a setear
	 */
	public void setIn(String str){
		if(isEditing){
			textField.setContent(str);
		}
	}

}
