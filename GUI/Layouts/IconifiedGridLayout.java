package ExtraClass.GUI.Layouts;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Layout Manager inspirado en @see GridLayout con algunas extensiones.
 * Para poder trabajar su comporatamiento es similar al @see GridLayout
 * salvo que se establece un tamaño específico a cada elemento, ideal para
 * administrar contenedores de iconos , paginas o items del mismo tamaño.
 * 
 * 
 * @author David Giordana
 *
 */
public class IconifiedGridLayout implements LayoutManager2 , Serializable{


	private static final long serialVersionUID = -6612278666962070218L;

	/**
	 * ---- ATTRIBUTES
	 */

	//cantidad de columnas a setear
	private int cols;

	//brechas entre objetos
	private int vgap;
	private int hgap;

	//tamaño de los items a agregar
	private Dimension itemDim;

	//lista de objetos en orden especifico
	private List<Component> components;

	/**
	 * ---- CONSTRUCTORS
	 */

	/**
	 * Constructor de la clase
	 * @param cols - número de columnas del layout
	 * @param itemDim - tamaño de los items
	 * @param vgap - brecha vertical entre los items
	 * @param hgap - brecha horizontal entre los items
	 */
	public IconifiedGridLayout(int cols , Dimension itemDim , int vgap , int hgap){
		this.cols = cols;
		this.itemDim = itemDim;
		this.vgap = vgap;
		this.hgap = hgap;
		this.components = new ArrayList<Component>();
	}

	/**
	 * Constructor de la clase
	 * @param cols - número de columnas del layout
	 * @param itemWidth - ancho de los items
	 * @param itemHeight - alto de los items
	 * @param vgap - brecha vertical entre los items
	 * @param hgap - brecha horizontal entre los items
	 */
	public IconifiedGridLayout(int cols , int itemWidth , int itemHeight , int vgap , int hgap){
		this(cols , new Dimension(itemWidth , itemHeight) , vgap , hgap);
	}

	/**
	 * Constructor de la clase
	 * @param cols - número de columnas del layout
	 * @param itemWidth - ancho de los items
	 * @param itemHeight - alto de los items
	 */
	public IconifiedGridLayout(int cols , int itemWidth , int itemHeight){
		this(cols , itemWidth , itemHeight , 0 , 0);
	}

	/**
	 * Constructor de la clase
	 * @param cols - número de columnas del layout
	 */
	public IconifiedGridLayout(int cols){
		this(cols , 100 , 100);
	}

	/**
	 * Constructor de la clase
	 */
	public IconifiedGridLayout(){
		this(1);
	}

	/**
	 * ---- GETTERS & SETTERS
	 */

	/**
	 * retorna la cantidad de columnas
	 * @return cantidad de columnas
	 */
	public int getCols(){
		return cols;
	}

	/**
	 * retorna la brecha vertical
	 * @return brecha vertical
	 */
	public int getVgap(){
		return vgap;
	}

	/**
	 * retorna la brecha horizontal
	 * @return brecha horizontal
	 */
	public int getHgap(){
		return hgap;
	}

	/**
	 * retorna el tamaño de los items
	 * @return el tamaño de los items
	 */
	public Dimension getItemDimension(){
		return itemDim;
	}
	
	/**
	 * retorna la cantidad de filas
	 * @return cantidad de filas
	 */
	public int getRows(){
		return (int)Math.ceil((float)components.size() / cols);
	}

	/**
	 * setea la cantidad de columnas del layout
	 * @param cols cantidad de columnas del layout
	 */
	public void setCols(int cols){
		this.cols = cols;
	}

	/**
	 * setea la brecha horizontal
	 * @param h brecha horizontal
	 */
	public void setHgap(int h){
		this.hgap = h;
	}

	/**
	 * setea la brecha vertical
	 * @param v brecha vertical
	 */
	public void setVgap(int v){
		this.vgap = v;
	}

	/**
	 * setea el tamaño de los items
	 * @param dim tamaño de los items
	 */
	public void setItemDimension(Dimension dim){
		this.itemDim = dim;
	}

	/**
	 * ---- LAYOUT METHODS
	 */

	@Override
	public void addLayoutComponent(String name, Component comp) {}

	@Override
	public void removeLayoutComponent(Component comp) {
		components.remove(comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return minimumLayoutSize(parent);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension();
			int rows = getRows();
			Insets inset = parent.getInsets();
			dim.width = vgap + cols * (vgap + itemDim.width);
			dim.height = hgap + rows * (hgap + itemDim.height);
			dim.width += inset.left + inset.right;
			dim.height += inset.bottom + inset.top;
			return dim;
		}
	}

	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets inset = parent.getInsets();
			int x = inset.left + vgap;
			int y = inset.top + hgap;
			for(int i = 0 ; i < components.size() ; i++){
				if(i % cols == 0 && i != 0){
					x = inset.left + vgap;
					y += hgap + itemDim.height;
				}
				else if (i != 0){
					x += vgap + itemDim.width;
				}
				components.get(i).setBounds(x, y, itemDim.width, itemDim.height);
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

	/**
	 * ---- EXTRA METHODS
	 */


}
