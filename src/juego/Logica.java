package juego;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import javax.swing.JLabel;

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
	//protected String tiempo; 
	protected LinkedList<String> archivos;
	
	protected Criatura criatura;
	protected Ranking ranking;
	protected Celda[][] tablero;
	protected Stack<Consumible> pila;
	protected Ventana ventana;
	protected Reloj time;
	
	protected List<Celda> celdasConConsumible;
	protected int nivel;
	protected int tam;
	protected JLabel labelReloj;

	public Logica (int tam, Ventana ventana,JLabel l){
		//Se crea el tablero
		tablero = new Celda[20][20];
		for(int i = 0 ; i<20; i++){
			for(int j = 0; j< 20; j++){
				Celda c = new Celda(tam, i, j);
				tablero[i][j] = c;
			}
		}
		celdasConConsumible = new ArrayList<Celda>();
		nivel = 0;
		this.tam = tam;
		
		//Listado de niveles
		archivos = new LinkedList<String>();
		archivos.add("src/archivos/nivel1.txt");
		archivos.add("src/archivos/nivel2.txt");
		archivos.add("src/archivos/nivel3.txt");
		archivos.add("src/archivos/nivel4.txt");
		archivos.add("src/archivos/nivel5.txt"); 
		
		iniciarNivel(nivel, this.tam);
		this.ventana = ventana;
		labelReloj = l;
		time = new Reloj(labelReloj);
		ranking = new Ranking("Ranking");
		
		if(ranking.existeArchivo())
			ranking = ranking.abrir();
		
	}
	
	public void cambiarDireccion(char dir){
		criatura.setDireccion(dir);

	}
	
	public char getDireccion() {
		return criatura.getDireccion();
	}
	
	public void empezarJuego(){

		time.start();
	}
	
	public void sumarPuntaje(int puntaje){
		this.puntaje = this.puntaje + puntaje;
		System.out.println(this.puntaje);
		ventana.setPuntaje(""+this.puntaje);
	}

	public Celda getCelda(int x, int y){
		return tablero[x][y];

	}
	
	private void iniciarNivel(int nivel, int tam) {
		Path archivoNivel = Path.of(archivos.get(nivel));
		List<String> filas; //Cada arreglo tiene una fila
		try {
			filas = Files.readAllLines(archivoNivel);

			Map<Celda,Integer> posImposibles = new HashMap<Celda,Integer>();

			for(int fila = 0; fila < filas.size(); fila++) {
				for(int col = 0; col < 20; col++) {
					//System.out.print(filas.get(fila).charAt(col));
					switch(filas.get(fila).charAt(col)) {
						case '#' -> {
							Celda pared = tablero[col][fila];
							pared.setOcupada("pared");
							//tablero[col][fila] = pared;
							posImposibles.put(tablero[col][fila],1);
							if(col>0) posImposibles.put(tablero[col-1][fila],1);
							if(col-1>0) posImposibles.put(tablero[col-2][fila],1);
							if(col<19) posImposibles.put(tablero[col+1][fila],1);
							if(col+1<19) posImposibles.put(tablero[col+2][fila],1);
							if(col+2<19) posImposibles.put(tablero[col+3][fila],1);
							if(col+3<19) posImposibles.put(tablero[col+4][fila],1);
						}
						case '.' -> {
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 'r' -> {
							Ratita rat = new Ratita();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(rat);
							celdasConConsumible.add(fondoAux);
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						
						case 'l' -> {
							Lombriz lomb = new Lombriz();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(lomb);
							celdasConConsumible.add(fondoAux);
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 'p' -> {
							Pescado pez = new Pescado();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(pez);
							celdasConConsumible.add(fondoAux);
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 'i' -> {
							Arania julian = new Arania(); // :)
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(julian);
							celdasConConsumible.add(fondoAux);
							Celda fondo =tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 't' -> {
							Sapo sapo = new Sapo();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(sapo);
							celdasConConsumible.add(fondoAux);
							Celda fondo =tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 'd' -> {
							Psicodelico psi = new Psicodelico();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(psi);
							celdasConConsumible.add(fondoAux);
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 'f' -> {
							Futbol fulvo = new Futbol();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(fulvo);
							celdasConConsumible.add(fondoAux);
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						case 'c' -> {
							Redondito redondo = new Redondito();
							Celda fondoAux = new Celda(tam, col, fila);
							fondoAux.setConsumible(redondo);
							celdasConConsumible.add(fondoAux);
							Celda fondo = tablero[col][fila];
							fondo.desocupar();
							//tablero[col][fila] = fondo;
						}
						
 					}
				}

				//System.out.println();
			}
			
			ponerConsumible();
			criatura = new Criatura(this, posImposibles);
			criatura.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void mostrarPuntajes() {
		time.setFinished(true);
		String nombre = ventana.ingresarNombre();
		if(nombre != null) { //El usuario presiono aceptar 
			Jugador jugador = new Jugador(nombre, puntaje, time.getTiempo().toString());
			ranking.agregarJugador(jugador);
			ranking.ordenar();
			ranking.ordenar();
			ranking.guardar();
		}
		
		ventana.cargarRanking();
	}
	
	public void terminoNivel(){
		nivel++;
		
		
		if(nivel<5)	
			iniciarNivel(nivel, tam);
		else{
			mostrarPuntajes();
			puntaje = 0;
			time = new Reloj(labelReloj);
			nivel = 0;
			iniciarNivel(nivel, tam);
			empezarJuego();
		}
	}
	
	public Ranking getRanking() {
		return ranking;
	}
	
	public void ponerConsumible() {
		if(celdasConConsumible.size() > 0) {
				Random rand = new Random();
				int random = rand.nextInt(celdasConConsumible.size());
				System.out.println(rand.nextInt(1));
				Celda primero = celdasConConsumible.get(random);
				celdasConConsumible.remove(random);
				
				
				while(tablero[primero.getXenTablero()][primero.getYenTablero()].estaOcupada()==true){
					
					if(celdasConConsumible.size() == 0)
						random = 0;
					else {
						random = rand.nextInt(celdasConConsumible.size());
						primero = celdasConConsumible.get(random);
						celdasConConsumible.remove(random);
					}
					
					random = rand.nextInt(celdasConConsumible.size());
					primero = celdasConConsumible.get(random);
					celdasConConsumible.remove(random);
				}
				

				tablero[primero.getXenTablero()][primero.getYenTablero()].setConsumible(primero.getConsumible());
		}
		else
			criatura.setJuegoAndando(false);
			
	}
}
