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

    public Celda( int tam, int x, int y){
        imagen = "";
        size = tam;
        xenTablero = x;
        yenTablero = y;

        ocupada = false;
        consumible = null;
    }

    public String getImagen() {
        return imagen;
    }

    private void setImagen(String img) {
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
        ocupada = false;

    }

    public void setImagenCuerpo(){

        setImagen("/images/cuerpo.png");
        ocupada= true;
    }

    public void setImagenPared(){

        setImagen("/images/pared.jpg");
        ocupada = true;
    }

    public void setImagenCabeza(){
        if(xenTablero%2 != yenTablero%2) setImagen("/images/cabeza1.jpg");
        else setImagen("/images/cabeza2.jpg");
        ocupada = true;

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
    

    
    public int getXenTablero(){
        return xenTablero;
    }
    

    
    public int getYenTablero(){
        return yenTablero;
    }
    
    public void efecto(Criatura criatura) {
    	if (ocupada == true){
            criatura.setEstaViva(false);

        }

        if(consumible != null) consumible.afectarJugador(criatura);
    }
    
    public int getSizeCelda(){
    	return size;
    }
}
