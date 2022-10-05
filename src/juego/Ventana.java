package juego;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import static java.awt.BorderLayout.*;

public class Ventana implements KeyListener {

	private JFrame frame;
	int filas = 20;
	int columnas = 20;
	int tamCelda = 35;
	private Logica logica;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda,tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda};
		gridBagLayout.rowHeights = new int[] {tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda,tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		JPanel tablero = new JPanel();
		tablero.setLayout(gridBagLayout);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(tablero, CENTER);
		JPanel arriba = new JPanel();
		frame.getContentPane().add(arriba, NORTH);
		JLabel puntaje = new JLabel("PUNTAJE:");
		arriba.add(puntaje);
		logica = new Logica(tamCelda);

		for(int i = 0; i<20; i++){
			for(int j = 0; j <20 ; j++){
				Celda c = logica.getCelda(i,j);

				GridBagConstraints gbc_c = new GridBagConstraints();
				gbc_c.fill = GridBagConstraints.BOTH;
				gbc_c.gridx = i;
				gbc_c.gridy = j;
				tablero.add(c, gbc_c);
			}
		}
		
		frame.addKeyListener(this);
		logica.empezarJuego();

		frame.setVisible(true);
		
	}

	@Override 
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	//Metodo oyente de las teclas/letras para poder mover la serpiente 
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:{
			if(logica.getDireccion()!='s') logica.cambiarDireccion('w');
		}break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:{
			if(logica.getDireccion()!='d') logica.cambiarDireccion('a');
		}break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:{
			if(logica.getDireccion()!='w') logica.cambiarDireccion('s');
		}break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:{
			if(logica.getDireccion()!='a') logica.cambiarDireccion('d');
		}break;
	}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

}
