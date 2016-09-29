package dominio;
import java.math.*;
import java.io.IOException;
import gestion.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import Presentacion.*;

public class ResolverPuzle {

	public static void main(String[] args) throws IOException {
		int filas=4, columnas=5;
		BufferedImage imgs[] = new BufferedImage[filas*columnas];
		ImagenDividir.dividirImagen("alhambra.png", imgs, filas, columnas);

		Pieza puzleOriginal[][]= new Pieza[filas][columnas];
		
		int posicion=0;
		for(int i=0; i<filas; i++){
			for(int j=0; j<columnas; j++){
				puzleOriginal[i][j]=new Pieza(posicion, imgs[posicion]);
				posicion++;
			}
		}
		puzleOriginal[0][0].esNegro();
		Pieza randomPuzle[][]= new Pieza[filas][columnas];
		desordenar(puzleOriginal, randomPuzle);
		
		BufferedImage aux=ImagenUnion.unirImagen(puzleOriginal);
		BufferedImage aux2=ImagenUnion.unirImagen(randomPuzle);
		InterfazPuzle mostrar = new InterfazPuzle("Prueba", aux);
		mostrar.mostrarVentana();

	}
	public static void desordenar(Pieza original [][], Pieza desordenado [][]){
		int x, y;
		for(int i=0; i<original.length; i++){
			for(int j=0; j<original[0].length; j++){
				x=(int) (Math.random()*original.length);
				y=(int) (Math.random()*original[0].length);
				while(desordenado[x][y]!=null){
					
					y++;
					if(y>=original[0].length){
						y=0;
						x++;
						if(x>=original.length){
							x=0;
						}
					}
				}
				desordenado[x][y]=original[i][j];

				
			}
		}
	}
}
