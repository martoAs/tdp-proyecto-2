package juego;

import java.util.LinkedList;

public class Criatura extends Thread {

	protected boolean estaViva;
	protected LinkedList<Celda> cuerpo;
	protected char direccion;

	
	protected Logica controlador;

	protected CriaturaGrafica graficos;

	protected int celdasAgregadas ;

	public Criatura(Logica logica){
		controlador = logica;
		cuerpo = new LinkedList<Celda>();
		graficos = new CriaturaGrafica();
		//Se inicializa la criatura
		cuerpo.addFirst(controlador.getCelda(15,6));
		cuerpo.getFirst().setOcupada(graficos.getImagenCabeza());
		cuerpo.addLast(controlador.getCelda(16,6));
		cuerpo.getLast().setOcupada(graficos.getImagenCuerpo());
		cuerpo.addLast(controlador.getCelda(17,6));
		cuerpo.getLast().setOcupada(graficos.getImagenCuerpo());
		direccion = 'a';
		estaViva = true;


	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(estaViva){
			mover();
			//if(cuerpo.getFirst().getXenTablero()==2) estaViva= false;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		this.stop();
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
	}

	public void cambiarSkin(int estado) {
		graficos.cambiarEstado(estado);
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

}
