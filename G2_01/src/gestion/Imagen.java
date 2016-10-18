package gestion;
import javax.imageio.ImageIO;

import dominio.Pieza;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class Imagen {
    public static void dividirImagen(String nombreImagen ,BufferedImage [] imgs, int filas, int columnas, int original) throws IOException {

        File file = new File(nombreImagen); 
        FileInputStream fis = new FileInputStream(file);
        BufferedImage imagen = ImageIO.read(fis);
        if(imagen.getWidth()%columnas != 0 || imagen.getHeight()%filas != 0 ){
        	System.out.print("La imagen es indivisible entre " + filas + " filas "+ "y " + columnas + " columnas.");
        	System.exit(0);
        }else{
        	int trozoAncho = imagen.getWidth() / columnas; 
            int trozoAlto = imagen.getHeight() / filas;
            int contador = 0;

            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas; y++) {
                    imgs[contador] = new BufferedImage(trozoAncho, trozoAlto, imagen.getType());

                    Graphics2D gr = imgs[contador++].createGraphics();
                    gr.drawImage(imagen, 0, 0, trozoAncho, trozoAlto, trozoAncho * y, trozoAlto * x, trozoAncho * y + trozoAncho, trozoAlto * x + trozoAlto, null);
                    gr.dispose();
                }
            }
            if(original==1)
            	imgs[0].setRGB(0, 0, trozoAncho, trozoAlto, new int [trozoAncho*trozoAlto ], 0, trozoAncho);//colorear de negro la primera
        }
        
        
    }
    public static BufferedImage unirImagen(Pieza [][] puzle) throws IOException {


        int anchoTrozo = puzle[0][0].getImagen().getWidth();
        int altoTrozo = puzle[0][0].getImagen().getHeight();

        BufferedImage result = new BufferedImage(anchoTrozo*puzle[0].length, altoTrozo*puzle.length,BufferedImage.TYPE_INT_RGB);
        Graphics g = result.getGraphics();
        int x=0, y=0, salto=0;
        for(int i=0; i<puzle.length; i++ ){
        	for(int j=0; j<puzle[0].length;j++){
            	g.drawImage(puzle[i][j].getImagen(), x, y, null);
            	x += anchoTrozo;
            	salto++;
            	if(salto==(puzle[0].length)){
            		x = 0;
            		y += altoTrozo;
            		salto=0;
            	}
            	
        	}

        }

        return result;

    }
}