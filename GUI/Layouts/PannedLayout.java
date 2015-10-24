package ExtraClass.GUI.Layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.Serializable;

/**
 * Controlador de distribucion de componentes para contenedores 
 * basado en paneles.
 * La implementacion se basa en que los componentes se distribuyen
 * en paneles de manera similar al @see BorderLayout con algunos paneles mas
 * 
 * 
 * @author David Giordana
 *
 */
public class PannedLayout implements LayoutManager2 , Serializable{

	/**
	 * ---- CONSTANTS
	 */

	private static final long serialVersionUID = -5643723790753156435L;

	//constantes que almacenan los nombres de los campos del layout
	public static final String TOP = "Top";
	public static final String BOTTOM = "Bottom";
	public static final String CENTER = "Center";
	public static final String LEFT_BAR = "LeftBar";
	public static final String RIGHT_BAR = "RightBar";
	public static final String CENTER_TOP = "CenterTop";
	public static final String CENTER_BOTTOM = "CenterBottm";

	/**
	 * ---- ATTRIBUTES
	 */

	//paneles del layout
	private Component top;
	private Component bottom;
	private Component center;
	private Component leftBar;
	private Component rightBar;
	private Component centerTop;
	private Component centerBottom;

	//tamaños de limitadores de campo
	private int topHeight;
	private int bottomHeight;
	private int leftBarWidth;
	private int rightBarWidth;
	private int centerTopHeight;
	private int centerBottomHeight;

	/**
	 * ---- CONSTRUCTOR
	 */

	/**
	 * Constructor del layout
	 */
	public PannedLayout(){
		clearConstraints();
	}

	/**
	 * --- EXTRA COMPONENTS
	 */

	/**
	 * limpia los limitadores de tamaño
	 */
	public void clearConstraints(){
		topHeight = -1;
		bottomHeight = -1;
		leftBarWidth = -1;
		rightBarWidth = -1;
		centerTopHeight = -1;
		centerBottomHeight = -1;
	}

	/**
	 * Setea la dimension obligada de los paneles
	 * @param key - nombre del panel
	 * @param value - valor a setear
	 */
	public void setConstraint(String key , int value){
		if(key.equals(TOP)){
			this.topHeight = value;
		}
		else if (key.equals(BOTTOM)){
			this.bottomHeight = value;
		}
		else if (key.equals(LEFT_BAR)){
			this.leftBarWidth = value;
		}
		else if (key.equals(RIGHT_BAR)){
			this.rightBarWidth = value;
		}
		else if (key.equals(CENTER_TOP)){
			this.centerTopHeight = value;
		}
		else if (key.equals(CENTER_BOTTOM)){
			this.centerBottomHeight = value;
		}
	}

	/**
	 * calcula el tamaño preferido de un componente
	 * @param comp - componente base
	 * @return tañaño preferido del componente
	 */
	private Dimension preferredComponentSize(Component comp){
		synchronized (comp.getTreeLock()) {
			Dimension ret = comp.getPreferredSize();
			if(comp == top){
				if(topHeight > -1){
					ret.height = topHeight;
				}
				return ret;
			}
			else if(comp == bottom){
				if(bottomHeight > -1){
					ret.height = bottomHeight;
				}
				return ret;
			}
			else if(comp == leftBar){
				if(leftBarWidth > -1){
					ret.width = leftBarWidth;
				}
				return ret;
			}
			else if(comp == rightBar){
				if(rightBarWidth > -1){
					ret.width = rightBarWidth;
				}
				return ret;
			}
			else if(comp == centerTop){
				if(centerTopHeight > -1){
					ret.height = centerTopHeight;
				}
				return ret;
			}
			else if(comp == centerBottom){
				if(centerBottomHeight > -1){
					ret.height = centerBottomHeight;
				}
				return ret;
			}
			else if(comp == center){
				return ret;
			}
			return ret;
		}
	}

	/**
	 * calcula el tamaño minimo de un componente
	 * @param comp - componente base
	 * @return tamaño minimo del componente
	 */
	private Dimension minimumComponentSize(Component comp){
		synchronized (comp.getTreeLock()) {
			Dimension ret = comp.getMinimumSize();
			if(comp == top){
				if(topHeight > -1){
					ret.height = topHeight;
				}
				return ret;
			}
			else if(comp == bottom){
				if(bottomHeight > -1){
					ret.height = bottomHeight;
				}
				return ret;
			}
			else if(comp == leftBar){
				if(leftBarWidth > -1){
					ret.width = leftBarWidth;
				}
				return ret;
			}
			else if(comp == rightBar){
				if(rightBarWidth > -1){
					ret.width = rightBarWidth;
				}
				return ret;
			}
			else if(comp == centerTop){
				if(centerTopHeight > -1){
					ret.height = centerTopHeight;
				}
				return ret;
			}
			else if(comp == centerBottom){
				if(centerBottomHeight > -1){
					ret.height = centerBottomHeight;
				}
				return ret;
			}
			else if(comp == center){
				return ret;
			}
			return ret;
		}
	}


	/**
	 * ---- LAYOUT METHODS
	 */

	@Override
	public void addLayoutComponent(String name, Component comp) {}

	@Override
	public void removeLayoutComponent(Component comp) {
		synchronized (comp.getTreeLock()) {
			if(comp == top){
				top = null;
				topHeight = -1;
			}
			else if(comp == bottom){
				bottom = null;
				bottomHeight = -1;
			}
			else if(comp == leftBar){
				leftBar = null;
				leftBarWidth = -1;
			}
			else if(comp == rightBar){
				rightBar = null;
				rightBarWidth = -1;
			}
			else if(comp == centerTop){
				centerTop = null;
				centerTopHeight = -1;
			}
			else if(comp == centerBottom){
				centerBottom = null;
				centerBottomHeight = -1;
			}
			else if(comp == center){
				center = null;
			}
		}
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension();
			Component c = null;
			Dimension centerDim = new Dimension();
			//precalcula la region del centro
			if((c = centerTop) != null){
				Dimension d = preferredComponentSize(c);
				centerDim.width = Math.max(d.width, dim.width);
				centerDim.height = d.height;
			}
			if((c = centerBottom) != null){
				Dimension d = preferredComponentSize(c);
				centerDim.width = Math.max(d.width, dim.width);
				centerDim.height = d.height;
			}
			if((c = center) != null){
				Dimension d = preferredComponentSize(c);
				centerDim.width = Math.max(d.width, dim.width);
				centerDim.height = d.height;
			}
			//calcula la dimension de los componentes
			if ((c = leftBar) != null) {
				Dimension d = preferredComponentSize(c);
				dim.width += d.width;
				dim.height = Math.max(d.height, dim.height);
			}
			if ((c = rightBar) != null) {
				Dimension d = preferredComponentSize(c);
				dim.width += d.width;
				dim.height = Math.max(d.height, dim.height);
			}
			if ((c = center) != null) {
				dim.width += centerDim.width;
				dim.height = Math.max(centerDim.height, dim.height);
			}
			if ((c = top) != null) {
				Dimension d = preferredComponentSize(c);
				dim.width = Math.max(d.width, dim.width);
				dim.height += d.height;
			}
			if ((c = bottom) != null) {
				Dimension d = preferredComponentSize(c);
				dim.width = Math.max(d.width, dim.width);
				dim.height += d.height;
			}
			//incorpora el offset del contenedor
			Insets insets = parent.getInsets();
			dim.width += insets.left + insets.right;
			dim.height += insets.top + insets.bottom;
			return dim;
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension();
			Component c = null;
			Dimension centerDim = new Dimension();
			//precalcula la region del centro
			if((c = centerTop) != null){
				Dimension d = minimumComponentSize(c);
				centerDim.width = Math.max(d.width, dim.width);
				centerDim.height = d.height;
			}
			if((c = centerBottom) != null){
				Dimension d = minimumComponentSize(c);
				centerDim.width = Math.max(d.width, dim.width);
				centerDim.height = d.height;
			}
			if((c = center) != null){
				Dimension d = minimumComponentSize(c);
				centerDim.width = Math.max(d.width, dim.width);
				centerDim.height = d.height;
			}
			//calcula la dimension de los componentes
			if ((c = leftBar) != null) {
				Dimension d = minimumComponentSize(c);
				dim.width += d.width;
				dim.height = Math.max(d.height, dim.height);
			}
			if ((c = rightBar) != null) {
				Dimension d = minimumComponentSize(c);
				dim.width += d.width;
				dim.height = Math.max(d.height, dim.height);
			}
			if ((c = center) != null) {
				dim.width += centerDim.width;
				dim.height = Math.max(centerDim.height, dim.height);
			}
			if ((c = top) != null) {
				Dimension d = minimumComponentSize(c);
				dim.width = Math.max(d.width, dim.width);
				dim.height += d.height;
			}
			if ((c = bottom) != null) {
				Dimension d = minimumComponentSize(c);
				dim.width = Math.max(d.width, dim.width);
				dim.height += d.height;
			}
			//incorpora el offset del contenedor
			Insets insets = parent.getInsets();
			dim.width += insets.left + insets.right;
			dim.height += insets.top + insets.bottom;
			return dim;
		}
	}

	@Override
	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			Insets insets = target.getInsets();
			int top = insets.top;
			int bottom = target.getHeight() - insets.bottom;
			int left = insets.left;
			int right = target.getWidth() - insets.right;
			Component c = null;
			if ((c = this.top) != null) {
				if(topHeight != -1){
					c.setSize(right - left, topHeight);
				}
				else{
					c.setSize(right - left, c.getHeight());
				}
				Dimension d = preferredComponentSize(c);
				c.setBounds(left, top, right - left, d.height);
				top += d.height;
			}
			if ((c = this.bottom) != null) {
				if(bottomHeight != -1){
					c.setSize(right - left, bottomHeight);
				}
				else{
					c.setSize(right - left, c.getHeight());
				}
				Dimension d = preferredComponentSize(c);
				c.setBounds(left, bottom - d.height, right - left, d.height);
				bottom -= d.height;
			}
			if ((c = this.rightBar) != null) {
				if(rightBarWidth != -1){
					c.setSize(rightBarWidth, bottom - top);
				}
				else{
					c.setSize(c.getWidth(), bottom - top);
				}
				Dimension d = preferredComponentSize(c);
				c.setBounds(right - d.width, top, d.width, bottom - top);
				right -= d.width;
			}
			if ((c = this.leftBar) != null) {
				if(leftBarWidth != -1){
					c.setSize(leftBarWidth, bottom - top);
				}
				else{
					c.setSize(c.getWidth(), bottom - top);
				}
				Dimension d = preferredComponentSize(c);
				c.setBounds(left, top, d.width, bottom - top);
				left += d.width;
			}

			if((c = this.centerTop) != null){
				if(topHeight != -1){
					c.setSize(right - left, topHeight);
				}
				else{
					c.setSize(right - left, c.getHeight());
				}
				Dimension d = preferredComponentSize(c);
				c.setBounds(left, top,  right - left, d.height);
				top += d.height;
			}
			if((c = this.centerBottom) != null){
				if(bottomHeight != -1){
					c.setSize(right - left, bottomHeight);
				}
				else{
					c.setSize(right - left, c.getHeight());
				}
				Dimension d = preferredComponentSize(c);
				c.setBounds(left, bottom - d.height, right - left, d.height);
				bottom -= d.height;
			}
			if ((c = this.center) != null) {
				c.setBounds(left, top, right - left, bottom - top);
			}
		}
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if(!(constraints instanceof String)){
			throw new IllegalArgumentException("Argumento Invalido: " + constraints);
		}
		if(((String)constraints).equals(TOP)){
			top = comp;
		}
		else if(((String)constraints).equals(BOTTOM)){
			bottom = comp;
		}
		else if(((String)constraints).equals(CENTER)){
			center = comp;
		}
		else if(((String)constraints).equals(LEFT_BAR)){
			leftBar = comp;
		}
		else if(((String)constraints).equals(RIGHT_BAR)){
			rightBar = comp;
		}
		else if(((String)constraints).equals(CENTER_TOP)){
			centerTop = comp;
		}
		else if(((String)constraints).equals(CENTER_BOTTOM)){
			centerBottom = comp;
		}
		else{
			throw new IllegalArgumentException("Constratint inválido :" + constraints);
		}
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
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
