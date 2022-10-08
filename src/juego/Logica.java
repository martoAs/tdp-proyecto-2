package juego;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import consumibles.Arania;
import consumibles.Consumible;
import consumibles.Futbol;
import consumibles.Lombriz;
import consumibles.Pescado;
import consumibles.Psicodelico;
import consumibles.Ratita;
import consumibles.Redondito;
import consumibles.Sapo;

public class Logica {
	
	protected int puntaje;
	protected String tiempo; 
	protected LinkedList<String> archivos;
	
	protected Criatura criatura;
	protected Ranking ranking;
	protected Celda[][] tablero;
	protected Stack<Consumible> pila;
	protected Ventana ventana;
	protected Reloj time;

	public Logica (int tam, Ventana ventana,JLabel l){
		//Se crea el tablero
		tablero = new Celda[20][20];
		
		//Listado de niveles
		archivos = new LinkedList<String>();
		archivos.add("src/niveles/nivel1.txt");
		archivos.add("src/niveles/nivel2.txt");
		archivos.add("src/niveles/nivel3.txt");
		archivos.add("src/niveles/nivel4.txt");
		archivos.add("src/niveles/nivel5.txt"); 
		
		iniciarNivel(1, tam);
		criatura = new Criatura(this);
		this.ventana = ventana;
		time = new Reloj(l);
		
	}
	
	public void cambiarDireccion(char dir){
		criatura.setDireccion(dir);

	}
	
	public char getDireccion() {
		return criatura.getDireccion();
	}
	
	public void empezarJuego(){
		criatura.start();
		time.start();
	}
	
	public void sumarPuntaje(int puntaje){
		this.puntaje = this.puntaje + puntaje;
		System.out.println(this.puntaje);
	}

	public Celda getCelda(int x, int y){
		return tablero[x][y];

	}
	
	private void iniciarNivel(int nivel, int tam) {
		Path archivoNivel = Path.of(archivos.get(nivel));
		String todoNivel = ""; //Archivo que contiene todos los caracteres del nivel
		List<String> filas; //Cada arreglo tiene una fila
		try {
			filas = Files.readAllLines(archivoNivel);

			
			for(int fila = 0; fila < filas.size(); fila++) {
				for(int col = 0; col < 20; col++) {
					System.out.print(filas.get(fila).charAt(col));
					switch(filas.get(fila).charAt(col)) {
						case '#', 'P' -> {
							Celda pared = new Celda(tam, col, fila);
							pared.setOcupada("pared");
							tablero[col][fila] = pared;
						}
						case '.' -> {
							Celda fondo = new Celda(tam, col, fila);
							fondo.desocupar();
							tablero[col][fila] = fondo;
						}
						case 'r' -> {
							Ratita rat = new Ratita();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(rat);
							tablero[col][fila] = fondo;
						}
						
						case 'l' -> {
							Lombriz lomb = new Lombriz();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(lomb);
							tablero[col][fila] = fondo;
						}
						case 'p' -> {
							Pescado pez = new Pescado();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(pez);
							tablero[col][fila] = fondo;
						}
						case 'i' -> {
							Arania julian = new Arania(); // :)
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(julian);
							tablero[col][fila] = fondo;
						}
						case 't' -> {
							Sapo sapo = new Sapo();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(sapo);
							tablero[col][fila] = fondo;
						}
						case 'd' -> {
							Psicodelico psi = new Psicodelico();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(psi);
							tablero[col][fila] = fondo;
						}
						case 'f' -> {
							Futbol fulvo = new Futbol();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(fulvo);
							tablero[col][fila] = fondo;
						}
						case 'c' -> {
							Redondito redondo = new Redondito();
							Celda fondo = new Celda(tam, col, fila);
							fondo.setConsumible(redondo);
							tablero[col][fila] = fondo;
						}
						
 					}
				}

				System.out.println();
			}
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void mostrarPuntajes() {
		time.setFinished(true);
		String nombre = ventana.ingresarNombre();
		if(nombre != null) { //El usuario presiono aceptar 
			Jugador jugador = new Jugador(nombre, puntaje, tiempo);
			ranking.agregarJugador(jugador);
			ranking.ordenarPorPuntaje();
			ranking.ordenarPorTiempo();
		}
		ventana.cargarRanking();
	}
	
	public void terminoNivel(){
		
	}
	
	public Ranking getRanking() {
		return ranking;
	}
}
