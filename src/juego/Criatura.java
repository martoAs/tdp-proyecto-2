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

	public Criatura(Logica logica, Map posNoPoner){
		controlador = logica;
		cuerpo = new LinkedList<Celda>();
		graficos = new CriaturaGrafica();
		//Se inicializa la criatura

		Random random = new Random();
		int posx =  random.nextInt(19);
		int posy =  random.nextInt(19);
		System.out.println("("+posx+","+posy+")");


		 while(posNoPoner.get(controlador.getCelda(posx,posy))!= null){
			  posx =  random.nextInt(19);
			  posy =  random.nextInt(19);
			 System.out.println("("+posx+","+posy+")");
		 };


		cuerpo.addFirst(controlador.getCelda(posx,posy));
		cuerpo.getFirst().setOcupada(graficos.getImagenCabeza());
		cuerpo.addLast(controlador.getCelda(posx+1,posy));
		cuerpo.getLast().setOcupada(graficos.getImagenCuerpo());
		cuerpo.addLast(controlador.getCelda(posx+2,posy));
		cuerpo.getLast().setOcupada(graficos.getImagenCuerpo());
		direccion = 'a';
		estaViva = true;
		juegoAndando = true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(estaViva && juegoAndando){
			mover();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		if(!estaViva) controlador.mostrarPuntajes();
		this.stop();
		for(Celda c: cuerpo){
			c.desocupar();
		}
		cuerpo = new LinkedList<>();
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
		if(estaViva && juegoAndando){
			nuevaCabeza.setOcupada(graficos.getImagenCabeza());
			cuerpo.getFirst().setOcupada(graficos.getImagenCuerpo());
			cuerpo.addFirst(nuevaCabeza);

			if(celdasAgregadas>0) celdasAgregadas--;
			else{
				borrar.desocupar();
				cuerpo.removeLast();
			}

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
