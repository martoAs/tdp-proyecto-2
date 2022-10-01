package consumibles;

import juego.Criatura;

public abstract class Consumible {
	protected int size;
	protected int puntaje;
	
	public void afectarJugador(Criatura criatura) {
		criatura.agrandarCriatura(size);
		criatura.getControlador().sumarPuntaje(puntaje);
	}
}
