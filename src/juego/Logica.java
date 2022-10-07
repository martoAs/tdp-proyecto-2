package juego;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		
		//Listado de niveles
		archivos = new LinkedList<String>();
		archivos.add("src/niveles/nivel1.txt");
		archivos.add("src/niveles/nivel2.txt");
		
		
		
		iniciarNivel(1, tam);
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
						case '#', 'F', 'P' -> {
							Celda pared = new Celda(tam, col, fila);
							pared.setImagenPared();
							tablero[col][fila] = pared;
						}
						case '.' -> {
							Celda fondo = new Celda(tam, col, fila);
							fondo.setImagenFondo();
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
	
	private void mostrarPuntajes() {
		
	}
	
	public void terminoNivel(){

	}
}
