package consumibles;
import juego.Criatura;

public abstract class PowerUp extends Consumible{
	
	protected String skin;
	
	public void afectarJugador(Criatura criatura) {
		super.afectarJugador(criatura);
		criatura.cambiarSkin(skin);
	}
}
