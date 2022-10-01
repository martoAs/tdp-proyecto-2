package juego;

import java.util.ArrayList;
import java.util.LinkedList;

public class Criatura extends Thread {

	protected boolean estaViva;
	protected Logica controlador;

	protected LinkedList<Celda> cuerpo;

	protected char direccion;

	public Criatura(Logica logica){
		controlador = logica;
		cuerpo = new LinkedList<Celda>();
		//Criatura en (3,6)-(3,7)-(4,7)
		cuerpo.addFirst(controlador.getCelda(15,6));
		cuerpo.getFirst().setImagen("/images/cuerpo.png");
		cuerpo.addLast(controlador.getCelda(16,6));
		cuerpo.getLast().setImagen("/images/cuerpo.png");
		cuerpo.addLast(controlador.getCelda(17,6));
		cuerpo.getLast().setImagen("/images/cuerpo.png");
		direccion = 'a';
		estaViva = true;
	}

	@Override
	public void run() {
		while(estaViva){
			mover();
			if(cuerpo.getFirst().getXenTablero()==2) estaViva= false;
			try {
				sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		this.stop();
	}


	public void setDireccion(char d){
		direccion = d;
	}
	public void mover(){

		//COMO C HACE EL SWITCH
		switch (direccion){
			case 'a': {
				if(cuerpo.getFirst().getXenTablero()>2){
					Celda cabeza = cuerpo.getFirst();
					int sgtX = cabeza.getXenTablero()-1;
					System.out.println(sgtX+"  "+cabeza.getYenTablero());
					Celda nuevaCabeza = controlador.getCelda(sgtX,cabeza.getYenTablero());
					nuevaCabeza.setImagen("/images/cuerpo.png");
					cuerpo.addFirst(nuevaCabeza);
					Celda borrar = cuerpo.getLast();
					borrar.setImagenFondo();
					cuerpo.removeLast();
				}
			}break;
			case 'd': {
				if(cuerpo.getFirst().getXenTablero()<18){
					Celda cabeza = cuerpo.getFirst();
					int sgtX = cabeza.getXenTablero()+1;
					System.out.println(sgtX+"  "+cabeza.getYenTablero());
					Celda nuevaCabeza = controlador.getCelda(sgtX,cabeza.getYenTablero());
					nuevaCabeza.setImagen("/images/cuerpo.png");
					cuerpo.addFirst(nuevaCabeza);
					Celda borrar = cuerpo.getLast();
					borrar.setImagenFondo();
					cuerpo.removeLast();
				}
			}break;
			case 'w': {
				if(cuerpo.getFirst().getYenTablero()>2){
					Celda cabeza = cuerpo.getFirst();
					int sgtY = cabeza.getYenTablero()-1;
					//System.out.println(sgtX+"  "+cabeza.getYenTablero());
					Celda nuevaCabeza = controlador.getCelda(cabeza.getXenTablero(),sgtY);
					nuevaCabeza.setImagen("/images/cuerpo.png");
					cuerpo.addFirst(nuevaCabeza);
					Celda borrar = cuerpo.getLast();
					borrar.setImagenFondo();
					cuerpo.removeLast();
				}
			}break;
			case 's': {// BANCO WASD
				if(cuerpo.getFirst().getYenTablero()<18){
					Celda cabeza = cuerpo.getFirst();
					int sgtY = cabeza.getYenTablero()+1;
					//System.out.println(sgtX+"  "+cabeza.getYenTablero());
					Celda nuevaCabeza = controlador.getCelda(cabeza.getXenTablero(),sgtY);
					nuevaCabeza.setImagen("/images/cuerpo.png");
					cuerpo.addFirst(nuevaCabeza);
					Celda borrar = cuerpo.getLast();
					borrar.setImagenFondo();
					cuerpo.removeLast();
				}
			}break;
		}


	}

	public void cambiarSkin(String nuevaSkin) {
		
	}
	
	public void agrandarCriatura(int tam) {
		
	}

	public Logica getControlador() {
		return controlador;
	}
	
	public char getDireccion() {
		return direccion;
	}



}
