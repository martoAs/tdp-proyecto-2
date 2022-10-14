package consumibles;
import juego.Criatura;

public abstract class PowerUp extends Consumible{
	

	protected int estado;

	//Redefine el metodo de consumible para tambien cambiar la skin de la criatura
	public synchronized void afectarJugador(Criatura criatura) {
		size = 3;
		super.afectarJugador(criatura);
		criatura.cambiarSkin(estado);
	}
}
