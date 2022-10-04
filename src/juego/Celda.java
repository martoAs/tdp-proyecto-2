package juego;
import javax.swing.*;
import consumibles.Consumible;
import java.awt.*;

@SuppressWarnings("serial")
public class Celda extends JLabel{

    protected String imagen;
    protected int size;
    protected boolean ocupada;
    protected int xenTablero;
    protected int yenTablero;
    
    protected Consumible consumible;

    public Celda(String img, int tam, int x, int y){
        imagen = img;
        size = tam;
        xenTablero = x;
        yenTablero = y;
        ImageIcon ic = new ImageIcon(Celda.class.getResource(imagen));
        Image image = ic.getImage();
        Image newimg = image.getScaledInstance(tam, tam,  java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);


        this.setIcon(ic);
        ocupada = false;
        consumible = null;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String img) {
        imagen = img;
        ImageIcon ic = new ImageIcon(Celda.class.getResource(imagen));
        Image image = ic.getImage();
        Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);


        this.setIcon(ic);
        this.repaint();
    }

    public void setImagenFondo(){
        if(xenTablero%2 != yenTablero%2) setImagen("/images/celda1.jpg");
        else setImagen("/images/celda2.jpg");

    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Consumible getConsumible() {
        return consumible;
    }

    public void setConsumible(Consumible consumible) {
        this.consumible = consumible;
    }
    
    public void setX(int x) {
    	xenTablero = x;
    }
    
    public int getXenTablero(){
        return xenTablero;
    }
    
    public void setY(int y) {
    	xenTablero = y;
    }
    
    public int getYenTablero(){
        return yenTablero;
    }
    
    public void efecto(Criatura criatura) {
    	if(consumible != null) consumible.afectarJugador(criatura);
    }
    
    public int getSizeCelda(){
    	return size;
    }
}
