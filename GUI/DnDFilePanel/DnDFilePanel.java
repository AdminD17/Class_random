package ExtraClass.GUI.DnDFilePanel;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Panel con la capacidad de detectar cuando un usuario arrastra hacia
 * el uno o varios archivos
 * 
 * @author David Giordana
 *
 */
public class DnDFilePanel extends JPanel{

	private static final long serialVersionUID = -5338467532719594128L;

	/**
	 * ---- ATTRIBUTES
	 */

	//lista de listeners
	private ArrayList<DnDFileListener> dndfileListeners;

	/**
	 * ---- CONSTRUCTOR
	 */

	/**
	 * Constructor de la clase
	 */
	public DnDFilePanel(){
		dndfileListeners = new ArrayList<DnDFileListener>();
		this.setDropTarget(new DropTarget() {
			private static final long serialVersionUID = 1667552306938510802L;
			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>)
							evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					DnDFileEvent ev = new DnDFileEvent(this , droppedFiles);
					for(DnDFileListener dnd : dndfileListeners){
						dnd.filesDropped(ev);
					}
				} catch (Exception ex) {}
			}
		});
	}

	/**
	 * ---- LISTENER METHODS
	 */

	/**
	 * agrega un @see DnDFileListener a la lista
	 * @param l objeto DnDFileListener a agregar
	 */
	public void addDnDFileListener(DnDFileListener l){
		dndfileListeners.add(l);
	}

	/**
	 * elimina un @see DnDFileListener
	 * @param l objeto DnDFileListener
	 */
	public void removeDnDFileListener(DnDFileListener l){
		dndfileListeners.remove(l);
	}

}
