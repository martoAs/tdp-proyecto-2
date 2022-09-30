package juego;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
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
		Logica logica = new Logica();

		for(int i = 0; i<20; i++){
			for(int j = 0; j <20 ; j++){
				Celda c = logica.getCelda(i,j);

				GridBagConstraints gbc_c = new GridBagConstraints();
				gbc_c.fill = GridBagConstraints.BOTH;
				gbc_c.gridx = i;
				gbc_c.gridy = j;
				frame.getContentPane().add(c, gbc_c);
			}
		}

		/*for (int i = 0; i < filas; i++) {
			JToggleButton tglbtnNewToggleButton = new JToggleButton("");
			tglbtnNewToggleButton.setBackground(Color.GREEN);
			tglbtnNewToggleButton.setEnabled(false);
			tglbtnNewToggleButton.setSize(20, 20);
			GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
			gbc_tglbtnNewToggleButton.fill = GridBagConstraints.BOTH;
			gbc_tglbtnNewToggleButton.gridx = i;
			gbc_tglbtnNewToggleButton.gridy = 0;
			frame.getContentPane().add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
			for(int j = 1; j < columnas; j++) {
				JToggleButton tglbtnNewToggleButton2 = new JToggleButton("");
				tglbtnNewToggleButton2.setBackground(Color.ORANGE);
				tglbtnNewToggleButton2.setEnabled(false);
				tglbtnNewToggleButton2.setSize(20, 20);
				GridBagConstraints gbc_tglbtnNewToggleButton2 = new GridBagConstraints();
				gbc_tglbtnNewToggleButton2.fill = GridBagConstraints.BOTH;
				gbc_tglbtnNewToggleButton2.gridx = i;
				gbc_tglbtnNewToggleButton2.gridy = j;
				frame.getContentPane().add(tglbtnNewToggleButton2, gbc_tglbtnNewToggleButton2);
			}
		} */

		frame.setVisible(true);
	}

}
