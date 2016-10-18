package dominio;
import java.util.concurrent.TimeUnit;
import gestion.*;
import java.awt.image.BufferedImage;
import Presentacion.*;
public class ResolverPuzle {
	public static void main( String [] args)  {
		/**
		 * 
		 * Version 4x4
		 * 
		 */
		int filas= 4, columnas=4;
		BufferedImage original[] = new BufferedImage[filas*columnas];
		BufferedImage mezclado[]=new BufferedImage[filas*columnas];
		try{
			Imagen.dividirImagen("alhambra.png", original, filas, columnas, 1);
			Imagen.dividirImagen("AlhambraInicialPuzzle4x4.png" , mezclado, filas, columnas, -1);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		/**
		 * 
		 * Version 5x10
		 * 
		 */
		/*
		int filas= 5, columnas=10;
		BufferedImage original[] = new BufferedImage[filas*columnas];
		BufferedImage mezclado[]=new BufferedImage[filas*columnas];
		try{
			Imagen.dividirImagen("Inicialalhambra10x5.png", original, filas, columnas);
			Imagen.dividirImagen("IntermedioAlhambra10x5.png" , mezclado, filas, columnas);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		*/
		
		Pieza puzleEntrada[][]= new Pieza [filas][columnas];
		for(int i=0; i<filas; i++){
			for(int j=0; j<columnas;j++){
				puzleEntrada[i][j]=null;
			}
		}
		boolean encontrado=false;
		int [] negro= new int [2]; //Coordenadas de la pieza negra
		for(int i=0; i<filas*columnas; i++){
			encontrado=false;
			for(int j=0; j<filas*columnas && !encontrado ; j++){
				if(puzleEntrada[j/columnas][j%columnas]==null){
					if(compararImagen(original[i], mezclado[j])){
						puzleEntrada[j/columnas][j%columnas]= new Pieza( i ,mezclado[j]);
						encontrado=true;
						if(i==0){
							negro[0]=j/columnas;
							negro[1]=j%columnas;
						}
					}
				}
			}
			if(!encontrado){
				System.out.println("El puzle no es resoluble, alguna pieza no coincide" );
				System.exit(0);
			}
		}
		int [] movimientos= {-1, -1, -1, -1};
		try{
			BufferedImage aux=Imagen.unirImagen(puzleEntrada);
			InterfazPuzle mostrar = new InterfazPuzle("Resolver Puzle", aux);
			mostrar.mostrarVentana();
			TimeUnit.SECONDS.sleep(2);
			
			for(int i=0; i<10; i++){
				movimientosPosibles(filas, columnas, negro, movimientos);
				for(int j=0; j<4; j++){
					if(movimientos[j]==1){
						mover(puzleEntrada, negro, j);
						break;
					}
				}
				aux=Imagen.unirImagen(puzleEntrada);
				mostrar.cambiarImagen(aux);
				TimeUnit.SECONDS.sleep(2);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
	public static void movimientosPosibles(int filas, int columnas, int negro[], int movimientos[]){
		int x=negro[0];
		int y=negro[1];
		if(x>0){
			movimientos[0]=1;
		}else{
			movimientos[0]=-1;
		}
		if(y>0){
			movimientos[1]=1;
		}else{
			movimientos[1]=-1;
		}
		if(x<filas-1){
			movimientos[2]=1;
		}else{
			movimientos[2]=-1;
		}
		if(y<columnas-1){
			movimientos[3]=1;
		}else{
			movimientos[3]=-1;
		}
		
	}
	public static void mover(Pieza puzle[][], int negro[], int movimiento){
		int x=negro [0];
		int y=negro [1];
		switch (movimiento){
			case 0: //arriba 
				if(x>0){
					Pieza aux;
					aux=puzle[x-1][y];
					puzle[x-1][y]=puzle[x][y];
					puzle[x][y]=aux;
					negro[0]=x-1;
				}	
				break;
			case 1: //izquierda
				if(y>0){
					Pieza aux;
					aux=puzle[x][y-1];
					puzle[x][y-1]=puzle[x][y];
					puzle[x][y]=aux;
					negro[1]=y-1;
				}
				break;
			case 2: //abajo
				if(x<puzle.length-1){
					Pieza aux;
					aux=puzle[x+1][y];
					puzle[x+1][y]=puzle[x][y];
					puzle[x][y]=aux;
					negro[0]=x+1;
				}
				break;
			case 3: //derecha
				if(y<puzle[0].length-1){
					Pieza aux;
					aux=puzle[x][y+1];
					puzle[x][y+1]=puzle[x][y];
					puzle[x][y]=aux;
					negro[1]=y+1;
				}	
				break;
		}
	}
	public static boolean compararImagen(BufferedImage imgA, BufferedImage imgB) {
		  if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
		    int ancho = imgA.getWidth();
		    int alto = imgA.getHeight();

		    for (int y = 0; y < alto; y++) {
		      for (int x = 0; x < ancho; x++) {

		        if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
		          return false;
		        }
		      }
		    }
		  } else {
		    return false;
		  }

		  return true;
		}
}
