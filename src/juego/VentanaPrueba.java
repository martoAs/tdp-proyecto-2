package juego;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.awt.BorderLayout.*;
public class VentanaPrueba{

	private JFrame frame;
	private int filas = 20;
	private int columnas = 20;
	private int altoVentana = 800;
	private int anchoVentana = 800;

	private int tamCelda = 35;
	
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
		frame.setBounds(100, 100, 22*tamCelda+16, 24*tamCelda);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.yellow);
		frame.setVisible(true);

		ImageIcon ic = new ImageIcon(VentanaPrueba.class.getResource("/images/tablero.jpg"));
		Image image = ic.getImage(); // transform it
		Image newimg = image.getScaledInstance(22*tamCelda, 24*tamCelda,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		ic = new ImageIcon(newimg);  // transform it back
		JLabel contentPane = new JLabel();
		contentPane.setIcon( ic);
		BorderLayout bl_contentPane = new BorderLayout();
		bl_contentPane.setVgap(1000);
		bl_contentPane.setHgap(10);
		contentPane.setLayout( bl_contentPane );
		frame.setContentPane( contentPane );
		frame.getContentPane().setSize(22*tamCelda,24*tamCelda);
		//frame.setSize(22*tamCelda+100,24*tamCelda);
		JPanel tablero = new JPanel();
		//frame.getContentPane().setLayout(new BorderLayout());
		//frame.getContentPane().add(tablero);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda,tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda};
		gridBagLayout.rowHeights = new int[] {tamCelda*2, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda,tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};

		frame.getContentPane().setLayout(gridBagLayout);
		Logica logica = new Logica(tamCelda);
		frame.getContentPane().add(new JLabel("PUNTjsdhfjahgjadfj"));
		for(int i = 0; i<20; i++){
			for(int j = 1; j <21 ; j++){
				Celda c = logica.getCelda(i,j-1);

				GridBagConstraints gbc_c = new GridBagConstraints();
				gbc_c.fill = GridBagConstraints.BOTH;
				gbc_c.gridx = i;
				gbc_c.gridy = j;
				frame.getContentPane().add(c, gbc_c);
			}
		}
		for(int i = 1 ; i<19; i++){
			for(int j = 1; j <19 ; j++){
				logica.getCelda(i,j).setImagen("");
			}
		}
		logica.getCelda(3,6).setImagen("/images/cuerpo.png");
		logica.getCelda(3,7).setImagen("/images/cuerpo.png");
		logica.getCelda(3,6).setImagen("");
		//logica.getCelda(0,3).setImagen("");
//		//Creacion del tablero
//		TableroGrafico tablero = new TableroGrafico();
//		//frame.getContentPane().add(tablero);
//
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[] {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
//		gridBagLayout.rowHeights = new int[] {40, 40, 40, 40, 40,40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
//		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
//		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
//		frame.setContentPane(tablero);
//		frame.getContentPane().setLayout(gridBagLayout);
//		GridBagConstraints gbc_tglbtnNewToggleButton2 = new GridBagConstraints();
//		gbc_tglbtnNewToggleButton2.fill = GridBagConstraints.BOTH;
//		gbc_tglbtnNewToggleButton2.gridx = 0;
//		gbc_tglbtnNewToggleButton2.gridy = 0;
//		//frame.getContentPane().add(tablero,gbc_tglbtnNewToggleButton2 );
//		frame.add(new JLabel("HIKAA"));

//		Logica logica = new Logica();
//
//		for(int i = 0; i<20; i++){
//			for(int j = 0; j <20 ; j++){
//				Celda c = logica.getCelda(i,j);
//
//				GridBagConstraints gbc_tglbtnNewToggleButton2 = new GridBagConstraints();
//				gbc_tglbtnNewToggleButton2.fill = GridBagConstraints.BOTH;
//				gbc_tglbtnNewToggleButton2.gridx = i;
//				gbc_tglbtnNewToggleButton2.gridy = j;
//				frame.getContentPane().add(c, gbc_tglbtnNewToggleButton2);
//			}
//		}
		//this.repaint();
		//tablero.cambiarCelda(0, 0, "/images/cuerpo.png");
		//tablero.cambiarCelda(0, 1, "images/cuerpo.png");
		//tablero.cambiarCelda(0, 2, "images/cuerpo.png");
		//logica.getCelda(3,6).setImagen("");
		frame.repaint();
	}
	

	private class TableroGrafico extends JPanel{
		private int anchoTablero = anchoVentana-100;
		private int altoTablero = altoVentana-100;
		private int anchoCelda = anchoTablero/columnas;
		private int altoCelda = altoTablero/filas;
		
		public void paint(Graphics g) {
			int esquinaX = 50;
			int esquinaY = 50;
			
			g.setColor(new Color(194,201,155)); //PONER ACA UNO OTRO DE LOS COLORES DEL FONDO
			g.fillRect(esquinaX, esquinaY, anchoTablero , altoTablero);
			
			 for(int cols = 0; cols < columnas/2; cols++) {
				for(int i= esquinaX; i < anchoTablero; i+=2*anchoCelda) { 
					g.setColor(new Color(202,209,161)); //PONER ACA EL OTRO DE LOS COLORES DEL FONDO
					g.fillRect(i, esquinaY + (cols * 2 * altoCelda), anchoCelda, altoCelda); 
					g.fillRect(i + anchoCelda, esquinaY + altoCelda + (cols * 2 * altoCelda), anchoCelda, altoCelda);
				}
			} 
			
		}
		
		public void cambiarCelda(int posX, int posY, String pathImg){
			ClassLoader classLoader = getClass().getClassLoader();
			URL resURL =  classLoader.getResource("/images/cuerpo.png");

			try {
				File imgfile = new File(resURL.toURI());
				//ImageIcon ic = new ImageIcon(VentanaPrueba.class.getResource(pathImg));
				//Image image = ic.getImage(); // transform it
				//Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
				//BufferedImage img = ImageIO.read(new File(pathImg));
				Image img = ImageIO.read(imgfile);
				Graphics g = getGraphics();
				g.drawImage(img, posX * anchoCelda, posY * altoCelda, null);
			} catch(IOException e) {
				System.out.println("Beware, se rompio lo que sea que lee los paths y los pasa a imagenes para el tablero");
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
		
	}

}
