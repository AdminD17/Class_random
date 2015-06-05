package ExtraClass.GUI.Layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.Serializable;


/**
 * 
 * 
 * @author David Giordana
 *
 */
public class ListLayout2 implements LayoutManager2, Serializable {

	private static final long serialVersionUID = -3347619822413708016L;

	/**
	 *Indica orientacion descendente.
	 */
	public static final int TOP_BOTTOM = 0;

	/**
	 * Indica orientacion ascendente.
	 */
	public static final int BOTTOM_TOP = 1;

	//Orientacion de los elementos
	private int orientation;

	/**
	 * Constructor de la clase
	 * @param orientation Indice de la orientacion
	 */
	public ListLayout2(int orientation) {
		setOrientation(orientation);
	}

	/**
	 * Constructor de la clase
	 */
	public ListLayout2(){
		this(TOP_BOTTOM);
	}
	
	/**
	 * Setea la orientacion del layout
	 * @param orientation Orientacion deseada
	 */
	public void setOrientation(int orientation){
		this.orientation = orientation;
	}

	/**
	 * Retorna la orientacion de los objetos
	 * @return Orientacion de los objetos
	 */
	public int getOrientation() {
		return this.orientation;
	}

	/**
	 * No usado por la clase
	 */
	@Override
	public void addLayoutComponent(String name, Component comp) {}

	/**
	 * No usado por la clase
	 */
	@Override
	public void removeLayoutComponent(Component comp) {}

	/**
	 * No usado por la clase
	 */	
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {}

	/**
	 * Retorna el tamaño preferido de los componentes de un contenedor
	 * @param target Contenedor a analizar
	 * @return Dimesnion preferida
	 */
	@Override
	public Dimension preferredLayoutSize(Container target) {		
		int x = 0;
		int y = 0;
		synchronized (target.getTreeLock()) {
			Insets insets = target.getInsets();
			for(Component c : target.getComponents()){
				x = Math.max(x, c.getPreferredSize().width);
				y += c.getPreferredSize().getHeight();
			}
			x += insets.left + insets.right;
			y += insets.top + insets.bottom;
		}
		return new Dimension(x , y);
	}

	/**
	 * Retorna el minimo tamaño necesario para mostrar los componentes
	 * @param target Contenedor a analizar
	 * @return Dimesnion minima
	 */
	@Override
	public Dimension minimumLayoutSize(Container target) {
		int x = 0;
		int y = 0;
		synchronized (target.getTreeLock()) {
			Insets insets = target.getInsets();
			for(Component c : target.getComponents()){
				x = Math.max(x, c.getMinimumSize().width);
				y += c.getMinimumSize().getHeight();
			}
			x += insets.left + insets.right;
			y += insets.top + insets.bottom;
		}
		return new Dimension(x , y);
	}

	/**
	 * Retorna el maximo tamaño necesario usable por los componentes
	 * @param target Contenedor a analizar
	 * @return Dimesnion maxima
	 */
	@Override
	public Dimension maximumLayoutSize(Container target) {
		int x = 0;
		int y = 0;
		synchronized (target.getTreeLock()) {
			Insets insets = target.getInsets();
			for(Component c : target.getComponents()){
				x = Math.max(x, c.getMaximumSize().width);
				y += c.getMaximumSize().getHeight();
			}
			x += insets.left + insets.right;
			y += insets.top + insets.bottom;
		}
		return new Dimension(x , y);	}

	/**
	 * Retorna la alineacion con respecto al eje X
	 * @param target Contenedor a analizar
	 * @return Orientacion con respecto al eje X
	 */
	@Override
	public synchronized float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	/**
	 * Retorna la alineacion con respecto al eje Y
	 * @param target Contenedor a analizar
	 * @return Orientacion con respecto al eje Y
	 */
	@Override
	public synchronized float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	/**
	 * Aplica el layout a un contenedor
	 * to be laid out.
	 * @param target Contenedor a aplicar el layout
	 */
	@Override
	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			int n = target.getComponentCount();
			Dimension size = target.getSize();
			Insets in = target.getInsets();
			int width = size.width - in.left - in.right;
			int y = in.top;
			if(orientation == TOP_BOTTOM){
				for(int i = 0 ; i < n ; i++){
					Component comp = target.getComponent(i);
					Dimension d = comp.getPreferredSize();
					comp.setBounds(in.left , y, width , (int) d.getHeight());
					y += d.height;
				}
			}
			else{
				for(int i = n - 1 ; i >= 0 ; i--){
					Component comp = target.getComponent(i);
					Dimension d = comp.getPreferredSize();
					comp.setBounds(in.left , y, width , (int) d.getHeight());
					y += d.height;
				}
			}
		}
	}

	/**
	 * No usado por la clase
	 */	
	@Override
	public void invalidateLayout(Container target) {}

}
