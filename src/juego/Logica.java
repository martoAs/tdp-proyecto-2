package juego;

import consumibles.Consumible;

public class Logica {
	
	protected int puntaje;
	protected Celda[][] tablero;

	public Logica(){
		tablero = new Celda[20][20];

		for(int i = 0; i<20; i++){
			for(int j = 0; j<20;j++){
				Celda c = new Celda("/images/cuerpo.png");
				tablero[i][j] = c;

			}

		}

	}
	public void sumarPuntaje(int puntaje){
		this.puntaje = this.puntaje + puntaje;
	}

	public Celda getCelda(int x, int y){
		return tablero[x][y];

	}
}
