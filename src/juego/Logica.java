package juego;

import java.util.LinkedList;
import java.util.Stack;

import consumibles.Consumible;

public class Logica {
	
	protected int puntaje;
	protected String tiempo; 
	protected LinkedList<String> archivos;
	
	protected Criatura criatura;
	protected Ranking ranking;
	protected Celda[][] tablero;
	protected Stack<Consumible> pila;

	public Logica (int tam){
		//Se crea el tablero
		tablero = new Celda[20][20];

		//Se inicializan las celdas de los extremos como paredes
		for(int j = 0; j<20; j++){
			Celda p = new Celda(tam,0,j);
			p.setImagenPared();
			tablero[0][j] = p;
			Celda p2 = new Celda(tam,19,j);
			p2.setImagenPared();
			tablero[19][j] = p2;
		}
		for(int i = 1; i<19; i++){
			Celda p = new Celda(tam,i,0);
			p.setImagenPared();
			tablero[i][0] = p;

			//Se inicializan las celdas del medio intercaladas como tablero de ajedrez
			for(int j = 1; j<19;j++){

				Celda c = new Celda(tam,i,j);
				c.setImagenFondo();
				tablero[i][j] = c;

			}
			Celda p2 = new Celda(tam,i,19);
			p2.setImagenPared();
			tablero[i][19] = p2;
		}
		criatura = new Criatura(this);

	}
	
	public void cambiarDireccion(char dir){
		criatura.setDireccion(dir);

	}
	
	public char getDireccion() {
		return criatura.getDireccion();
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
	
	private void iniciarNivel(int nivel) {

	}
	
	private void mostrarPuntajes() {
		
	}
	
	public void terminoNivel(){

	}
}
