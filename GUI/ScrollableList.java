package ExtraClass.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Lista de Strings con barra de desplazamiento
 * 
 * @author David Giordana
 * 
 */
public class ScrollableList extends JPanel{

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
		//Instancia los componentes
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

	/**
	 * Retorna el objeto JList del panel
	 * @return objeto JList
	 */
	public JList<String> getList(){
		return list;
	}
	
	/**
	 * Retorna el modelo de la lista
	 * @return modelo de lista
	 */
	public DefaultListModel<String> getModel(){
		return model;
	}
	
	/**
	 * Habilita o deshabilita la lista
	 * @param b Indica estado a setear
	 */
	@Override
	public void setEnabled(boolean b){
		super.setEnabled(b);
		label.setEnabled(b);
		list.setEnabled(b);
		scroll.setEnabled(b);
	}
	
}
