package juego;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana implements KeyListener {

	private int largoVentana = 850;
	private int anchoVentana = 900;
	private Logica logica;
	private DefaultTableModel modelo;
	private JPopupMenu popRanking;
	
	public void initialize() {
		int tamCelda = 35;
		
		//Creamos el frame de la ventana
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, largoVentana, anchoVentana);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//Creamos el panel que contiene al juego, se incluye en el frame
		JPanel panelJuego = new JPanel();
		panelJuego.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda,tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda};
		gridBagLayout.rowHeights = new int[] {tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda,tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda, tamCelda};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		panelJuego.setLayout(gridBagLayout);
		frame.getContentPane().add(panelJuego);
		
		//Creamos el menu para mostrar puntaje, tiempo y el ranking, se incluye en el frame
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setBorder(new EmptyBorder(0, 100, 0, -100));
		
		JMenuItem Mpuntaje = new JMenuItem("PUNTAJE: ");
		Mpuntaje.setEnabled(false);
		Mpuntaje.setBackground(Color.BLACK);
		Mpuntaje.setForeground(Color.WHITE);
		Mpuntaje.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(Mpuntaje);
		
		JMenuItem Mtiempo = new JMenuItem("TIEMPO: ");
		Mtiempo.setEnabled(false);
		Mtiempo.setBackground(Color.BLACK);
		Mtiempo.setForeground(Color.WHITE);
		Mtiempo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(Mtiempo);
		
		Component horizontalGlue = Box.createHorizontalGlue(); //Para que ranking se vea a la derecha
		horizontalGlue.setEnabled(false);
		menuBar.add(horizontalGlue);
		
		//tabla y popUp del ranking
		JTable tablaRanking = new JTable();
		tablaRanking.setForeground(Color.WHITE);
		tablaRanking.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		tablaRanking.setBackground(Color.DARK_GRAY);
		tablaRanking.setEnabled(false);
		
		modelo = new DefaultTableModel(6,3);
		modelo.addRow(new Object[] {"NOMBRE", "PUNTAJE", "TIEMPO"});
		tablaRanking.setModel(modelo);
		
		popRanking = new JPopupMenu();
		popRanking.setVisible(false);
		popRanking.add(tablaRanking);
		
		JOptionPane popNombre;
		JMenuItem MRanking = new JMenuItem("Ver ranking");
		MRanking.addActionListener(new ActionListener() { //Cuando se presiona despliega un popUp con el ranking
			public void actionPerformed(ActionEvent a){
				cargarRanking();
				popRanking.setLocation(100 + largoVentana, anchoVentana/2);
			}
		});
		
		MRanking.setForeground(Color.WHITE);
		MRanking.setBackground(Color.DARK_GRAY);
		MRanking.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(MRanking);
		frame.setJMenuBar(menuBar);
		
		//Insertamos las celdas al panel del juego
		logica = new Logica(tamCelda, this);
		
		for(int i = 0; i<20; i++){
			for(int j = 0; j <20 ; j++){
				Celda c = logica.getCelda(i,j);

				GridBagConstraints gbc_c = new GridBagConstraints();
				gbc_c.fill = GridBagConstraints.BOTH;
				gbc_c.gridx = i+1;
				gbc_c.gridy = j;
				panelJuego.add(c, gbc_c);
			}
		}
		
		logica.empezarJuego();
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

	public void cargarRanking() {
		Ranking ranking = logica.getRanking();
		for(int i=0; i < 5; i++) {
			Jugador jugador = ranking.getJugador(i);
			modelo.addRow(new Object[] {jugador.getNombre(), jugador.getPuntaje(), jugador.getTiempo()} );
		}
		popRanking.setVisible(true);
		popRanking.setLocation(largoVentana/2, anchoVentana/2);
	}
	
	public String ingresarNombre() {
		String nombreJugador = JOptionPane.showInputDialog(new JFrame(), "Ingrese su nombre para participar del ranking: ", "Ganaste!", JOptionPane.YES_OPTION);
		return nombreJugador;
	}
}
