
package juego;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana implements KeyListener {

	private int largoVentana = 700;
	private int anchoVentana = 700;
	private Logica logica;

	private JLabel puntaje;
	private JFrame frame;
	private JPopupMenu popRanking;
	private JTable tablaRanking;
	private JLabel t;
	private DefaultTableModel modelo;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		
		Font fuente = null;
		
		try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File("src/archivos/retroComputer.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fuente);
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		int tamCelda = 25;
		
		//Creamos el frame de la ventana
		frame = new JFrame();
		frame.setResizable(false);
		BufferedImage icono;
		File ic = new File("src/images/lsd1.jpg");
		try {
			icono= ImageIO.read(ic);
			frame.setIconImage(icono);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frame.setTitle("THE CREATURE");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - largoVentana) / 2;  
		int y = (screenSize.height - anchoVentana) / 2;
		frame.setBounds(x, y, largoVentana, anchoVentana);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(this);
		BorderLayout borderLayout = new BorderLayout();
		frame.getContentPane().setLayout(borderLayout);
		
		//Creamos el panel que contiene al juego, se incluye en el frame
		JPanel panelJuego = new JPanel();
		panelJuego.setBackground(Color.black);
		panelJuego.setPreferredSize(new Dimension(largoVentana - 100, anchoVentana-100));
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
		
		JMenuItem Mpuntaje = new JMenuItem("PUNTAJE: ");
		Mpuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		Mpuntaje.setMaximumSize(new Dimension(120, 20));
		Mpuntaje.setEnabled(false);
		Mpuntaje.setBackground(Color.BLACK);
		Mpuntaje.setForeground(Color.WHITE);
		Mpuntaje.setFont(fuente.deriveFont(15f));
		menuBar.add(Mpuntaje);

		puntaje = new JLabel("0");
		puntaje.setBackground(Color.BLACK);
		puntaje.setForeground(Color.WHITE);
		puntaje.setBorder(new EmptyBorder(0, 0, 0, 0));
		puntaje.setFont(fuente.deriveFont(15f));
		menuBar.add(puntaje);
		
		JMenuItem Mtiempo = new JMenuItem("TIEMPO: ");
		Mtiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Mtiempo.setMaximumSize(new Dimension(120, 20));
		Mtiempo.setEnabled(false);
		Mtiempo.setBackground(Color.BLACK);
		Mtiempo.setForeground(Color.WHITE);
		Mtiempo.setFont(fuente.deriveFont(15f));
		menuBar.add(Mtiempo);
		
		t = new JLabel("00:00:00");
		t.setBackground(Color.BLACK);
		t.setForeground(Color.WHITE);
		t.setFont(fuente.deriveFont(15f));
		menuBar.add(t);
		
		menuBar.add(Box.createGlue()); //Para que ranking quede a la derecha
		JMenuItem Mranking = new JMenuItem("Ver Ranking");
		Mranking.setHorizontalAlignment(SwingConstants.CENTER);
		Mranking.setMaximumSize(new Dimension(140, 20));
		Mranking.setEnabled(true);
		Mranking.setBackground(Color.BLACK);
		Mranking.setForeground(Color.WHITE);
		Mranking.setFont(fuente.deriveFont(15f));
		Mranking.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!popRanking.isVisible()) {
					cargarRanking();
				}
				else popRanking.setVisible(false);		
			}
		});
		
		popRanking = new JPopupMenu();
		popRanking.setVisible(false);
		popRanking.setBackground(Color.DARK_GRAY);
		popRanking.setLocation(x + largoVentana - 240, y + 60);
		menuBar.add(popRanking); 
		
		//Declaramos la tabla y su modelo para el popUp
		modelo = new DefaultTableModel(new String[] {"Nombre", "Puntaje", "Tiempo"} , 3);
		modelo.setRowCount(0);
		tablaRanking = new JTable();
		tablaRanking.setForeground(Color.WHITE);
		tablaRanking.setBackground(Color.DARK_GRAY);
		tablaRanking.setFont(fuente.deriveFont(15f));
		tablaRanking.getTableHeader().setBackground(Color.BLACK);
		tablaRanking.getTableHeader().setForeground(Color.WHITE);
		tablaRanking.getTableHeader().setFont(fuente.deriveFont(15f));
		tablaRanking.setEnabled(false);
		tablaRanking.setVisible(true);
		tablaRanking.setModel(modelo);
		
		menuBar.add(Mranking); 
		frame.setJMenuBar(menuBar);
		
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
		
		//Seccion about, con la informacion de los integrantes del grupo
		JPanel aboutPanel = new JPanel();
		aboutPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		aboutPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
		GridBagLayout gbl_aboutPanel = new GridBagLayout();
		gbl_aboutPanel.rowWeights = new double[]{1.0,1.0};
		gbl_aboutPanel.columnWeights = new double[]{1.0};
		aboutPanel.setLayout(gbl_aboutPanel);
		aboutPanel.setBackground(Color.black);
		
		JLabel aboutLabel = new JLabel("Made by VIVIDOS INC.: Martina Asteasuain, Romina Garcia");
		aboutLabel.setFont(fuente.deriveFont(11f));
		aboutLabel.setForeground(Color.GRAY);
		JLabel aboutLabel2 = new JLabel(" Rocio Zentrigen y Thomas Mintzer. Imagenes por Paz Berterreix");
		aboutLabel2.setFont(fuente.deriveFont(11f));
		aboutLabel2.setForeground(Color.GRAY);
		GridBagConstraints gbc_aboutLabel = new GridBagConstraints();
		gbc_aboutLabel.gridx = 0;
		gbc_aboutLabel.gridy = 1;
		aboutPanel.add(aboutLabel2,gbc_aboutLabel);
		aboutPanel.add(aboutLabel);
		frame.getContentPane().add(aboutPanel, BorderLayout.SOUTH);
		
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

	//Accede a ranking para cargar los datos de los jugadores en la tabla 
	public void cargarRanking() {
		
		Ranking ranking = logica.getRanking();
		if(ranking.getSize() > 0) {
			if(popRanking.getComponentCount() == 1) popRanking.remove(0); 
			JScrollPane scroll = new JScrollPane(tablaRanking);
			scroll.setOpaque(true);
			scroll.setBackground(Color.DARK_GRAY);
			scroll.setPreferredSize(new Dimension(anchoVentana/2, anchoVentana/6));
			popRanking.add(scroll);
			
			if(ranking.existeArchivo()) ranking = ranking.abrir();
			
			int agregados = modelo.getRowCount();  
			if(ranking.getSize() == 5) {
				modelo.setRowCount(0); //Limpia cada que se abre
				agregados = 0; //Si el size del ranking es algun jugador se sobreescribe
			}
				
			for(int i= agregados; i < ranking.getSize(); i++) {
				Jugador jugador = ranking.getJugador(i);
				modelo.addRow(new Object[] {jugador.getNombre(), jugador.getPuntaje(), jugador.getTiempo()} );
			}
		}
		else {
			//Si no hay jugadores se muestra un mensaje en lugar de la tabla vacia
			if(popRanking.getComponentCount() == 0) {
				JLabel rankingVacio = new JLabel("No hay jugadores para mostrar");
				rankingVacio.setBackground(Color.BLACK);
				rankingVacio.setForeground(Color.WHITE);
				rankingVacio.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
				popRanking.add(rankingVacio,0);
			}
		}
		popRanking.setVisible(true);
	}

	//Modifica el texto de la etiqueta del puntaje
	public void setPuntaje(String puntos){
		puntaje.setText(puntos);
	}
	
	//Muestra la ventana en la que el usuario ingresa su nombre para ingresar al ranking
	public String ingresarNombre() {
		//Cambiamos variables del JOptionPane y creamos el JPanel para el JOptionPane
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
	
	public int reiniciarJuego() {
		UIManager.put("OptionPane.background", Color.DARK_GRAY);
		UIManager.put("Panel.background", Color.DARK_GRAY); 
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		
		JPanel ventanaReinicio = new JPanel(new FlowLayout());
		ventanaReinicio.setBackground(Color.DARK_GRAY);
		JLabel mensaje = new JLabel("Desea reiniciar el juego?");
		mensaje.setForeground(Color.WHITE);
		ventanaReinicio.add(mensaje);
		int opcionElegida = JOptionPane.showConfirmDialog(frame,ventanaReinicio,"Elija una opcion...",JOptionPane.YES_NO_OPTION);
		popRanking.setVisible(false);
		t.setText("00:00:00");
		puntaje.setText("0");
		return opcionElegida;
	}
}

