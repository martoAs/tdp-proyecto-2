package consumibles;
import juego.Criatura;

public abstract class PowerUp extends Consumible{
	
	protected int skin;
	protected int estado;
	
	public void afectarJugador(Criatura criatura) {
		size = 3;
		super.afectarJugador(criatura);
		criatura.cambiarSkin(estado);
	}
}
