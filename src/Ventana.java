import java.awt.EventQueue;

import javax.swing.JFrame;

public class Ventana {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Ventana() {
		//initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 895, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
