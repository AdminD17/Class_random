package ExtraClass.GUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * Contiene los metodos necesarios para mostrar un dialogo que solicita
 * al usuario una contraseña
 * 
 * @author David Giordana
 *
 */
public class PasswordDialog extends JDialog{

	private static final long serialVersionUID = 74496172471454840L;

	//variable de control
	protected static boolean isCancelled;

	/**
	 * Solicita al usuario una contraseña
	 * @param parent frame padre del dialogo, null en caso de no utilizarlo
	 * @param icon icono de la ventana
	 * @param title titulo del dialogo
	 * @param description descripcion del dialogo
	 * @param cancelLabel etiqueta del boton cancelar
	 * @param okLabel etiqueta del boton aceptar
	 * @return contraseña ingresada por el usuario , null en caso de cancelar
	 */
	public static String inputContraseña(Frame parent,Icon icon , String title ,String description , String cancelLabel , String okLabel ){
		//variables internas
		String password = "";
		isCancelled = true;
		//campo de entrada de contraseñas
		JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(16);
		passwordField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();  
				isCancelled = false;
			}
		});
		//crea objetos del dialogo
		Object[] obj = {description, passwordField};
		Object buttons[] = {okLabel , cancelLabel};
		//lanza el dialogo
		int selected = JOptionPane.showOptionDialog(
				parent, 
				obj, 
				title,	
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE,
				icon, 
				buttons, 
				obj);
		//retorna
		if (selected == JOptionPane.YES_OPTION || !isCancelled){
			for(char chr : passwordField.getPassword()){
				password += chr;
			}
			isCancelled = true;
			return password;
		}
		else{
			isCancelled = true;
			return null;
		}
	}

	/**
	 * Solicita al usuario una contraseña
	 * @param parent frame padre del dialogo, null en caso de no utilizarlo
	 * @param icon icono de la ventana
	 * @param title titulo del dialogo
	 * @param description descripcion del dialogo
	 * @return contraseña ingresada por el usuario , null en caso de cancelar
	 */
	public static String inputContraseña(Frame parent, Icon icon , String title ,String description){
		return inputContraseña(parent , icon, title , description , "Cancel" , "Ok");
	}

	/**
	 * Solicita al usuario una contraseña
	 * @param parent frame padre del dialogo, null en caso de no utilizarlo
	 * @param title titulo del dialogo
	 * @return contraseña ingresada por el usuario , null en caso de cancelar
	 */
	public static String inputContraseña(Frame parent , String title){
		return inputContraseña(parent , null, title , "");
	}
	
	/**
	 * Solicita al usuario una contraseña
	 * @param parent frame padre del dialogo, null en caso de no utilizarlo
	 * @return contraseña ingresada por el usuario , null en caso de cancelar
	 */
	public static String inputContraseña(Frame parent){
		return inputContraseña(parent , "Password");
	}
	
	/**
	 * Solicita al usuario una contraseña
	 * @return contraseña ingresada por el usuario , null en caso de cancelar
	 */
	public static String inputContraseña(){
		return inputContraseña(null);
	}

}
