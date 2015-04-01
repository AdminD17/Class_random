package ExtraClass.GUI.LoginDialog;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ExtraClass.GUI.JTextFieldHint;

public class LoginDialog extends JDialog{

	/**
	 * Clase que genera una ventana de logueo con ciertos parametro
	 * preestablecidos para simplificar su itilizacion en caso de ser necesario
	 * 
	 * @author David Giordana
	 * 
	 */

	private static final long serialVersionUID = 1658370022248766428L;

	/**
	 * ---- CONSTANTS
	 */

	/**
	 * modo con botones cancelar - registar - loguear
	 */
	public static final int CANCEL_REGISTER_LOGIN_MODE = 1;

	/**
	 * modo con botones cancelar - loguear
	 */
	public static final int CANCEL_LOGIN_MODE = 2;

	/**
	 * modo con boton loguear
	 */
	public static final int LOGIN_MODE = 3;

	/**
	 * ancho por defecto de la ventana
	 */
	public static final int WIDTH = 400;

	/**
	 * titulo por defecto de la ventana
	 */
	public static final String DEF_TITLE = "";

	/**
	 * description por defecto de la ventana
	 */
	public static final String DEF_DESCRIPTION = "";

	/**
	 * nombre de lcampo de usuario por defecto
	 */
	public static final String DEF_LOG_LAB = "User: ";

	/**
	 * nombre del campo de contraseña por defecto
	 */
	public static final String DEF_PASS_LAB = "Password: ";

	/**
	 * nombre del boton cancelar por defecto
	 */
	public static final String DEF_CANCEL = "Cancel";

	/**
	 * nombre del boton registrar por defecto
	 */
	public static final String DEF_REGISTER = "Sign Up";

	/**
	 * nombre del boton iniciar sesion por defecto
	 */
	public static final String DEF_LOGIN = "Log in";

	/**
	 * ---- GUI PARTS
	 */

	/**
	 * etiqueta con titulo de la ventana
	 */
	private JLabel title;

	/**
	 * descripcion de la ventana de logueo
	 */
	private JTextArea description;

	/**
	 * etiqueta de usuario
	 */
	private JLabel userLab;

	/**
	 * etiqueta de contraseña
	 */
	private JLabel passLab;

	/**
	 * campo de usuario
	 */
	private JTextFieldHint userField;

	/**
	 * campo de contraseña
	 */
	private JPasswordField passField;

	/**
	 * boton cancelar
	 */
	private JButton cancel;

	/**
	 * boton registrar
	 */
	private JButton register;

	/**
	 * boton loguear
	 */
	private JButton login;

	/**
	 * limitador de layout
	 */
	private GridBagConstraints gbc;

	/**
	 * ---- OTHER COMPONENTS
	 */

	/**
	 * lista de listaners
	 */
	private ArrayList<LoginListener> listListener;

	/**
	 * ---- CONSTRUCTORS
	 */

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 * @param title titulo de la ventana
	 * @param description descripcion de la ventana
	 * @param logLab etiqueta de usuario
	 * @param passLab etiqueta de contrasña
	 * @param cancel nombre del boton cancelar
	 * @param register nombre del boton registrar
	 * @param login nombre del boton loguear
	 * @param mode indica el modo para setear los botones
	 */
	public LoginDialog(Frame owner , String title , String description ,String logLab , String passLab , String cancel , String register , String login , int mode){
		//instancia los componentes de la clase
		super(owner);
		setTitleLabel(title);
		setDescriptionText(description);
		setUserField(logLab);
		setPassField(passLab);
		JPanel but = setButtons(cancel , register , login , mode);
		gbc = new GridBagConstraints();
		listListener = new ArrayList<LoginListener>();
		//agrega los componentes a la ventana
		this.getContentPane().setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(this.title , gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(this.description , gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.NONE;
		this.getContentPane().add(this.userLab , gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(this.userField , gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.NONE;
		this.getContentPane().add(this.passLab , gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.BOTH;
		this.getContentPane().add(this.passField , gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(but , gbc);
		//setea los parametros de la ventana
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setSize(new Dimension(WIDTH , (int) this.getSize().getHeight()));
		this.setResizable(false);
	}

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 * @param logLab etiqueta de usuario
	 * @param passLab etiqueta de contrasña
	 * @param cancel nombre del boton cancelar
	 * @param register nombre del boton registrar
	 * @param login nombre del boton loguear
	 * @param mode indica el modo para setear los botones
	 */
	public LoginDialog(Frame owner , String logLab , String passLab , String cancel , String register , String login , int mode){
		this(owner , DEF_TITLE , DEF_DESCRIPTION , logLab , passLab , cancel , register , login , mode);
	}

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 * @param cancel nombre del boton cancelar
	 * @param register nombre del boton registrar
	 * @param login nombre del boton loguear
	 * @param mode indica el modo para setear los botones
	 */
	public LoginDialog(Frame owner , String cancel , String register , String login , int mode){
		this(owner , DEF_LOG_LAB , DEF_PASS_LAB , cancel , register , login , mode);
	}

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 * @param cancel nombre del boton cancelar
	 * @param register nombre del boton registrar
	 * @param login nombre del boton loguear
	 */
	public LoginDialog(Frame owner , String cancel , String register , String login ){
		this(owner , cancel , register , login , CANCEL_REGISTER_LOGIN_MODE);
	}

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 * @param cancel nombre del boton cancelar
	 * @param login nombre del boton loguear
	 */
	public LoginDialog(Frame owner , String cancel , String login ){
		this(owner , cancel , DEF_REGISTER , login);
	}

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 * @param login nombre del boton loguear
	 */
	public LoginDialog(Frame owner , String login ){
		this(owner , DEF_CANCEL , login);
	}

	/**
	 * Constructor de la clase
	 * @param owner componente padre para setear el dialogo modal, null en caso de no querer
	 * generar un dialogo modal
	 */
	public LoginDialog(Frame owner){
		this(owner , DEF_LOGIN);
	}

	/**
	 * Constructor de la clase
	 */
	public LoginDialog(){
		this(null);
	}

	/**
	 * muestra el dialogo en pantalla
	 */
	public void showDialog(){
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * ---- CONSTRUCT FUNCTIONS
	 */

	/**
	 * setea la botonera de la ventana
	 * @param cancel texto del boton cancelar
	 * @param register texto del boton registrar
	 * @param login texto del boton iniciar sesion
	 * @param mode indica el tipo de botonera a setear
	 * @return panel con la botonera seteada
	 */
	private JPanel setButtons(String cancel, String register, String login, int mode){
		JPanel ret = new JPanel();
		//setea una botonera completa
		if(mode == CANCEL_REGISTER_LOGIN_MODE){
			ret.add(setCancelButton(cancel));
			ret.add(setRegisterButton(register));
			ret.add(setLoginButton(login));
		}
		//setea una botonera sin el boton registrar
		if(mode == CANCEL_LOGIN_MODE){
			ret.add(setCancelButton(cancel));
			ret.add(setLoginButton(login));
		}
		//setea una botonera solo con el boton para loguear
		if(mode == LOGIN_MODE){
			ret.add(setLoginButton(login));
		}
		return ret;
	}

	/**
	 * setea el boton iniciar sesion
	 * @param txt texto del boton
	 * @return boton iniciar sesion
	 */
	private JButton setLoginButton(String txt){
		login = new JButton(txt);
		login.addActionListener(loginActionListener());
		return login;
	}

	/**
	 * setea el boton cancelar
	 * @param txt texto del boton
	 * @return boton cancelar
	 */
	private JButton setCancelButton(String txt){
		cancel = new JButton(txt);
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				runAbortEvent();
			}
		});
		return cancel;
	}

	/**
	 * setea el boton registrar
	 * @param txt texto del boton
	 * @return boton registrar
	 */
	private JButton setRegisterButton(String txt){
		register = new JButton(txt);
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				runRegisterEvent();
			}
		});
		return register;
	}

	/**
	 * setea el titulo de la ventana
	 * @param str titulo de la ventana
	 */
	private void setTitleLabel(String str){
		title = new JLabel(str);
		title.setFocusable(false);
		this.setTitle(str);	
	}

	/**
	 * setea el texto descriptor de la ventana
	 * @param str texto descriptor
	 */
	private void setDescriptionText(String str){
		description = new JTextArea();
		description.setText(str);
		description.setEditable(false);
		description.setFocusable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
	}

	/**
	 * setea el campo de usuario y su respectiva etiqueta
	 * @param str texto a setear en la etiqueta
	 */
	private void setUserField(String str){
		userLab = new JLabel(str);
		userLab.setBorder(new EmptyBorder(0, 10, 0, 1));
		userField = new JTextFieldHint();
		userField.setHint(str);
		userField.addActionListener(loginActionListener());
	}

	/**
	 * setea los elementos del campo de contraseñas y su respectiva etiqueta
	 * @param str texto a setear en la etiqueta
	 */
	private void setPassField(String str){
		passLab = new JLabel(str);
		passLab.setBorder(new EmptyBorder(0, 10, 0, 1));
		passField = new JPasswordField();
		passField.addActionListener(loginActionListener());
	}

	/**
	 * genera un listener para loguear
	 * @return listener para loguear
	 */
	private ActionListener loginActionListener(){
		ActionListener ret = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userField.getContent().equals("")){
					userField.requestFocus();
				}
				else if (passField.getPassword().length == 0){
					passField.requestFocus();
				}
				else{
					runLoginEvent();
				}
			}
		};
		return ret;
	}

	/**
	 * ---- LISTENER METHODS
	 */

	/**
	 * agrega un listener a la lista
	 * @param listener listener a agregar
	 */
	public void addLoginListener(LoginListener listener){
		System.out.println("agrega");
		listListener.add(listener);
	}

	/**
	 * remueve un listener de la lista
	 * @param listener listener a eliminar
	 */
	public void removeLoginListener(LoginListener listener){
		listListener.remove(listener);
	}

	/**
	 * llama al metodo loginEvent del LoginListener
	 */
	private void runLoginEvent(){
		LoginEvent e = new LoginEvent(this , userField.getContent() , passField.getPassword());
		Iterator<LoginListener> it = listListener.iterator();
		while(it.hasNext()){
			LoginListener lst = it.next();
			lst.loginEvent(e);
		}
	}

	/**
	 * llama al metodo abortEvent del LoginListener
	 */
	private void runAbortEvent(){
		LoginEvent e = new LoginEvent(this , userField.getContent() , passField.getPassword());
		Iterator<LoginListener> it = listListener.iterator();
		while(it.hasNext()){
			LoginListener lst = it.next();
			lst.abortEvent(e);
		}
	}

	/**
	 * llama al metodo registerEvent del LoginListener
	 */
	private void runRegisterEvent(){
		System.out.println("asado" + listListener);
		LoginEvent e = new LoginEvent(this , userField.getContent() , passField.getPassword());
		Iterator<LoginListener> it = listListener.iterator();
		while(it.hasNext()){
			LoginListener lst = it.next();
			lst.registerEvent(e);
		}
	}


}
