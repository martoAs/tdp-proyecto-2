package juego;
import javax.swing.*;

import consumibles.Consumible;

import java.awt.*;

public class Celda extends JLabel{

    protected String imagen;
    protected boolean ocupada;
    protected Consumible consumible;

    public Celda(String img){
        imagen = img;
        ImageIcon ic = new ImageIcon(Celda.class.getResource(imagen));
        Image image = ic.getImage(); // transform it
        Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ic = new ImageIcon(newimg);  // transform it back


        this.setIcon(ic);
        ocupada = false;
        consumible = null;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
        this.setIcon(new ImageIcon(Celda.class.getResource(imagen)));
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

    public boolean tieneConsumible(){

        return consumible != null;
    }
}
