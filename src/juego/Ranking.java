package juego;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class Ranking implements Serializable {
	
	//public listadoJugador; 
	protected List<Jugador> listaJugadores;
	private String lugarGuardado;
	private static final int NUMERO_JUGADORES = 10;
	
	public Ranking(String nombreArchivo) {
		listaJugadores = new ArrayList<>();
		lugarGuardado = nombreArchivo;
		File file = new File(lugarGuardado);
		try {
			if(!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ordenarPorPuntaje() {
		listaJugadores.sort(new Comparator<Jugador>() {

			@Override
			public int compare(Jugador jug1, Jugador jug2) {
				Integer puntaje1 = jug1.getPuntaje();
				Integer puntaje2 = jug2.getPuntaje();
				return puntaje1.compareTo(puntaje2);
			}
			
		});
	}
	
	public void ordenarPorTiempo() {
		listaJugadores.sort(new Comparator<Jugador>() {
			

			@Override
			public int compare(Jugador jug1, Jugador jug2) {
				String tiempo1 = jug1.getTiempo();
				String tiempo2 = jug2.getTiempo();
				return tiempo1.compareTo(tiempo2);
			}
			
		});
	}
	
	public boolean agregarJugador(Jugador jug) {
		boolean resultado = false;
		if(listaJugadores.size() < NUMERO_JUGADORES) {
			listaJugadores.add(jug);
			ordenarPorPuntaje();
			resultado = true;
		}
		else {
			Jugador jugMenosPuntaje = listaJugadores.get(0);
			if(jug.getPuntaje() > jugMenosPuntaje.getPuntaje()) {
				listaJugadores.remove(jugMenosPuntaje);
				listaJugadores.add(jug);
				ordenarPorPuntaje();
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
          
			// Method for deserialization of object 
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
