package juego;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ventana implements KeyListener {

	private int largoVentana = 750;
	private int anchoVentana = 850;
	private Logica logica;

	private JLabel puntaje;
	private DefaultTableModel modelo;
	private JFrame frame;
	
	public void initialize() {
		int tamCelda = 28;
		
		//Creamos el frame de la ventana
		frame = new JFrame();
		frame.setBounds(300, 20, largoVentana, anchoVentana);
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
		menuBar.setBorder(new EmptyBorder(0, anchoVentana/5, 0, anchoVentana/5));
		
		JMenuItem Mpuntaje = new JMenuItem("PUNTAJE: ");
		Mpuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		Mpuntaje.setPreferredSize(new Dimension(100, 10));
		Mpuntaje.setEnabled(false);
		Mpuntaje.setBackground(Color.BLACK);
		Mpuntaje.setForeground(Color.WHITE);
		Mpuntaje.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(Mpuntaje);

		puntaje = new JLabel("0");
		puntaje.setBackground(Color.BLACK);
		puntaje.setForeground(Color.WHITE);
		puntaje.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(puntaje);
		
		JMenuItem Mtiempo = new JMenuItem("TIEMPO: ");
		Mtiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Mtiempo.setPreferredSize(new Dimension(100, 10));
		Mtiempo.setEnabled(false);
		Mtiempo.setBackground(Color.BLACK);
		Mtiempo.setForeground(Color.WHITE);
		Mtiempo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(Mtiempo);
		
		JLabel t = new JLabel("00:00:00");
		t.setBackground(Color.BLACK);
		t.setForeground(Color.WHITE);
		t.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		menuBar.add(t);
		
		frame.setJMenuBar(menuBar);
		
		//tabla y popUp del ranking
		JTable tablaRanking = new JTable();
		tablaRanking.setForeground(Color.WHITE);
		tablaRanking.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		tablaRanking.setBackground(Color.DARK_GRAY);
		tablaRanking.setEnabled(false);
		
		modelo = new DefaultTableModel(6,3);
		tablaRanking.setModel(modelo);
		
		JPopupMenu popRanking = new JPopupMenu();
		popRanking.setVisible(false);
		popRanking.add(tablaRanking);
		
		//Insertamos las celdas al panel del juego
		logica = new Logica(tamCelda, this, t);
		
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
		
		/*El popUp del ranking se abre haciendo click en la pantalla, para ocultarlo hacer click nuevamente
		 *no se muestra si el ranking esta vacio*/
		cargarRanking();
		frame.getContentPane().addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				Ranking ranking = logica.getRanking();
				
				if(!popRanking.isVisible() && ranking.getSize() > 0) {
					popRanking.setLocation(e.getXOnScreen(), e.getYOnScreen());
					popRanking.setVisible(true);
				}
				else{
					popRanking.setVisible(false);
				}
			}
			
		});
		
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

	//Accede a ranking para cargar los datos de los jugadores en la tabla del PopUp ranking
	public void cargarRanking() {
		Ranking ranking = logica.getRanking();
		if(ranking.getSize() > 0) {
			modelo.setRowCount(0);
			modelo.addRow(new Object[] {"NOMBRE", "PUNTAJE", "TIEMPO"});
			if(ranking.existeArchivo())
				ranking = ranking.abrir();

			for(int i=0; i < ranking.getSize(); i++) {
				Jugador jugador = ranking.getJugador(i);
				modelo.addRow(new Object[] {jugador.getNombre(), jugador.getPuntaje(), jugador.getTiempo()} );
			}
		}
	}

	//Modifica el texto de la etiqueta del puntaje
	public void setPuntaje(String puntos){
		puntaje.setText(puntos);
	}
	
	//Muestra la ventana en la que el usuario ingresa su nombre para ingresar al ranking
	public String ingresarNombre() {
		UIManager.put("OptionPane.background", Color.DARK_GRAY);
		UIManager.put("Panel.background", Color.DARK_GRAY); 
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		JPanel ventanaNombre = new JPanel(new FlowLayout());
		ventanaNombre.setBackground(Color.DARK_GRAY);
		JLabel mensaje = new JLabel("Ingrese su nombre para participar del ranking: ");
		mensaje.setForeground(Color.WHITE);
		ventanaNombre.add(mensaje);
		
		String nombreJugador = JOptionPane.showInputDialog(frame, ventanaNombre, "Fin del juego!", JOptionPane.PLAIN_MESSAGE);
		return nombreJugador;
	}
}
