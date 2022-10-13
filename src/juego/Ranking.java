
package juego;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class Ranking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected List<Jugador> listaJugadores;
	private String lugarGuardado;
	private static final int NUMERO_JUGADORES = 5;
	
	public Ranking(String nombreArchivo) {
		listaJugadores = new ArrayList<>();
		lugarGuardado = nombreArchivo;
		File file = new File(lugarGuardado);
		try {
			if(!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Ordena por puntaje de forma descendiente, si son iguales posiciona el de menor tiempo arriba */
	public void ordenar() {
		listaJugadores.sort(new Comparator<Jugador>() {

			@Override
			public int compare(Jugador jug1, Jugador jug2) {
				Integer puntaje1 = jug1.getPuntaje();
				Integer puntaje2 = jug2.getPuntaje();
				int comparacion =  puntaje2.compareTo(puntaje1);
				if(comparacion != 0){ 
					return comparacion;
				}
				else { //Los puntajes son iguales
					String tiempo1 = jug1.getTiempo();
					String tiempo2 = jug2.getTiempo();
					return tiempo1.compareTo(tiempo2);
				}
			}
			
		});
	}
	
	public boolean agregarJugador(Jugador jug) {
		boolean resultado = false;
		if(listaJugadores.size() < NUMERO_JUGADORES) {
			listaJugadores.add(jug);
			ordenar();
			resultado = true;
		}
		else {
			Jugador jugMenosPuntaje = listaJugadores.get(0);
			if(jug.getPuntaje() > jugMenosPuntaje.getPuntaje()) {
				listaJugadores.remove(jugMenosPuntaje);
				listaJugadores.add(jug);
				ordenar();
				resultado = true;
			}
		}
		
		return resultado;
	}
	
	public void guardar() {
		try {
			FileOutputStream fos = new FileOutputStream(lugarGuardado);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			if((oos != null))
				oos.writeObject(this);
			oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Ranking abrir() {
		Ranking res = null;
		try {
			FileInputStream file = new FileInputStream(lugarGuardado); 
			ObjectInputStream in = new ObjectInputStream(file); 
          
			res = (Ranking) in.readObject(); 
          
			in.close(); 
			file.close(); 
		}catch(IOException e) {
			e.printStackTrace();
		}
		 catch(ClassNotFoundException ex) { 
            System.out.println("Clase no encontrada"); 
        } 
		return res;
	}

	public Jugador getJugador(int pos) {
		return listaJugadores.get(pos);
	}
	
	public int getSize() {
		return listaJugadores.size();
	}
	
	public boolean existeArchivo() {
		File f = new File(lugarGuardado);
		boolean res = f.exists() && f.length() != 0;
		return res;
	}
}
