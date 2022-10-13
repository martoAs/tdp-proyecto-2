package juego;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Criatura extends Thread {

	protected boolean estaViva;
	protected LinkedList<Celda> cuerpo;
	protected char direccion;

	protected boolean juegoAndando;
	protected Logica controlador;

	protected CriaturaGrafica graficos;

	protected int celdasAgregadas ;

	public Criatura(Logica logica){
		controlador = logica;
		cuerpo = new LinkedList<Celda>();
		graficos = new CriaturaGrafica();
		estaViva = true;
		juegoAndando = true;
	}

	public void elegirDireccion() {
		Random random = new Random();
		switch(random.nextInt(4)) {
		case 0:
			direccion = 'w';
			break;
		case 1:
			direccion = 'a';
			break;
		case 2:
			direccion = 's';
			break;
		case 3:
			direccion = 'd';
			break;
		}   
	}
	
	public void elegirPosicionCriatura(Map<Celda,Integer> posNoPoner) {
		/* Elegimos donde comienza la criatura aleatoriamente entre las posiciones sin ocupar.
		 * La criatura siempre comienza como tres bloques verticales u horizontales segun la direccion en la que 
		 * se mueva. */
		
		Random random = new Random();
		
		int posx_cabeza =  random.nextInt(19);
		int posy_cabeza =  random.nextInt(19);
		
		while(posNoPoner.get(controlador.getCelda(posx_cabeza,posy_cabeza))!= null){
			  posx_cabeza =  random.nextInt(19);
			  posy_cabeza =  random.nextInt(19);
		 };
		 
		int posx_cuerpo = posx_cabeza+1; //Posiciones para izquierda
		int posy_cuerpo = posy_cabeza;
		int posx_cola = posx_cabeza+2;
		int posy_cola = posy_cabeza;
		switch(direccion) {
		case 'd':
			posx_cuerpo = posx_cabeza-1; //Las posiciones en y se corresponden con las inicializadas
			posx_cola = posx_cabeza-2;
			break;
		case 'w':
			posx_cuerpo = posx_cabeza;
			posy_cuerpo = posy_cabeza+1;
			posx_cola = posx_cabeza;
			posy_cuerpo = posy_cabeza+2;
			break;
		case 's':
			posx_cuerpo = posx_cabeza;
			posy_cuerpo = posy_cabeza-1;
			posx_cola = posx_cabeza;
			posy_cola = posy_cabeza-2;
			break;
		}
		
		cuerpo.addFirst(controlador.getCelda(posx_cabeza,posy_cabeza));
		cuerpo.getFirst().setOcupada(graficos.getImagenCabeza());
		cuerpo.addLast(controlador.getCelda(posx_cuerpo,posy_cuerpo));
		cuerpo.getLast().setOcupada(graficos.getImagenCuerpo());
		cuerpo.addLast(controlador.getCelda(posx_cola,posy_cola));
		cuerpo.getLast().setOcupada(graficos.getImagenCuerpo());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(estaViva && juegoAndando){
			mover();
			try {
				sleep(130);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		if(!estaViva) controlador.mostrarPuntajes();
		
		if(!juegoAndando) controlador.terminoNivel();
		this.stop();
		
	}


	public void setJuegoAndando(boolean esta){
		juegoAndando = esta;
	}

	public void setEstaViva(boolean viva) {
		estaViva = viva;
	}
	
	public boolean getEstaViva() {
		return estaViva;
	}
	
	public void setDireccion(char d){
		direccion = d;
	}
	
	public char getDireccion() {
		return direccion;
	}
	
	private void mover(){
		Celda cabeza = cuerpo.getFirst();
		Celda nuevaCabeza = cabeza; //se inicializa para no repetir codigo en el switch
		Celda borrar = cuerpo.getLast();
		int sgtX, sgtY;
		
		switch (direccion){
			case 'a': {

					sgtX = cabeza.getXenTablero()-1;
					nuevaCabeza = controlador.getCelda(sgtX,cabeza.getYenTablero());	

			}break;
			case 'd': {

					sgtX = cabeza.getXenTablero()+1;
					nuevaCabeza = controlador.getCelda(sgtX,cabeza.getYenTablero());

			}break;
			case 'w': {

					sgtY = cabeza.getYenTablero()-1;
					nuevaCabeza = controlador.getCelda(cabeza.getXenTablero(),sgtY);

			}break;
			case 's': {

					sgtY = cabeza.getYenTablero()+1;
					nuevaCabeza = controlador.getCelda(cabeza.getXenTablero(),sgtY);			

			}break;
		}

		nuevaCabeza.efecto(this);
		if(estaViva){
			nuevaCabeza.setOcupada(graficos.getImagenCabeza());
			cuerpo.getFirst().setOcupada(graficos.getImagenCuerpo());
			cuerpo.addFirst(nuevaCabeza);

			if(celdasAgregadas>0) celdasAgregadas--;
			else{
				borrar.desocupar();
				cuerpo.removeLast();
			}

		}
		if(!juegoAndando) {
			for(Celda c: cuerpo){
				c.desocupar();
			}
			cuerpo = new LinkedList<>();
			
		}
		
	}

	public void cambiarSkin(int estado) {
		graficos.cambiarEstado(estado);
		for(Celda c: cuerpo){
			c.setOcupada(graficos.getImagenCuerpo());
		}
	}
	
	public void agrandarCriatura(int tam) {
		celdasAgregadas = tam;
	}

	public Logica getControlador() {
		return controlador;
	}
	
	public void cambiarPuntaje(int puntos) {
		controlador.sumarPuntaje(puntos);
	}
	
	public void consumibleComido() {
		controlador.ponerConsumible();
	}

}
