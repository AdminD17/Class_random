package ExtraClass.GUI.Layouts;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Layout manager inspirado en las @see JList enfocado 
 * a mostrar paneles o componentes similares en un formato de lista
 * 
 * @author David Giordana
 *
 */
public class ListLayout implements LayoutManager2 , Serializable {

	private static final long serialVersionUID = -6947703403806800489L;

	/**
	 * ---- ATTRIBUTES
	 */

	//lista de componentes
	private ArrayList<Component> components;

	/**
	 * ---- CONSTRUCTORS
	 */

	/**
	 * Constructor de la clase
	 */
	public ListLayout(){
		components = new ArrayList<Component>();
	}

	/**
	 * LAYOUT METHODS
	 */

	@Override
	public void addLayoutComponent(String name, Component comp) {}

	@Override
	public void removeLayoutComponent(Component comp) {
		components.remove(comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int x = 0;
			int y = 0;
			for(Component comp : components){
				Dimension d = comp.getPreferredSize();
				x = Math.max(x, d.width);
				y += d.height;
			}
			return new Dimension(x , y);
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int x = 0;
			int y = 0;
			for(Component comp : components){
				Dimension d = comp.getMinimumSize();
				x = Math.max(x, d.width);
				y += d.height;
			}
			return new Dimension(x , y);
		}
	}

	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int top = insets.top;
			int left = insets.left;
			int right = parent.getWidth() - insets.right;
			for(Component comp : components){
				Dimension d = comp.getPreferredSize();
				comp.setBounds(left, top, right - left, d.height);
				top += d.height;
			}
		}
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		components.add(comp);
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE , Integer.MAX_VALUE);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {}

}
