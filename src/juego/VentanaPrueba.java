package juego;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaPrueba{

	private JFrame frame;
	private int filas = 20;
	private int columnas = 20;
	private int altoVentana = 800;
	private int anchoVentana = 800;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrueba window = new VentanaPrueba();
					window.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public void inicializar() {
		
		//Inicializacion de la ventana
		frame = new JFrame();
		frame.setBounds(100, 100, anchoVentana, altoVentana);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.yellow);
		frame.setVisible(true);
		
		//Creacion del tablero
		TableroGrafico tablero = new TableroGrafico();
		frame.getContentPane().add(tablero);
		
		
		tablero.cambiarCelda(0, 0, "images/cuerpo.png");
		tablero.cambiarCelda(0, 1, "images/cuerpo.png");
		tablero.cambiarCelda(0, 2, "images/cuerpo.png");
	}
	

	private class TableroGrafico extends JPanel{
		private int anchoTablero = anchoVentana-100;
		private int altoTablero = altoVentana-100;
		private int anchoCelda = anchoTablero/columnas;
		private int altoCelda = altoTablero/filas;
		
		public void paint(Graphics g) {
			int esquinaX = 50;
			int esquinaY = 50;
			
			g.setColor(Color.black); //PONER ACA UNO OTRO DE LOS COLORES DEL FONDO
			g.fillRect(esquinaX, esquinaY, anchoTablero , altoTablero);
			
			 for(int cols = 0; cols < columnas/2; cols++) {
				for(int i= esquinaX; i < anchoTablero; i+=2*anchoCelda) { 
					g.setColor(Color.white); //PONER ACA EL OTRO DE LOS COLORES DEL FONDO
					g.fillRect(i, esquinaY + (cols * 2 * altoCelda), anchoCelda, altoCelda); 
					g.fillRect(i + anchoCelda, esquinaY + altoCelda + (cols * 2 * altoCelda), anchoCelda, altoCelda);
				}
			} 
			
		}
		
		public void cambiarCelda(int posX, int posY, String pathImg){
			
			try {
				BufferedImage img = ImageIO.read(new File(pathImg));
				Graphics g = getGraphics();
				g.drawImage(img, posX * anchoCelda, posY * altoCelda, this);
			} catch(IOException e) {
				System.out.println("Beware, se rompio lo que sea que lee los paths y los pasa a imagenes para el tablero");
			}
		}
		
	}

}
