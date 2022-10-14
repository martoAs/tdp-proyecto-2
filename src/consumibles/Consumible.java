package consumibles;

import juego.Criatura;

public abstract class Consumible {
	protected int size;
	protected int puntaje;
	protected String skin1;
	protected String skin2;
	
	public void afectarJugador(Criatura criatura) {
		criatura.agrandarCriatura(size);
		criatura.getControlador().sumarPuntaje(puntaje);
		criatura.consumibleComido();
	}

	public String getSkin1() {
		return skin1;
	}


	public String getSkin2() {
		return skin2;
	}
	
}
