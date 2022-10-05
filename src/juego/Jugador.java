package juego;

import java.io.Serializable;

public class Jugador implements Serializable{
	private int puntaje;
	private String tiempo;
	
	public Jugador(int p, String t) {
		puntaje = p;
		tiempo = t;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
}
