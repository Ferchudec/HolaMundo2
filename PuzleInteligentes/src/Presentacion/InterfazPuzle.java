package Presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class InterfazPuzle
{
 private JFrame ventana;
 private JPanel contenedor;
 private JLabel etiqueta;
 
  public InterfazPuzle(String titulo, BufferedImage imagen)
  {
   ventana = new JFrame(titulo);
   contenedor = new JPanel();
   etiqueta = new JLabel(new ImageIcon(imagen));
  }

  public void mostrarVentana()
  {
   contenedor.add(etiqueta);
   ventana.getContentPane().add(contenedor);
   ventana.pack();
   ventana.setResizable(true);
   ventana.setVisible(true);
   ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


}

