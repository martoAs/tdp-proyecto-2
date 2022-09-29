import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import java.awt.Color;


public class Ventana {

	private JFrame frame;
	int filas = 20;
	int columnas = 20;
	
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
	public Ventana() {
		//initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
		gridBagLayout.rowHeights = new int[] {40, 40, 40, 40, 40,40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		
		frame.getContentPane().setLayout(gridBagLayout);
		
		for (int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				JToggleButton tglbtnNewToggleButton = new JToggleButton("");
				tglbtnNewToggleButton.setBackground(Color.ORANGE);
				tglbtnNewToggleButton.setEnabled(false);
				tglbtnNewToggleButton.setSize(20, 20);
				GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
				gbc_tglbtnNewToggleButton.fill = GridBagConstraints.BOTH;
				gbc_tglbtnNewToggleButton.gridx = i;
				gbc_tglbtnNewToggleButton.gridy = j;
				frame.getContentPane().add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
			}
		}

		frame.setVisible(true);
	}

}
