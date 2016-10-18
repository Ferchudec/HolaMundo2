package dominio;
import java.awt.image.BufferedImage;
public class Pieza {
	int valor;
	BufferedImage imagen;
	
	public Pieza(int v, BufferedImage i){
		valor=v;
		imagen=i;
	}
	public Pieza(BufferedImage i){
		imagen=i;
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}

	
}
