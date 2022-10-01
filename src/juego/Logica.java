package juego;

import consumibles.Consumible;

public class Logica {
	
	protected int puntaje;
	protected Celda[][] tablero;

	protected Criatura criatura;

	public Logica (int tam){
		tablero = new Celda[20][20];
		int es1 = 1;
		for(int j = 0; j<20; j++){
			Celda p = new Celda("/images/pared.jpg",tam,0,j);
			tablero[0][j] = p;
			Celda p2 = new Celda("/images/pared.jpg",tam,19,j);
			tablero[19][j] = p2;
		}
		for(int i = 1; i<19; i++){
			Celda p = new Celda("/images/pared.jpg",tam,i,0);
			tablero[i][0] = p;
			es1 = 1 - es1;
			for(int j = 1; j<19;j++){
				String imagen = "/images/celda2.jpg";
				if (es1==1) imagen = "/images/celda1.jpg";
				Celda c = new Celda(imagen,tam,i,j);
				tablero[i][j] = c;
				es1 = 1 - es1;
			}
			Celda p2 = new Celda("/images/pared.jpg",tam,i,19);
			tablero[i][19] = p2;
		}
		criatura = new Criatura(this);


	}
	public void cambiarDireccion(char dir){
		criatura.setDireccion(dir);

	}
	public void empezarJuego(){
		criatura.start();

	}
	public void sumarPuntaje(int puntaje){
		this.puntaje = this.puntaje + puntaje;
	}

	public Celda getCelda(int x, int y){
		return tablero[x][y];

	}
}
