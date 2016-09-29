package gestion;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class ImagenDividir {
    public static void dividirImagen(String nombreImagen ,BufferedImage [] imgs, int filas, int columnas) throws IOException {

        File file = new File(nombreImagen); 
        FileInputStream fis = new FileInputStream(file);
        BufferedImage imagen = ImageIO.read(fis);


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
        imgs[0].setRGB(0, 0, trozoAncho, trozoAlto, new int [trozoAncho*trozoAlto ], 0, trozoAncho);//colorear de negro la primera
        System.out.println("Imagen dividida");
    }
}