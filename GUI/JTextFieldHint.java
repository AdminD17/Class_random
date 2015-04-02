package ExtraClass.GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class JTextFieldHint extends JTextField implements FocusListener {

	/**
	 * Campo de texto con indicador
	 * 
	 * @author David Giordana
	 */

	/**
	 * *INFORMACION
	 * La clase genera un campo de texto con dindicador.
	 * Esta implementacion extiende de JTextField e incorpora
	 * los metodos y atributos necesarios para el funcionamiento.
	 *
	 * *ADVERTENCIA:
	 * *para obtener y setear el contenido del campo no se deben
	 * utilizar los metodos convencionales, sino los provistos por la
	 * implementacion (setContent , getContent)
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * ---- CONSTANTS
	 */
	
	/**
	 * fuente para foco perdido predefinido
	 */
	public static final Font FONT_LOST_DEFAULT = new Font("Monaco", Font.ITALIC, 14);

	/**
	 * fuente para foco ganado predefinido
	 */
	public static final Font FONT_GAINED_DEFAULT = new Font("Monaco", Font.PLAIN, 14);

	/**
	 * color para foco perdido predefinido
	 */
	public static final Color COLOR_LOST_DEFAULT = Color.LIGHT_GRAY;

	/**
	 * color para foco ganado predefinido
	 */
	public static final Color COLOR_GAINED_DEFAULT = Color.BLACK;
	
	/**
	 * color de estado error
	 */
	public static final Color ERROR_COLOR = new Color (255,204,204);

	/**
	 * color de estado correcto
	 */
	public static final Color OK_COLOR = new Color (204,255,204);

	/**
	 * ---- INFO COMPONENTS
	 */
	
	/**
	 * informacion para indicacion
	 */
	private String hint;

	/**
	 * fuente para foco perdido
	 */
	private Font Font_Lost;

	/**
	 * fuente para foco ganado
	 */
	private Font Font_Gained;

	/**
	 * color para foco perdido
	 */
	private Color Color_Lost;

	/**
	 * color para foco ganado
	 */
	private Color Color_Gained;

	/**
	 * ---- CONSTRUCTORS
	 */
	
	/**
	 * Constructor de la clase
	 * @param Font_Lost	fuente de foco perdido
	 * @param Font_Gained fuente de foco ganado
	 * @param Color_Lost fuente de color perdido
	 * @param Color_Gained fuente de color ganado
	 */
	public JTextFieldHint(Font Font_Lost , Font Font_Gained, Color Color_Lost , Color Color_Gained){
		this.addFocusListener(this);
		this.Font_Lost = Font_Lost;
		this.Font_Gained = Font_Gained;
		this.Color_Lost = Color_Lost;
		this.Color_Gained = Color_Gained;
		this.hint = "";
	}

	/**
	 * constructor de la clase
	 */
	public JTextFieldHint() {
		this(FONT_LOST_DEFAULT , FONT_GAINED_DEFAULT , COLOR_LOST_DEFAULT , COLOR_GAINED_DEFAULT);
	}

	/**
	 * ---- METHODS
	 */
	
	/**
	 * setea el indicador a mostrar
	 * @param hint texto del indicador
	 */
	public void setHint(String hint) {
		if(hint != null && hint.length() != 0){
			this.hint = hint;
			if(getText() == null || getText().length() == 0)
				this.setText(hint);
			this.correctSet();
		}
	}

	/**
	 * obtiene el texto indicador
	 * @return texto indicador
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * verifica si un string es el marcador seteado
	 * @param txt texto a comprobar
	 * @return true si el texto coincide con el marcador,
	 * false en caso contrario
	 */
	public boolean isHint(String txt){
		return this.getHint().equals(txt);
	}

	/**
	 * setea la tipografia del campo de texto
	 * @param focused si vale true tiene el foco
	 */
	private void setTypography(boolean focused){
		if(focused){
			setFont(Font_Gained);
			setForeground(Color_Gained);
		}
		else{
			setForeground(Color_Lost);
			setFont(Font_Lost);
		}
	}

	/**
	 * verifica valores internos y corrige detalles de interfaz
	 */
	private void correctSet(){
		setTypography(!isHint(getText()));
	}

	/**
	 * limpia el contenido del campo de texto
	 * mostrando el indicador
	 */
	public void clearField(){
		this.setText(getHint());
		this.setTypography(false);
	}

	/**
	 * setea un texto en el campo
	 * @param text texto a setear
	 */
	public void setContent(String text){
		this.setText(text);
		this.correctSet();
	}

	/**
	 * devuelve el contenido del campo de texto
	 * @return obtiene el contenido del campo
	 * si es igual al hint devuelve una cadena vacia
	 */
	public String getContent(){
		String get = getText();
		if(isHint(get))
			return "";
		return get;
	}
	
	/**
	 * setea el campo con el color OK
	 */
	public void setOkColor(){
		this.setBackground(OK_COLOR);
	}
	
	/**
	 * setea el campo con el color de ERROR
	 */
	public void setErrorColor(){
		this.setBackground(ERROR_COLOR);
	}

	/**
	 * --- LISTENERS FOR INTERNAL WORK
	 */
	
	@Override
	public void focusGained(FocusEvent e) {
		//si el contenido del field es el indicador
		if (isHint(getText())) {
			this.setText("");
			this.correctSet();
		}
		//en caso de no ser un indicador
		else {
			this.correctSet();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(getText().length() <= 0){
			this.setText(hint);
		}
		this.correctSet();
	}

}
