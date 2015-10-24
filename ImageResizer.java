package ExtraClass;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Clase encargada de abrir imagenes y permite redimensionarlas para
 * uso del programa (redimension volatil)
 *
 * @author David Giordana
 *
 */
public class ImageResizer {
	
	/**
	 * Carga imagen segun la ruta del archivo
	 * @param path ruta de la imagen
	 * @return Objeto ImageIcon extraida del archivo, null en caso de ocurrir
	 * algun error
	 */
	public static ImageIcon loadImageIcon(String path){
		try{
			return new ImageIcon(path);
		}catch(Exception e){
			System.err.println("Error al abrir el archivo: " + path);
			return null;
		}
	}

	/**
	 * Carga imagen segun la ruta del archivo
	 * @param path ruta de la imagen
	 * @return Objeto Image extraida del archivo, null en caso de ocurrir
	 * algun error
	 */
	public static Image LoadImage(String path){
		ImageIcon image = loadImageIcon(path);
		return toImage(image);
	}

	/**
	 * Convierte un objeto ImageIcon en Image
	 * @param image objeto ImageIcon
	 * @return objeto Image
	 */
	public static Image toImage(ImageIcon image){
		return image == null ? null : image.getImage();
	}

	/**
	 * Convierte un objeto Image en ImageIcon
	 * @param image objeto Image
	 * @return objeto ImageIcon
	 */
	public static ImageIcon toImageIcon(Image image){
		return image == null ? null : new ImageIcon(image);
	}

	/**
	 * Redimensiona una imagen
	 * @param image imagen a redimensionar
	 * @param w ancho deseado
	 * @param h altura deseada
	 * @return imagen redimensionada
	 */
	public static ImageIcon resize(ImageIcon image, int w , int h){
		if(image == null){
			return image;
		}
		h = h < 0 ? 0 : h;
		w = w < 0 ? 0 : w;
		return new ImageIcon(image.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

	/**
	 * Redimensiona una imagen
	 * @param image imagen a redimensionar
	 * @param w ancho deseado
	 * @param h alto deseado
	 * @return imagen redimensionada
	 */
	public static Image resize(Image image, int w , int h){
		if(image == null){
			return image;
		}
		h = h < 0 ? 0 : h;
		w = w < 0 ? 0 : w;
		return toImage(new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
	}

	/**
	 * Setea la dimension del limitador
	 * @param d dimension del limitador
	 *
	public void setDimension(Dimension d){
		dim = d;
	}*/

	/**
	 * Obtiene la dimension del limitador
	 * @return dimension del limitador
	 *
	public Dimension getDimension(){
		return dim;
	}*/

	/**
	 * Redimensiona una imagen con el limitador seteado
	 * @param image imagen a redimensionar
	 * @param dim tamaño deseado para la redimension
	 * @return imagen redimensionada
	 */
	public static Image resizeLimited(Image image , Dimension dim){
		return toImage(resizeLimited(toImageIcon(image) , dim));
	}

	/**
	 * Redimensiona una imagen con el limitador seteado
	 * @param image imagen a redimensionar
     * @param dim tamaño deseado para la redimension
	 * @return imagen redimensionada
	 */
	public static ImageIcon resizeLimited(ImageIcon image , Dimension dim){
		int wt = (int) Math.abs(image.getIconWidth() - dim.getWidth());
		int ht = (int) Math.abs(image.getIconHeight() - dim.getHeight());
		if(ht == 0 || wt == 0){
			return image;
		}
		else if(ht <= wt){
			int ratio = dim.height == 0 ? 0 : dim.width / dim.height;
			int h = dim.height;
			int w = ratio * h;
			return resize(image , w , h);
		}
		else {
			int ratio = dim.width == 0 ? 0 : dim.height / dim.width;
			int w = dim.width;
			int h = ratio * w;
			return resize(image , w , h);
		}
	}

}
