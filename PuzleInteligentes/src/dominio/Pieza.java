package dominio;
import java.awt.image.BufferedImage;
public class Pieza {
	int valor;
	BufferedImage imagen;
	boolean negro=false;
	
	public Pieza(int v, BufferedImage i){
		valor=v;
		imagen=i;
	}
	
	public void esNegro(){
		negro=true;
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
	public boolean equals( Pieza p){
		return this.imagen.equals(p.getImagen());
		
	}
	
}
