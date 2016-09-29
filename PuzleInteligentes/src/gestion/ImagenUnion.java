package gestion;

import dominio.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;


public class ImagenUnion {
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