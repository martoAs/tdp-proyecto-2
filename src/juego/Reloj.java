package juego;

import java.sql.Time;

import javax.swing.JLabel;

public class Reloj extends Thread{
	boolean finished;
	JLabel label;
	
	public Reloj(JLabel l) {
		finished = false;
		label = l;
	}
	
	public void run() {
		Time tiempo = new Time(0);
		tiempo.setHours(0);
		while(!finished) {
			try {
				sleep(1000);
				tiempo.setTime(tiempo.getTime()+1000);
				label.setText(tiempo.toString());
				label.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		this.stop();
	}
	
	public void setFinished(boolean f) {
		finished = f;
	}
}
